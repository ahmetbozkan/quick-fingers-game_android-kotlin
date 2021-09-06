package com.ahmetbozkan.quickfingers.ui.arcade

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.databinding.FragmentArcadeModeBinding
import com.ahmetbozkan.quickfingers.util.extension.hideMenuItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArcadeModeFragment : BaseFragment<FragmentArcadeModeBinding, ArcadeModeViewModel>() {

    override val viewModel: ArcadeModeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_arcade_mode

    override fun initialize(savedInstanceState: Bundle?) {

        observeLiveData()

        manageEditText()

    }

    private fun observeLiveData() {
        viewModel.isFinished.observe(viewLifecycleOwner) { isFinished ->
            if (isFinished) {
                val result = viewModel.onGameFinished()

                val action = ArcadeModeFragmentDirections
                    .actionArcadeModeFragmentToResultFragment(result)
                findNavController().navigate(action)
            }
        }
    }

    private fun manageEditText() {
        binding.editTextWord.setOnEditorActionListener { _, keyEvent, _ ->
            if (keyEvent == EditorInfo.IME_ACTION_DONE) {
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