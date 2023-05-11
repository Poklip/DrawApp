package com.example.myfirstdrawing

import androidx.annotation.ColorRes
import com.example.myfirstdrawing.base.Item

sealed class ToolItem : Item {
    data class ColorModel(@ColorRes val color: Int) : ToolItem()
    data class SizeModel(val size: Int) : ToolItem()
    data class ToolModel(
        val type: TOOLS,
        val selectedTool: TOOLS = TOOLS.NORMAL,
        val isSelected: Boolean = false,
//        val selectedSIZE: SIZE = SIZE.SMALL,
        val selectedColor: COLOR = COLOR.BLACK
    ) : ToolItem()
}