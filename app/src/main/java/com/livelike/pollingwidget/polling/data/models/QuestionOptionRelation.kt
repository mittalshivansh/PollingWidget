package com.livelike.pollingwidget.polling.data.models

import androidx.room.Relation

/**
 * Created by shivanshmittal on 2019-06-23.
 */
class QuestionOptionRelation {

    var id: Long = 0
    var inActive: Boolean = false
    var type: Int=0
    lateinit var value: String

    @Relation(parentColumn = "id", entityColumn = "questionId")
    lateinit var options: List<OptionEntity>


    constructor(id: Long, type: Int, value: String, options: List<OptionEntity>) {
        this.id = id
        this.type = type
        this.value = value
        this.options = options
    }
}