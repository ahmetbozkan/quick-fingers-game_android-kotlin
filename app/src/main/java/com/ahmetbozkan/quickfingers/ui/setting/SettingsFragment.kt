package com.ahmetbozkan.quickfingers.ui.setting

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {

    override val viewModel: SettingsViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_settings

    override fun initialize(savedInstanceState: Bundle?) {
        // nothing
    }
}