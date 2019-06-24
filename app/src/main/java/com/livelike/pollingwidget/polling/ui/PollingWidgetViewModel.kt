package com.livelike.pollingwidget.polling.ui

import androidx.lifecycle.*
import com.livelike.pollingwidget.core.Resource
import com.livelike.pollingwidget.core.State
import com.livelike.pollingwidget.polling.data.PollingQuestionRepository
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by shivanshmittal on 2019-06-23.
 */

class PollingWidgetViewModel : ViewModel() {

    val state = MutableLiveData<State>()

    private val _textTypeQuestion = MediatorLiveData<QuestionOptionRelation>()

    private val _imageTypeQuestion = MediatorLiveData<QuestionOptionRelation>()

    val textTypeQuestion: LiveData<QuestionOptionRelation>
        get() = _textTypeQuestion

    val imageTypeQuestion: LiveData<QuestionOptionRelation>
        get() = _imageTypeQuestion


    fun start() {
        if (state.value == null) {

            state.value = State.ShowLoading

            viewModelScope.launch{
                _textTypeQuestion.addSource(PollingQuestionRepository.getTextTypeQuestion()) {
                    if (it.status.equals(Resource.Status.SUCCESS))
                        _textTypeQuestion.postValue(it.data)
                }

                _imageTypeQuestion.addSource(PollingQuestionRepository.getImageTypeQuestion()) {
                    if (it.status.equals(Resource.Status.SUCCESS))
                        _imageTypeQuestion.postValue(it.data)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}