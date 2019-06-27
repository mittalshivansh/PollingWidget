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
import kotlinx.android.synthetic.main.image_poll_widget_fragment.*
import kotlinx.android.synthetic.main.item_poll_image.*
import kotlinx.android.synthetic.main.item_poll_text.*

class ImagePollWidgetFragment : Fragment() {

    companion object {
        fun newInstance() = ImagePollWidgetFragment()
    }

    private lateinit var viewModel: ImagePollWidgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_poll_widget_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ImagePollWidgetViewModel::class.java)

        viewModel.start()

        setupViewBindings()

    }

    private fun setupViewBindings() {

        viewModel.state.observe(this, Observer { })


        image_options_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        val imageOptionsAdapter = ImageOptionsAdapter(viewModel.imageTypeQuestion.value?.options, viewModel)
        image_options_recycler_view.adapter = imageOptionsAdapter

        viewModel.imageTypeQuestion.observe(viewLifecycleOwner,
            Observer<QuestionOptionRelation> { question ->
                image_question_title.text = question.value
                imageOptionsAdapter.replaceData(question.options)
            })

    }

}
