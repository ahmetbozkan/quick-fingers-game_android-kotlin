package com.ahmetbozkan.quickfingers.ui.classic

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.databinding.FragmentClassicModeBinding
import com.ahmetbozkan.quickfingers.util.Constants
import com.ahmetbozkan.quickfingers.util.extension.hideMenuItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassicModeFragment : BaseFragment<FragmentClassicModeBinding, ClassicModeViewModel>() {

    override val viewModel: ClassicModeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_classic_mode

    private lateinit var timer: CountDownTimer

    override fun initialize(savedInstanceState: Bundle?) {

        initTimer()

        observeLiveData()

        manageEditText()

    }

    private fun initTimer() {
        timer = object : CountDownTimer(viewModel.time.value!!, Constants.COUNTDOWN_INTERVAL) {
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
    }

    private fun observeLiveData() {
        viewModel.startTimer.observe(viewLifecycleOwner) { start ->
            if (start)
                timer.start()
            else
                timer.cancel()
        }
    }

    private fun manageEditText() {
        binding.editTextWord.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                viewModel.onEnterPressed(getEditTextValue())

                binding.editTextWord.text?.clear()
            }

            true
        }
    }

    private fun getEditTextValue(): String =
        binding.editTextWord.text.toString()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_game_modes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_replay -> {
                viewModel.onReplayClicked()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}