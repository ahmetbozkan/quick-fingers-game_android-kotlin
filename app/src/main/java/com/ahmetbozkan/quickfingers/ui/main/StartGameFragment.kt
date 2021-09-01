package com.ahmetbozkan.quickfingers.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.databinding.FragmentPlayBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartGameFragment : Fragment(R.layout.fragment_play) {

    private var _binding: FragmentPlayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StartGameViewModel by viewModels()
    private lateinit var currentGameMode: GameMode

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlayBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.preferencesFlow.collect { mode ->
                binding.textViewGameMode.text = mode.name
                currentGameMode = mode
            }
        }

        binding.apply {
            buttonStart.setOnClickListener {

                val action: NavDirections = when (currentGameMode) {
                    GameMode.CLASSIC -> {
                        StartGameFragmentDirections.actionStartGameFragmentToClassicModeFragment()
                    }
                    GameMode.ARCADE -> {
                        StartGameFragmentDirections.actionStartGameFragmentToArcadeModeFragment()
                    }
                    else -> {
                        StartGameFragmentDirections.actionStartGameFragmentToClassicModeFragment()
                    }
                }
                findNavController().navigate(action)
            }

            buttonPreviousGameMode.setOnClickListener {
                val newGameMode = prepareNewGameMode(false)
                viewModel.onGameModeChanged(gameMode = newGameMode)
            }

            buttonNextGameMode.setOnClickListener {
                val newGameMode = prepareNewGameMode(true)
                viewModel.onGameModeChanged(gameMode = newGameMode)
            }
        }
    }

    private fun prepareNewGameMode(next: Boolean): GameMode {
        val gameModes = GameMode.values().toList()
        var currentModeIndex = gameModes.indexOf(currentGameMode)

        return if (!next) {
            if (currentModeIndex == 0) {
                gameModes[gameModes.size - 1]
            } else {
                gameModes[--currentModeIndex]
            }
        } else {
            if (currentModeIndex == gameModes.size - 1) {
                gameModes[0]
            } else {
                gameModes[++currentModeIndex]
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}