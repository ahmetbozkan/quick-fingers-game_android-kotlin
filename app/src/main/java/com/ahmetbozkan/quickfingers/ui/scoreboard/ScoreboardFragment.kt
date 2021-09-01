package com.ahmetbozkan.quickfingers.ui.scoreboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ahmetbozkan.quickfingers.R
import com.ahmetbozkan.quickfingers.databinding.FragmentScoreboardBinding
import com.ahmetbozkan.quickfingers.ui.scoreboard.arcade.ArcadeScoreboardFragment
import com.ahmetbozkan.quickfingers.ui.scoreboard.classic.ClassicScoreboardFragment
import com.ahmetbozkan.quickfingers.util.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreboardFragment : Fragment(R.layout.fragment_scoreboard) {

    private var _binding: FragmentScoreboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var mediator: TabLayoutMediator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentScoreboardBinding.bind(view)

        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(ClassicScoreboardFragment())
        viewPagerAdapter.addFragment(ArcadeScoreboardFragment())

        binding.apply {
            viewPager.adapter = viewPagerAdapter
            mediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when(position) {
                    0 -> tab.text = "Classic"
                    1 -> tab.text = "Arcade"
                }
            }
            mediator.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.adapter = null
        mediator.detach()
        _binding = null
    }
}