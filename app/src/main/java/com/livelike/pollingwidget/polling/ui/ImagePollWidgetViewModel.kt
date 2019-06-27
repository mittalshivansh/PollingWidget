package com.livelike.pollingwidget.polling.ui

import androidx.lifecycle.*
import com.livelike.pollingwidget.core.Resource
import com.livelike.pollingwidget.core.State
import com.livelike.pollingwidget.polling.data.PollingQuestionRepository
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import kotlinx.coroutines.launch

class ImagePollWidgetViewModel : ViewModel() {

    val state = MutableLiveData<State>()


    private val _imageTypeQuestion = MediatorLiveData<QuestionOptionRelation>()

    val imageTypeQuestion: LiveData<QuestionOptionRelation>
        get() = _imageTypeQuestion


    fun start() {
        if (state.value == null) {

            state.value = State.ShowLoading

            viewModelScope.launch {

                _imageTypeQuestion.addSource(PollingQuestionRepository.getImageTypeQuestion()) {
                    if (it.status.equals(Resource.Status.SUCCESS))
                        _imageTypeQuestion.postValue(it.data)
                }
            }
        }
    }

}
