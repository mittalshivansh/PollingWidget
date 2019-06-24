package com.livelike.pollingwidget.core.database

import androidx.room.PrimaryKey

/**
 * Created by shivanshmittal on 2019-06-23.
 */
abstract class AbstractEntity(
    @PrimaryKey
    val id: Long,
    val inActive: Boolean
)