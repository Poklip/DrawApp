package com.example.myfirstdrawing.viewstate

import androidx.annotation.ColorRes
import com.example.myfirstdrawing.R

enum class COLOR(
    @ColorRes
    val value: Int
) {

    BLACK(R.color.black),
    RED(R.color.red),
    BLUE(R.color.blue),
    PINK(R.color.purple_200),
    GREEN(R.color.green),
    YELLOW(R.color.yellow),
    WHITE(R.color.white);

    companion object {
        private val map = values().associateBy(COLOR::value)
        fun from(color: Int) = map[color] ?: BLACK
    }
}