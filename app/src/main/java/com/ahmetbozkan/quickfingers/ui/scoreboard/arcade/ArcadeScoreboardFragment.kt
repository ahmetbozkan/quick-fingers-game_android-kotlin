package com.ahmetbozkan.quickfingers.ui.scoreboard.arcade

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.databinding.FragmentArcadeScoreboardBinding
import com.ahmetbozkan.quickfingers.ui.scoreboard.ScoreboardAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArcadeScoreboardFragment :
    BaseFragment<FragmentArcadeScoreboardBinding, ArcadeScoreboardViewModel>() {

    override val viewModel: ArcadeScoreboardViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_arcade_scoreboard

    @Inject
    lateinit var scoreboardAdapter: ScoreboardAdapter

    override fun initialize(savedInstanceState: Bundle?) {

        setupRecyclerView()

        observeLiveData()

    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = scoreboardAdapter
        }
    }

    private fun observeLiveData() {
        viewModel.results.observe(viewLifecycleOwner) { results ->
            scoreboardAdapter.submitList(results)
        }
    }
}