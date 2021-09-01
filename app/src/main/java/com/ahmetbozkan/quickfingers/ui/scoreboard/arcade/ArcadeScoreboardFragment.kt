package com.ahmetbozkan.quickfingers.ui.scoreboard.arcade

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.databinding.FragmentArcadeScoreboardBinding
import com.ahmetbozkan.quickfingers.ui.scoreboard.ScoreboardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArcadeScoreboardFragment: Fragment(R.layout.fragment_arcade_scoreboard) {

    private var _binding: FragmentArcadeScoreboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArcadeScoreboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArcadeScoreboardBinding.bind(view)

        val adapter = ScoreboardAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        viewModel.results.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}