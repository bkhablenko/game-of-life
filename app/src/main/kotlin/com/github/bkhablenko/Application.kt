package com.github.bkhablenko

import com.github.bkhablenko.game.Cell
import com.github.bkhablenko.terminal.TerminalController

private val GLIDER_SEED =
    setOf(
        Cell(1, 0),
        Cell(2, 1),
        Cell(0, 2),
        Cell(1, 2),
        Cell(2, 2),
    )

fun main() {
    TerminalController().animateEvolution(GLIDER_SEED)
}
