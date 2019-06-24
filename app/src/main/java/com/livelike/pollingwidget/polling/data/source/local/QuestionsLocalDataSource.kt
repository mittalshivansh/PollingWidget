package com.livelike.pollingwidget.polling.data.source.local

import androidx.lifecycle.LiveData
import com.livelike.pollingwidget.core.database.PollingDatabase
import com.livelike.pollingwidget.polling.data.models.QuestionEntity
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import com.livelike.pollingwidget.polling.data.models.QuestionType



object QuestionsLocalDataSource {

    val questionsDao: QuestionsDao = PollingDatabase.getInstance().questionsDao()


     suspend fun inserQuestion(questionOptionRelation: QuestionOptionRelation) {
        PollingDatabase.getInstance().runInTransaction{
                questionsDao.insert(
                    QuestionEntity(questionOptionRelation.id,questionOptionRelation.inActive,
                    questionOptionRelation.type,questionOptionRelation.value)
                )
            PollingDatabase.getInstance().optionsDao().insertAll(questionOptionRelation.options)
        }
    }



    suspend fun getTextQuestion(): LiveData<QuestionOptionRelation> {
        return questionsDao.getQuestion(QuestionType.TEXT.id)
    }


    suspend fun getImageQuestion(): LiveData<QuestionOptionRelation> {
        return questionsDao.getQuestion(QuestionType.IMAGE.id)
    }

}
