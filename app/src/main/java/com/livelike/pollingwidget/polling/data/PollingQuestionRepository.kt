package com.livelike.pollingwidget.polling.data

import androidx.lifecycle.LiveData
import com.livelike.pollingwidget.core.NetworkBoundResource
import com.livelike.pollingwidget.core.Resource
import com.livelike.pollingwidget.core.util.distinctUntilChanged
import com.livelike.pollingwidget.polling.data.PollingQuestionRepository.localDataSource
import com.livelike.pollingwidget.polling.data.models.AnswerEntity
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import com.livelike.pollingwidget.polling.data.source.local.QuestionsLocalDataSource
import com.livelike.pollingwidget.polling.data.source.remote.QuestionsRemoteDataSource
import kotlinx.coroutines.*

/**
 * Created by shivanshmittal on 2019-06-22.
 */
object PollingQuestionRepository {

    val localDataSource = QuestionsLocalDataSource

    val remoteDataSource = QuestionsRemoteDataSource


    suspend fun getQuestion(questionType: Int): LiveData<Resource<QuestionOptionRelation>> {
        return object : NetworkBoundResource<QuestionOptionRelation, QuestionOptionRelation>() {
            override suspend fun saveCallResults(items: QuestionOptionRelation) {
                return withContext(Dispatchers.Default) {
                    localDataSource.insertQuestion(items)
                }
            }

            override fun shouldFetch(data: QuestionOptionRelation?): Boolean {
                return data == null
            }

            override suspend fun loadFromDb(): LiveData<QuestionOptionRelation> {
                return withContext(Dispatchers.Default) {
                    localDataSource.getQuestion(questionType)
                }
            }

            override suspend fun fromRemote(): Deferred<QuestionOptionRelation> {
                return CoroutineScope(Dispatchers.Default).async { remoteDataSource.getQuestion(questionType) }
            }

        }.build().asLiveData().distinctUntilChanged()
    }

    fun getSelectedOption(id: Long): LiveData<AnswerEntity> {
       return localDataSource.getSelectedOption(id)
    }

    suspend fun selectAnswer(questionId: Long, optionId: Long) {
        localDataSource.selectAnswer(AnswerEntity(questionId,optionId))
    }


}