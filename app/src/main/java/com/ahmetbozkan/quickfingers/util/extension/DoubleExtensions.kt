package com.ahmetbozkan.quickfingers.util.extension

/**
 * turn any floating number into two decimals (1.234444444 -> 1.23)
 */
fun Double.formatEndingDecimals(): Double {
    return String.format("%.2f", this).replaceCommas()
        .toDouble()
}

