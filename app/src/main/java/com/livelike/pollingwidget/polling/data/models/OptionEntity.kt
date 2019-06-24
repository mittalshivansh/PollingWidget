package com.livelike.pollingwidget.polling.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.livelike.pollingwidget.core.database.AbstractEntity

/**
 * Created by shivanshmittal on 2019-06-23.
 */
@Entity(tableName = "Option")
class OptionEntity constructor(
    id: Long,
    inActive: Boolean,
    val questionId: Long,
    val value: String
) : AbstractEntity(id, inActive)
