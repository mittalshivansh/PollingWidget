package com.livelike.pollingwidget.polling.data.models

import androidx.room.Entity
import com.livelike.pollingwidget.core.database.AbstractEntity

/**
 * Created by shivanshmittal on 2019-06-22.
 */
@Entity(tableName = "Question")
class QuestionEntity(
    id: Long,
    inActive: Boolean,
    val type: Int,
    val value: String
) : AbstractEntity(id, inActive)

enum class QuestionType(val id: Int) {
    TEXT(1),
    IMAGE(2)

}