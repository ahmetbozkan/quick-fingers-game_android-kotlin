package com.ahmetbozkan.quickfingers.ui.scoreboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.base.BaseFragment
import com.ahmetbozkan.quickfingers.databinding.FragmentScoreboardBinding
import com.ahmetbozkan.quickfingers.ui.scoreboard.arcade.ArcadeScoreboardFragment
import com.ahmetbozkan.quickfingers.ui.scoreboard.classic.ClassicScoreboardFragment
import com.ahmetbozkan.quickfingers.util.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreboardFragment : BaseFragment<FragmentScoreboardBinding, ScoreboardViewModel>() {

    override val viewModel: ScoreboardViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_scoreboard

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var mediator: TabLayoutMediator

    override fun initialize(savedInstanceState: Bundle?) {
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(ClassicScoreboardFragment())
        viewPagerAdapter.addFragment(ArcadeScoreboardFragment())

        binding.apply {
            viewPager.adapter = viewPagerAdapter

            mediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Classic"
                    1 -> tab.text = "Arcade"
                }
            }
            mediator.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator.detach()
    }
}