package com.ahmetbozkan.quickfingers.ui.result

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.model.Result
import com.ahmetbozkan.quickfingers.databinding.FragmentResultBinding
import com.ahmetbozkan.quickfingers.util.extension.hideMenuItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding, ResultViewModel>() {

    override val viewModel: ResultViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_result

    private val args: ResultFragmentArgs by navArgs()

    private lateinit var result: Result

    override fun initialize(savedInstanceState: Bundle?) {

        getArgs()

        manageClick()
    }

    private fun getArgs() {
        result = args.result

        binding.result = result
    }

    private fun manageClick() {
        binding.apply {
            buttonPlayAgain.setOnClickListener {
                navigateToGameScreen()
            }

            buttonMainMenu.setOnClickListener {
                popBackStack()
            }
        }
    }

    private fun navigateToGameScreen() {
        val action: NavDirections = when (result.gameMode) {
            GameMode.CLASSIC -> {
                ResultFragmentDirections.actionResultFragmentToClassicModeFragment()
            }
            GameMode.ARCADE -> {
                ResultFragmentDirections.actionResultFragmentToArcadeModeFragment()
            }
            else -> {
                ResultFragmentDirections.actionResultFragmentToClassicModeFragment()
            }

        }
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_result, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                viewModel.onSaveClick(result)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}