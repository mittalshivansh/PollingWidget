package com.livelike.pollingwidget.polling.data.source.local

import androidx.lifecycle.LiveData
import com.livelike.pollingwidget.core.database.PollingDatabase
import com.livelike.pollingwidget.polling.data.models.AnswerEntity
import com.livelike.pollingwidget.polling.data.models.QuestionEntity
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation


object QuestionsLocalDataSource {

    val questionsDao: QuestionsDao = PollingDatabase.getInstance().questionsDao()


    suspend fun insertQuestion(questionOptionRelation: QuestionOptionRelation) {
        PollingDatabase.getInstance().runInTransaction{
            questionsDao.insertOrUpdate(
                QuestionEntity(
                    questionOptionRelation.id, questionOptionRelation.inActive,
                    questionOptionRelation.type, questionOptionRelation.value
                )
            )
            PollingDatabase.getInstance().optionsDao().insertOrUpdateAll(questionOptionRelation.options)
        }

    }


    suspend fun getQuestion(questionType: Int): LiveData<QuestionOptionRelation> {
        return questionsDao.getQuestion(questionType)
    }



    fun getSelectedOption(id: Long): LiveData<AnswerEntity> {
        return PollingDatabase.getInstance().answerDao().getAnswer(id)
    }

    suspend fun selectAnswer(answerEntity: AnswerEntity) {
        PollingDatabase.getInstance().answerDao().insertOrUpdate(answerEntity)
    }

}
