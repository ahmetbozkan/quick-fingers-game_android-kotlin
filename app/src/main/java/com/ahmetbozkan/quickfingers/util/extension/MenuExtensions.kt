package com.ahmetbozkan.quickfingers.util.extension

import android.view.Menu

fun Menu.hideMenuItem(itemId: Int) {
    this.findItem(itemId).isVisible = false
}
