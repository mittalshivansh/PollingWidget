package com.livelike.pollingwidget.polling.data.source.local

/**
 * Created by shivanshmittal on 2019-06-22.
 */

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.livelike.pollingwidget.core.database.BaseDao
import com.livelike.pollingwidget.polling.data.models.OptionEntity
import com.livelike.pollingwidget.polling.data.models.QuestionEntity
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation

/**
 * Data Access Object for the Options table.
 */
@Dao
abstract class OptionsDao : BaseDao<OptionEntity>() {


    @Query("SELECT * FROM Option where questionId= :id")
    abstract fun getOptions(id: Int): LiveData<OptionEntity>

}