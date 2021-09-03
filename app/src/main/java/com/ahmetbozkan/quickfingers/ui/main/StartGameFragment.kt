package com.ahmetbozkan.quickfingers.ui.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.databinding.FragmentStartGameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartGameFragment : BaseFragment<FragmentStartGameBinding, StartGameViewModel>() {

    override val viewModel: StartGameViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_start_game

    private lateinit var currentGameMode: GameMode

    override fun initialize(savedInstanceState: Bundle?) {

        collectGameMode()

        manageClick()

    }

    private fun collectGameMode() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.preferencesFlow.collect { mode ->
                binding.textViewGameMode.text = mode.name
                currentGameMode = mode
            }
        }
    }

    private fun manageClick() {
        binding.apply {
            buttonStart.setOnClickListener {
                navigateToGame()
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

    private fun navigateToGame() {
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

    private fun prepareNewGameMode(isNext: Boolean): GameMode {
        val gameModes = GameMode.values().toList()
        var currentModeIndex = gameModes.indexOf(currentGameMode)

        return if (!isNext) {
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

}