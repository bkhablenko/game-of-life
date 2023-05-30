package com.github.bkhablenko.terminal.formatter

import com.github.ajalt.mordant.rendering.TextColors.gray
import com.github.ajalt.mordant.rendering.TextColors.white
import com.github.ajalt.mordant.rendering.TextStyles.bold
import com.github.bkhablenko.game.Universe
import com.github.bkhablenko.terminal.geometry.GridDimension

class TerminalUniverseFormatter(private val gridDimension: GridDimension) : UniverseFormatter {

    companion object {
        // https://www.compart.com/en/unicode/U+25A0
        private const val BLACK_SQUARE = "\u25A0"

        // https://www.compart.com/en/unicode/U+00B7
        private const val MIDDLE_DOT = "\u00B7"
    }

    override fun format(universe: Universe): String {
        val grid = createGrid()

        universe.liveCells.forEach { (x, y) ->
            if (y in grid.indices && x in grid[y].indices) {
                grid[y][x] = true
            }
        }

        return grid.joinToString(separator = "\n") { formatRow(it) }
    }

    private fun createGrid(): Array<BooleanArray> =
        with(gridDimension) {
            Array(height) { BooleanArray(width) }
        }

    private fun formatRow(row: BooleanArray): String =
        row.joinToString(separator = " ") { isLive ->
            if (isLive) {
                bold(white(BLACK_SQUARE))
            } else {
                gray(MIDDLE_DOT)
            }
        }
}
