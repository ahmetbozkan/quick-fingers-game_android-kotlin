package com.ahmetbozkan.quickfingers.util.extension

fun Int?.orZero(): Int {
    return this ?: 0
}