package com.livelike.pollingwidget.polling.data.source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.livelike.pollingwidget.core.database.BaseDao
import com.livelike.pollingwidget.polling.data.models.AnswerEntity
import com.livelike.pollingwidget.polling.data.models.QuestionEntity
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation


/**
 * Data Access Object for the Answers table.
 */
@Dao
abstract class AnswerDao : BaseDao<AnswerEntity>() {

    @Query("SELECT * FROM Answer where questionId= :questionId")
    abstract fun getAnswer(questionId: Long): LiveData<AnswerEntity>

}