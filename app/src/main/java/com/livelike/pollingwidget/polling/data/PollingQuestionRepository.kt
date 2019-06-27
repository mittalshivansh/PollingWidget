package com.livelike.pollingwidget.polling.data

import androidx.lifecycle.LiveData
import com.livelike.pollingwidget.core.NetworkBoundResource
import com.livelike.pollingwidget.core.Resource
import com.livelike.pollingwidget.core.util.distinctUntilChanged
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


    //For now separating both the question types to segregate their updates

    suspend fun getTextTypeQuestion(): LiveData<Resource<QuestionOptionRelation>> {
        return object : NetworkBoundResource<QuestionOptionRelation, QuestionOptionRelation>() {
            override suspend fun saveCallResults(items: QuestionOptionRelation) {
                return withContext(Dispatchers.Default) {
                    localDataSource.inserQuestion(items)
                }
            }

            override fun shouldFetch(data: QuestionOptionRelation?): Boolean {
                return data == null
            }

            override suspend fun loadFromDb(): LiveData<QuestionOptionRelation> {
                return withContext(Dispatchers.Default) {
                    localDataSource.getTextQuestion()
                }
            }

            override suspend fun fromRemote(): Deferred<QuestionOptionRelation> {
                return CoroutineScope(Dispatchers.Default).async { remoteDataSource.getTextQuestion() }
            }

        }.build().asLiveData().distinctUntilChanged()
    }

    suspend fun getImageTypeQuestion(): LiveData<Resource<QuestionOptionRelation>> {

        return object : NetworkBoundResource<QuestionOptionRelation, QuestionOptionRelation>() {
            override suspend fun saveCallResults(items: QuestionOptionRelation) {
                return withContext(Dispatchers.Default) {
                    localDataSource.inserQuestion(items)
                }
            }

            override fun shouldFetch(data: QuestionOptionRelation?): Boolean {
                return data == null
            }

            override suspend fun loadFromDb(): LiveData<QuestionOptionRelation> {
                return withContext(Dispatchers.Default) {
                    localDataSource.getImageQuestion()
                }
            }

            override suspend fun fromRemote(): Deferred<QuestionOptionRelation> {
                return CoroutineScope(Dispatchers.Default).async { remoteDataSource.getImageQuestion() }
            }

        }.build().asLiveData().distinctUntilChanged()
    }


}