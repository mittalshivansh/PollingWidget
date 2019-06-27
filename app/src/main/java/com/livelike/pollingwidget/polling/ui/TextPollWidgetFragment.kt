package com.livelike.pollingwidget.polling.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.livelike.pollingwidget.R
import com.livelike.pollingwidget.core.State
import com.livelike.pollingwidget.core.util.exhaustive
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import com.livelike.pollingwidget.polling.data.models.QuestionType
import kotlinx.android.synthetic.main.item_poll_text.*
import kotlinx.android.synthetic.main.text_poll_widget_fragment.*

class TextPollWidgetFragment : Fragment() {

    companion object {
        fun newInstance() = TextPollWidgetFragment()
    }

    private lateinit var viewModel: PollWidgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.text_poll_widget_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PollWidgetViewModel::class.java)

        viewModel.start(QuestionType.TEXT)

        setupViewBindings()

    }

    private fun setupViewBindings() {

        viewModel.state.observe(this, Observer {
            when (it){
                is State.ShowLoading -> loading_bar.isVisible=true
                is State.Success -> loading_bar.isVisible = false
                is State.ShowError -> loading_bar.isVisible = false
            }.exhaustive

        })

        text_options_recycler_view.layoutManager = LinearLayoutManager(context)
        val textOptionsAdapter = TextOptionsAdapter(viewModel.question.value?.options, viewModel)
        text_options_recycler_view.adapter = textOptionsAdapter

        viewModel.question.observe(viewLifecycleOwner,
            Observer<QuestionOptionRelation> { question ->
                text_question_title.text = question.value
                textOptionsAdapter.replaceData(question.options)
            })
        viewModel.selectedAnswer.observe(viewLifecycleOwner, Observer{
            textOptionsAdapter.setSelectedAnswer(it.optionId)
        })

    }


}
