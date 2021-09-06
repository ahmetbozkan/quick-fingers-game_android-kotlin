package com.ahmetbozkan.quickfingers.ui.howtoplay

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.databinding.FragmentHowToPlayBinding
import com.ahmetbozkan.quickfingers.util.extension.hideMenuItem

class HowToPlayFragment : BaseFragment<FragmentHowToPlayBinding, HowToPlayViewModel>() {

    override val viewModel: HowToPlayViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_how_to_play

    override fun initialize(savedInstanceState: Bundle?) {
        // nothing
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.hideMenuItem(R.id.howToPlayFragment)
    }


}