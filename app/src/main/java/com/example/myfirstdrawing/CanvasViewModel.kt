package com.example.myfirstdrawing

import com.example.myfirstdrawing.base.BaseViewModel
import com.example.myfirstdrawing.base.MyEvent
import com.example.myfirstdrawing.viewstate.COLOR
import com.example.myfirstdrawing.viewstate.CanvasViewState
import com.example.myfirstdrawing.viewstate.SIZE
import com.example.myfirstdrawing.viewstate.TOOLS

class CanvasViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
        toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(it) },
        sizeList = enumValues<SIZE>().map { ToolItem.SizeModel(it.value) },
        isPaletteVisible = false,
        isToolsVisible = false,
        isSizeChangerVisible = false,
        canvasViewState = CanvasViewState(color = COLOR.BLACK, size = SIZE.MEDIUM, tools = TOOLS.NORMAL),
    )

    init {
        processUiEvent(DataEvent.OnSetDefaultTools(tool = TOOLS.NORMAL, color = COLOR.BLACK))
    }

    override fun reduce(event: MyEvent, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnToolbarClicked -> return previousState.copy(isToolsVisible = !previousState.isToolsVisible, isPaletteVisible = false, isSizeChangerVisible = false)

            is UiEvent.OnToolsClick -> {
                when (event.index) {

                    TOOLS.PALETTE.ordinal -> return previousState.copy(isPaletteVisible = !previousState.isPaletteVisible)

                    TOOLS.SIZE.ordinal -> return previousState.copy(isSizeChangerVisible = !previousState.isSizeChangerVisible)

                    else -> {

                        val toolsList =  previousState.toolsList.mapIndexed { index, model ->
                            if (index == event.index) {
                                model.copy(isSelected = true)
                            } else {
                                model.copy(isSelected = false)
                            }
                        }

                        return previousState.copy(
                            toolsList = toolsList,
                            canvasViewState = previousState.canvasViewState.copy(tools = TOOLS.values()[event.index])
                        )
                    }
                }
            }

            is UiEvent.OnPaletteClicked -> {
                val selectedColor = COLOR.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.PALETTE) {
                        it.copy(selectedColor = selectedColor)
                    } else {
                        it
                    }
                }

                return previousState.copy(
                    toolsList = toolsList,
                    canvasViewState = previousState.canvasViewState.copy(color = selectedColor)
                )
            }

            is UiEvent.OnSizeClick -> {
                val selectedSize = SIZE.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.SIZE) {
                        it.copy(selectedSize = selectedSize)
                    } else {
                        it
                    }
                }

                return previousState.copy(
                    toolsList = toolsList,
                    canvasViewState = previousState.canvasViewState.copy(size = selectedSize)
                )
            }

            is DataEvent.OnSetDefaultTools -> {
                val toolsList =  previousState.toolsList.map { model ->
                    if (model.type == event.tool) {
                        model.copy(isSelected = true, selectedColor = event.color)
                    } else {
                        model.copy(isSelected = false)
                    }
                }

                return previousState.copy(toolsList = toolsList)
            }

            else -> return null
        }
    }
}