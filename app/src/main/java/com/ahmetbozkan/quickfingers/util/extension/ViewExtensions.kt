package com.ahmetbozkan.quickfingers.util.extension

import android.view.View

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.INVISIBLE
}

fun View.goneView() {
    this.visibility = View.GONE
}