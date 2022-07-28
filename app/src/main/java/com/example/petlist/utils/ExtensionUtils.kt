package com.example.petlist.utils

import android.content.res.AssetManager
import java.util.*

fun AssetManager.readFile(fileName: String) = open(fileName)
    .bufferedReader()
    .use { it.readText() }

fun Calendar.getWeekDay(): String = when (get(Calendar.DAY_OF_WEEK)) {
    Calendar.SUNDAY -> "SU"
    Calendar.MONDAY -> "M"
    Calendar.TUESDAY -> "T"
    Calendar.WEDNESDAY -> "W"
    Calendar.THURSDAY -> "TH"
    Calendar.FRIDAY -> "F"
    else -> "S"
}