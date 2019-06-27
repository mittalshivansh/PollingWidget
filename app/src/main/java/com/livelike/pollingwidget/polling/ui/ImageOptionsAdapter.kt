package com.livelike.pollingwidget.polling.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.livelike.pollingwidget.R
import com.livelike.pollingwidget.core.util.loadImage
import com.livelike.pollingwidget.polling.data.models.OptionEntity

/**
 * Created by shivanshmittal on 2019-06-24.
 */
class ImageOptionsAdapter(
    private var  options: List<OptionEntity>?,
    private val viewModel: PollWidgetViewModel
) : RecyclerView.Adapter<ImageOptionsAdapter.VH>() {


    private var selectedOption: Long? = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageOptionsAdapter.VH {
        return ImageOptionsAdapter.VH(LayoutInflater.from(parent.context).inflate(R.layout.item_option_image, null))
    }

    override fun getItemCount(): Int {
        return options?.size ?: 0
    }

    override fun onBindViewHolder(holder: ImageOptionsAdapter.VH, position: Int) {

        val option = options!![position]

        holder.optionImage.loadImage(option.value)
        holder.percentage.text = "${option.percentage} %"
        
        holder.item.isSelected = option.id == selectedOption

        holder.item.setOnClickListener {
            // Not updating ui here to follow single source of truth principle and to encourage unidirectional flow
            viewModel.selectAnswer(option.questionId,option.id)
        }
    }

    fun replaceData(options: List<OptionEntity>) {
        this.options = options
        notifyDataSetChanged()
    }

    fun setSelectedAnswer(id: Long) {
        selectedOption = id
        notifyDataSetChanged()
    }


    class VH(val item: View) : RecyclerView.ViewHolder(item) {

        val optionImage = itemView.findViewById<ImageView>(R.id.option_image)
        val percentage = itemView.findViewById<TextView>(R.id.percentage)
        val percentageBar = itemView.findViewById<View>(R.id.percentage_bar)
    }

}