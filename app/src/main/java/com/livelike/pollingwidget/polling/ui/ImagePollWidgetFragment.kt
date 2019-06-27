package com.livelike.pollingwidget.polling.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.livelike.pollingwidget.R
import com.livelike.pollingwidget.core.State
import com.livelike.pollingwidget.core.util.exhaustive
import com.livelike.pollingwidget.polling.data.models.QuestionOptionRelation
import com.livelike.pollingwidget.polling.data.models.QuestionType
import kotlinx.android.synthetic.main.image_poll_widget_fragment.*
import kotlinx.android.synthetic.main.image_poll_widget_fragment.loading_bar
import kotlinx.android.synthetic.main.item_poll_image.*
import kotlinx.android.synthetic.main.item_poll_text.*
import kotlinx.android.synthetic.main.text_poll_widget_fragment.*

class ImagePollWidgetFragment : Fragment() {

    companion object {
        fun newInstance() = ImagePollWidgetFragment()
    }

    private lateinit var viewModel: PollWidgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_poll_widget_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PollWidgetViewModel::class.java)

        viewModel.start(QuestionType.IMAGE)

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

        image_options_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        val imageOptionsAdapter = ImageOptionsAdapter(viewModel.question.value?.options, viewModel)
        image_options_recycler_view.adapter = imageOptionsAdapter

        viewModel.question.observe(viewLifecycleOwner,
            Observer<QuestionOptionRelation> { question ->
                image_question_title.text = question.value
                imageOptionsAdapter.replaceData(question.options)
            })

        viewModel.selectedAnswer.observe(viewLifecycleOwner, Observer{
            it?.let {imageOptionsAdapter.setSelectedAnswer(it.optionId)}

        })

    }

}
