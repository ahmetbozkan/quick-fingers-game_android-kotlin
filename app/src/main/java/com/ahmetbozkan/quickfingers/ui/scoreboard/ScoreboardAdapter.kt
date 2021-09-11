package com.ahmetbozkan.quickfingers.ui.scoreboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.databinding.RowScoreboardArcadeBinding
import com.ahmetbozkan.quickfingers.databinding.RowScoreboardClassicBinding
import javax.inject.Inject

class ScoreboardAdapter @Inject constructor() :
    ListAdapter<Result, RecyclerView.ViewHolder>(DiffCallback()) {

    companion object {
        private const val VIEW_TYPE_CLASSIC = 1
        private const val VIEW_TYPE_ARCADE = 2
    }

    inner class ClassicScoreboardViewHolder(private val binding: RowScoreboardClassicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.result = result
        }
    }

    inner class ArcadeScoreboardViewHolder(private val binding: RowScoreboardArcadeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.result = result
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if (item != null && item.gameMode == GameMode.CLASSIC) {
            VIEW_TYPE_CLASSIC
        } else {
            VIEW_TYPE_ARCADE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_CLASSIC) {
            ClassicScoreboardViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.row_scoreboard_classic,
                    parent,
                    false
                )
            )
        } else {
            ArcadeScoreboardViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.row_scoreboard_arcade,
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        when (holder.itemViewType) {
            VIEW_TYPE_CLASSIC -> {
                (holder as ClassicScoreboardViewHolder).bind(result = currentItem)
            }
            VIEW_TYPE_ARCADE -> {
                (holder as ArcadeScoreboardViewHolder).bind(result = currentItem)
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