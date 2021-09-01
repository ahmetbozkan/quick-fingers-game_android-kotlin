package com.ahmetbozkan.quickfingers.util.extension

/**
 * replaces commas with dots (2,15 -> 2.15)
 */
fun String.replaceCommas(): String {
    return this.replace(",", ".")
}