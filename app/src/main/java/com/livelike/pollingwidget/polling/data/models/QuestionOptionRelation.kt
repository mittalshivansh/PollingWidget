package com.livelike.pollingwidget.polling.data.models

import androidx.room.Relation

/**
 * Created by shivanshmittal on 2019-06-23.
 */
data class QuestionOptionRelation(
    val id: Long,
    val inActive: Boolean,
    val type: Int,
    val value: String,
    @Relation(parentColumn = "id", entityColumn = "questionId")
    val options: List<OptionEntity>
)