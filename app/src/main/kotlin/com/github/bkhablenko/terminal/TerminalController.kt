package com.github.bkhablenko.terminal

import com.github.ajalt.mordant.animation.textAnimation
import com.github.ajalt.mordant.terminal.ExperimentalTerminalApi
import com.github.ajalt.mordant.terminal.Terminal
import com.github.bkhablenko.game.Cell
import com.github.bkhablenko.game.Universe
import com.github.bkhablenko.terminal.formatter.TerminalUniverseFormatter
import com.github.bkhablenko.terminal.formatter.UniverseFormatter
import com.github.bkhablenko.terminal.geometry.GridDimension
import java.time.Duration

class TerminalController(gridDimension: GridDimension = DEFAULT_GRID_DIMENSION) {

    companion object {
        private val DEFAULT_GRID_DIMENSION = GridDimension(height = 25, width = 25)
        private val DEFAULT_DELAY_BETWEEN_GENERATIONS = Duration.ofMillis(250L)
    }

    private val universeFormatter: UniverseFormatter = TerminalUniverseFormatter(gridDimension)

    @OptIn(ExperimentalTerminalApi::class)
    private val terminal = Terminal()

    fun animateEvolution(
        seed: Collection<Cell>,
        delayBetweenGenerations: Duration = DEFAULT_DELAY_BETWEEN_GENERATIONS,
    ) {
        var universe = Universe(seed.toSet())

        val animation = terminal.textAnimation<Unit> {
            universeFormatter
                .format(universe)
                .also {
                    universe = universe.tick()
                }
        }
        while (true) {
            animation.update(Unit)
            Thread.sleep(delayBetweenGenerations.toMillis())
        }
    }
}
