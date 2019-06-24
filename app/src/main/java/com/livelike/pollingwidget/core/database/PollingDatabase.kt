package com.livelike.pollingwidget.core.database

/**
 * Created by shivanshmittal on 2019-06-22.
 */

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.livelike.pollingwidget.PollingApp
import com.livelike.pollingwidget.polling.data.models.OptionEntity
import com.livelike.pollingwidget.polling.data.models.QuestionEntity
import com.livelike.pollingwidget.polling.data.source.local.OptionsDao

import com.livelike.pollingwidget.polling.data.source.local.QuestionsDao

/**
 * The Room Database that contains the Task table.
 */
@Database(entities = arrayOf(QuestionEntity::class,OptionEntity::class), version = 1)
abstract class PollingDatabase : RoomDatabase() {

    abstract fun questionsDao(): QuestionsDao
    abstract fun optionsDao(): OptionsDao

    companion object {

        private var INSTANCE: PollingDatabase? = null

        private val lock = Any()

        fun getInstance(): PollingDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(PollingApp.get(),
                            PollingDatabase::class.java, "poll.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }

}