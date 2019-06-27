package com.livelike.pollingwidget.polling.data.source.remote


import com.livelike.pollingwidget.polling.data.models.OptionEntity
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import com.livelike.pollingwidget.polling.data.models.QuestionType
import kotlinx.coroutines.delay

/**
 * Implementation of the data source that adds a latency simulating network.
 */
object QuestionsRemoteDataSource {

    private const val SERVICE_LATENCY_IN_MILLIS = 1000L

    private var DUMMY_DATA = HashMap<Int, QuestionOptionRelation>(2)

    init {
        addDummyData(QuestionType.TEXT, "What is your favorite game", getTextOptions())
        addDummyData(QuestionType.IMAGE, "Who is your favorite player", getImageOptions())
    }


    suspend fun getQuestion(questionType: Int): QuestionOptionRelation {
        delay(SERVICE_LATENCY_IN_MILLIS)
        return if(questionType.equals(QuestionType.TEXT.id))
            DUMMY_DATA[1]!! else
            DUMMY_DATA[2]!!

    }

    private fun getImageOptions(): List<OptionEntity> {
        val options = mutableListOf<OptionEntity>()
        for (i in 1..4) {
            options.add(OptionEntity((4 + i).toLong(), false, 2, "",10*i))
        }
        return options
    }

    private fun addDummyData(questionType: QuestionType, question: String, options: List<OptionEntity>) {
        DUMMY_DATA.put(
            questionType.id, QuestionOptionRelation(
                questionType.id.toLong(),false,
                questionType.id, question, options
            )
        )

    }

    private fun getTextOptions(): List<OptionEntity> {
        val options = mutableListOf<OptionEntity>()
        for (i in 1..4) {
            options.add(OptionEntity(i.toLong(), false, 1, "Awesome",10*i))
        }
        return options
    }

}

