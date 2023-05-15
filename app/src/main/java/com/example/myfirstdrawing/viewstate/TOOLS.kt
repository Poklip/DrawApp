package com.example.myfirstdrawing.viewstate

import androidx.annotation.DrawableRes
import com.example.myfirstdrawing.R

enum class TOOLS(
    @DrawableRes
    val value: Int
) {
    NORMAL(R.drawable.ic_horizontal_line),
    DASH(R.drawable.ic_dashed_line),
    SIZE(R.drawable.size_changed),
    PALETTE(R.drawable.baseline_brightness_1_24)
}