package com.sample.surveycomponent.survey.suveylist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.sample.surveycomponent.data.SurveyItem
import com.sample.surveycomponent.databinding.SurveyItemBinding


class SurveyRecyclerViewAdapter() : RecyclerView.Adapter<SurveyRecyclerViewAdapter.ViewHolder>() {

    private val values = ArrayList<SurveyItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            SurveyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.surveyName.text = item.surveyName
        holder.surveyDesc.text = item.surveyDesc
    }

    override fun getItemCount(): Int = values.size

    fun add(list: List<SurveyItem>) {
        values.addAll(list)
        notifyDataSetChanged()
    }


    inner class ViewHolder(binding: SurveyItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val surveyName: TextView = binding.surveyName
        val surveyDesc: TextView = binding.surveyDescription
    }
}

