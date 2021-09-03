package com.ahmetbozkan.quickfingers.util.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ahmetbozkan.quickfingers.data.db.preference.GameMode
import com.ahmetbozkan.quickfingers.data.model.Result

/**
 * turns milliseconds value and turns it to seconds (1000 millis -> 1 sec)
 * sets converted value to the given TextView
 */
@BindingAdapter("millisToSeconds")
fun turnMillisIntoSeconds(view: TextView, value: Long) {
    view.text = (value / 1000).toString()
}

/**
 * sets Words per minute or Time passed value to the given TextView +
 * according to the result's GameMode
 */
@BindingAdapter("setWpmOrTimePassed")
fun setWpmOrTimePassedByGameMode(view: TextView, result: Result) {
    if (result.gameMode == GameMode.CLASSIC)
        view.text = "Words per minute: " + result.wordsPerMinute.toString()
    else
        view.text = "Time passed: " + result.timePassed.toString()

}