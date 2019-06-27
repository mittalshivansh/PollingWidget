package com.livelike.pollingwidget.polling.data.source.local

/**
 * Created by shivanshmittal on 2019-06-22.
 */

import androidx.lifecycle.LiveData
import androidx.room.*
import com.livelike.pollingwidget.core.database.BaseDao
import com.livelike.pollingwidget.polling.data.models.QuestionEntity
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation

/**
 * Data Access Object for the Question table.
 */
@Dao
abstract class QuestionsDao : BaseDao<QuestionEntity>() {

    @Transaction
    @Query("SELECT * FROM Question where type= :questionType")
    abstract fun getQuestion(questionType: Int): LiveData<QuestionOptionRelation>

}