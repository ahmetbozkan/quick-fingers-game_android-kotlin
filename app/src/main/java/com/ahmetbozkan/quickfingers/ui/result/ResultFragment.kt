package com.ahmetbozkan.quickfingers.ui.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.data.GameMode
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment(R.layout.fragment_result) {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResultViewModel by viewModels()
    private val args: ResultFragmentArgs by navArgs()
    private lateinit var result: Result

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentResultBinding.bind(view)

        result = args.result

        binding.apply {
            textViewScore.text = "score: ${result.score}"
            if (result.gameMode == GameMode.CLASSIC)
                textViewWpmTimePassed.text = "wpm: ${result.wordsPerMinute}"
            else textViewWpmTimePassed.text = "time passed: ${result.timePassed} sec"
            textViewCorrect.text = result.correct.toString()
            textViewWrong.text = result.wrong.toString()
            textViewAccuracy.text = result.accuracy.toString()

            buttonPlayAgain.setOnClickListener {
                var action: NavDirections? = null
                when(result.gameMode) {
                    GameMode.CLASSIC -> {
                        action = ResultFragmentDirections.actionResultFragmentToClassicModeFragment()
                    }
                    GameMode.ARCADE -> {
                        action = ResultFragmentDirections.actionResultFragmentToArcadeModeFragment()
                    }
                    GameMode.PARAGRAPH ->
                        action = ResultFragmentDirections.actionResultFragmentToClassicModeFragment()

                }
                findNavController().navigate(action!!)
            }

            buttonMainMenu.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_result, menu)
        menu.findItem(R.id.howToPlayFragment).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                if (!viewModel.saved) {
                    viewModel.onSaveClick(result)
                    viewModel.saved = true

                    Toast.makeText(requireContext(), "Result saved.", Toast.LENGTH_SHORT)
                        .show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}