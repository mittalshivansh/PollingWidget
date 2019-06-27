package com.livelike.pollingwidget.polling.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.livelike.pollingwidget.R
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import kotlinx.android.synthetic.main.item_poll_image.*
import kotlinx.android.synthetic.main.item_poll_text.*

class TextPollWidgetFragment : Fragment() {

    companion object {
        fun newInstance() = TextPollWidgetFragment()
    }

    private lateinit var viewModel: TextPollWidgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.text_poll_widget_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TextPollWidgetViewModel::class.java)

        viewModel.start()

        setupViewBindings()

    }

    private fun setupViewBindings() {

        viewModel.state.observe(this, Observer { })

        text_options_recycler_view.layoutManager = LinearLayoutManager(context)
        val textOptionsAdapter = TextOptionsAdapter(viewModel.textTypeQuestion.value?.options, viewModel)
        text_options_recycler_view.adapter = textOptionsAdapter

        viewModel.textTypeQuestion.observe(viewLifecycleOwner,
            Observer<QuestionOptionRelation> { question ->
                text_question_title.text = question.value
                textOptionsAdapter.replaceData(question.options)
            })
    }


}
