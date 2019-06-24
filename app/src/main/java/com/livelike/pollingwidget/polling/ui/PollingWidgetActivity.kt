package com.livelike.pollingwidget.polling.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.livelike.pollingwidget.R
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import kotlinx.android.synthetic.main.item_poll_image.*
import kotlinx.android.synthetic.main.item_poll_text.*

class PollingWidgetActivity : AppCompatActivity() {


    private lateinit var viewModel: PollingWidgetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polling_widget)

        viewModel = ViewModelProviders.of(this).get(PollingWidgetViewModel::class.java)

        viewModel.start()

        setupViewBindings()

    }

    private fun setupViewBindings() {

        viewModel.state.observe(this, Observer { })

        text_options_recycler_view.layoutManager = LinearLayoutManager(this)
        val textOptionsAdapter = TextOptionsAdapter(viewModel.textTypeQuestion.value?.options, viewModel)
        text_options_recycler_view.adapter = textOptionsAdapter

        viewModel.textTypeQuestion.observe(this,
            Observer<QuestionOptionRelation> { question ->
                text_question_title.text = question.value
                textOptionsAdapter.replaceData(question.options)
            })


        image_options_recycler_view.layoutManager = LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        val imageOptionsAdapter = ImageOptionsAdapter(viewModel.imageTypeQuestion.value?.options, viewModel)
        image_options_recycler_view.adapter = imageOptionsAdapter

        viewModel.imageTypeQuestion.observe(this,
            Observer<QuestionOptionRelation> { question ->
                image_question_title.text = question.value
                imageOptionsAdapter.replaceData(question.options)
            })

    }


}
