package com.ahmetbozkan.quickfingers.ui.mode.classic

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
import com.ahmetbozkan.quickfingers.databinding.FragmentClassicModeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassicModeFragment : Fragment(R.layout.fragment_classic_mode) {

    private var _binding: FragmentClassicModeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClassicModeViewModel by viewModels()

    private lateinit var timer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentClassicModeBinding.bind(view)

        timer = object : CountDownTimer(viewModel.time.value!!, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                viewModel.updateTimer(millisUntilFinished)
            }

            override fun onFinish() {
                val result = viewModel.onGameFinish()

                val action = ClassicModeFragmentDirections
                    .actionClassicModeFragmentToResultFragment(result)
                findNavController().navigate(action)
            }
        }

        binding.apply {
            viewModel.time.observe(viewLifecycleOwner) { time ->
                textViewCountdown.text = (time / 1000).toString()
            }

            viewModel.score.observe(viewLifecycleOwner) { score ->
                textViewScore.text = score.toString()
            }

            viewModel.randomTrWord.observe(viewLifecycleOwner) { word ->
                textViewCurrentWord.text = word
            }

            editTextWord.setOnEditorActionListener { textView, i, _ ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    viewModel.onEnterPressed(textView.text.toString())
                    viewModel.getRandomWord()
                    editTextWord.text?.clear()

                    if (!viewModel.isStarted) {
                        timer.start()
                        viewModel.isStarted = true
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
                    timer.cancel()
                    viewModel.onReplayClicked()

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