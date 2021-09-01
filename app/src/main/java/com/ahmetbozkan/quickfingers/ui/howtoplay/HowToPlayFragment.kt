package com.ahmetbozkan.quickfingers.ui.howtoplay

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import com.ahmetbozkan.quickfingers.R

class HowToPlayFragment: Fragment(R.layout.fragment_how_to_play) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.howToPlayFragment).isVisible = false
    }
}