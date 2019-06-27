package com.livelike.pollingwidget.polling.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.livelike.pollingwidget.R
import com.livelike.pollingwidget.polling.data.models.OptionEntity

/**
 * Created by shivanshmittal on 2019-06-24.
 */
class TextOptionsAdapter(
    private var options: List<OptionEntity>?,
    private val viewModel: TextPollWidgetViewModel
) : RecyclerView.Adapter<TextOptionsAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_option_text, null))
    }

    override fun getItemCount(): Int {
        return options?.size ?: 0
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        val option = options!![position]
        holder.optionLabel.text = option.value
    }

    fun replaceData(options: List<OptionEntity>) {
        this.options = options
        notifyDataSetChanged()
    }


    class VH(val item: View) : RecyclerView.ViewHolder(item) {

        val optionLabel = itemView.findViewById<TextView>(R.id.option_label)
        val percentage = itemView.findViewById<TextView>(R.id.percentage)
        val percentageBar = itemView.findViewById<View>(R.id.percentage_bar)
    }

}