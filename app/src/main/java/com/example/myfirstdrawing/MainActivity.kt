package com.example.myfirstdrawing

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PALETTE = 0
        private const val TOOL = 1
    }

    private val viewModel: CanvasViewModel by viewModel()

    private var toolsList: List<ToolsLayout> = listOf()

    private val paletteLayout: ToolsLayout by lazy { findViewById(R.id.paletteLayout) }
    private val toolsLayout: ToolsLayout by lazy { findViewById(R.id.toolsLayout) }
    private val ivTools: ImageView by lazy { findViewById(R.id.ivTools) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolsList = listOf(paletteLayout, toolsLayout)
        viewModel.viewState.observe(this, ::render)

        paletteLayout.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnPaletteClicked(it))
        }

        toolsLayout.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnToolsClick(it))
        }

        ivTools.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnToolbarClicked)
        }
    }

    private fun render(viewState: ViewState) {
        with(toolsList[PALETTE]) {
            render(viewState.colorList)
            isVisible= viewState.isPaletteVisible
        }
        with(toolsList[TOOL]) {
            render(viewState.toolsList)
            isVisible= viewState.isToolsVisible
        }
    }
}