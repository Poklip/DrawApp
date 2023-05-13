package com.example.myfirstdrawing


import androidx.annotation.ColorRes

data class CanvasViewState(val color: COLOR, val size: SIZE, val tools: TOOLS)

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

enum class SIZE(
    val value: Int
) {
    SMALL(4),
    MEDIUM(16),
    LARGE(32);

    companion object {
        private val map = values().associateBy(SIZE::value)
        fun from(size: Int) = map[size] ?: SMALL
    }
}

