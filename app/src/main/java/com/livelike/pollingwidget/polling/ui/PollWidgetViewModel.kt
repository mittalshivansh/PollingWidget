package com.livelike.pollingwidget.polling.ui

import android.util.Log
import androidx.lifecycle.*
import com.livelike.pollingwidget.core.Resource
import com.livelike.pollingwidget.core.State
import com.livelike.pollingwidget.polling.data.PollingQuestionRepository
import com.livelike.pollingwidget.polling.data.models.AnswerEntity
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import com.livelike.pollingwidget.polling.data.models.QuestionType
import com.livelike.pollingwidget.polling.data.source.local.QuestionsLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PollWidgetViewModel : ViewModel() {


    private val _state = MutableLiveData<State>()

    val state: LiveData<State>
        get() = _state


    private val _selectedAnswer = MediatorLiveData<AnswerEntity>()

    val selectedAnswer: LiveData<AnswerEntity>
        get() = _selectedAnswer


    private val _question = MediatorLiveData<QuestionOptionRelation>()

    val question: LiveData<QuestionOptionRelation>
        get() = _question


    fun start(questionType : QuestionType) {
        if (_state.value == null) {
            _state.value = State.ShowLoading


            viewModelScope.launch {
                _question.addSource(PollingQuestionRepository.getQuestion(questionType.id)) {
                    if (it.status.equals(Resource.Status.SUCCESS)) {
                        //Todo: Asumming question id will not change
                        it.data?.let { subscribeAnswerSteam(it.id) }
                        _question.postValue(it.data)
                        _state.postValue(State.Success)
                    }else if(it.status.equals(Resource.Status.ERROR)){
                        _state.postValue(State.ShowError(it.error.toString()))
                    }
                }

            }
        }
    }

    private fun subscribeAnswerSteam(id: Long) {
        _selectedAnswer.addSource(PollingQuestionRepository.getSelectedOption(id)) {
            _selectedAnswer.postValue(it)
        }
    }

    fun selectAnswer(questionId: Long, optionId: Long) {
        viewModelScope.async(Dispatchers.Default) {
            PollingQuestionRepository.selectAnswer(questionId, optionId)
        }
    }


}