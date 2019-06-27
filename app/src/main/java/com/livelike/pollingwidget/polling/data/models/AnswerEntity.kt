package com.livelike.pollingwidget.polling.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by shivanshmittal on 2019-06-26.
 */
@Entity(tableName = "Answer")
data class AnswerEntity(
    @PrimaryKey
    val questionId: Long,
    val optionId: Long,
    val sentFlag: Boolean = false //for outbound sync with server
)