package com.ahmetbozkan.quickfingers.ui.mode.arcade

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.databinding.FragmentArcadeModeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArcadeModeFragment : Fragment(R.layout.fragment_arcade_mode) {

    private var _binding: FragmentArcadeModeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArcadeModeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArcadeModeBinding.bind(view)

        binding.apply {
            viewModel.time.observe(viewLifecycleOwner) { time ->
                if(time == 0) {
                    val result = viewModel.onGameFinish()

                    val action = ArcadeModeFragmentDirections
                        .actionArcadeModeFragmentToResultFragment(result)
                    findNavController().navigate(action)
                }

                textViewCountdown.text = time.toString()
            }

            viewModel.score.observe(viewLifecycleOwner) { score ->
                textViewScore.text = score.toString()
            }

            viewModel.randomTrWord.observe(viewLifecycleOwner) { word ->
                textViewCurrentWord.text = word
            }

            editTextWord.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    viewModel.onEnterPressed(textView.text.toString())
                    viewModel.getRandomWord()
                    editTextWord.text?.clear()

                    if (!viewModel.isStarted) {
                        viewModel.onGameStarted()
                    }
                }

                true
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_game_modes, menu)
        menu.findItem(R.id.howToPlayFragment).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_replay -> {
                if (viewModel.isStarted) {
                    viewModel.onReplayClick()

                    Toast.makeText(requireContext(), "Game restarted.", Toast.LENGTH_LONG)
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