package com.ahmetbozkan.quickfingers.util.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("millisToSeconds")
fun turnMillisIntoSeconds(view: TextView, value: Long) {
    view.text = (value / 1000).toString()
}