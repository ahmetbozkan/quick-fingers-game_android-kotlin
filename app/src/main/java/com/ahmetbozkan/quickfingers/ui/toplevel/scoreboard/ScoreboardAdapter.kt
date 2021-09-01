package com.ahmetbozkan.quickfingers.ui.toplevel.scoreboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.databinding.SingleScoreboardItemBinding

class ScoreboardAdapter : ListAdapter<Result, ScoreboardAdapter.ScoreboardViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreboardViewHolder {
        val binding = SingleScoreboardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ScoreboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScoreboardViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(result = currentItem)
        }
    }

    inner class ScoreboardViewHolder(private val binding: SingleScoreboardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.apply {
                textViewCorrect.text = result.correct.toString()
                textViewWrong.text = result.wrong.toString()
                textViewScore.text = result.score.toString()
                textViewWpm.text = result.wordsPerMinute.toString()
                textViewAccuracy.text = "%${result.accuracy}"
                textViewDate.text = result.dateSavedFormatted
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem

    }

}