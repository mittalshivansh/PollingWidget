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


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polling_widget)

    }


}
