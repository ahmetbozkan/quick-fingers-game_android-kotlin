package com.ahmetbozkan.quickfingers.ui.scoreboard.classic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.databinding.FragmentClassicScoreboardBinding
import com.ahmetbozkan.quickfingers.ui.scoreboard.ScoreboardAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ClassicScoreboardFragment: BaseFragment<FragmentClassicScoreboardBinding, ClassicScoreboardViewModel>() {

    override val viewModel: ClassicScoreboardViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_classic_scoreboard

    @Inject
    private lateinit var scoreboardAdapter: ScoreboardAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ScoreboardAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

    }

}