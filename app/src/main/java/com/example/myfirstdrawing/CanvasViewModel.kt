package com.example.myfirstdrawing

import com.example.myfirstdrawing.base.BaseViewModel
import com.example.myfirstdrawing.base.MyEvent

class CanvasViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
        toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(it) },
        isPaletteVisible = false,
        isToolsVisible = false
    )

    override fun reduce(event: MyEvent, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnToolbarClicked -> return previousState.copy(isToolsVisible = !previousState.isToolsVisible, isPaletteVisible = false)

            is UiEvent.OnToolsClick -> {
                if (event.index == TOOLS.PALETTE.ordinal) {
                    return previousState.copy(isPaletteVisible = !previousState.isPaletteVisible)
                }
                return null
            }

            is UiEvent.OnColorClick -> return null

            else -> return null
        }
    }
}