package com.github.bkhablenko.game

class Universe(val liveCells: Set<Cell>) {

    fun tick(): Universe {
        val deadCells = mutableSetOf<Cell>()

        val nextGeneration = mutableSetOf<Cell>()
        liveCells.forEach { cell ->
            val neighbors = cell.neighbors

            // Any live cell with two or three live neighbors lives on to the next generation.
            countLiveCells(neighbors).let {
                if (it == 2 || it == 3) {
                    nextGeneration += cell
                }
            }

            deadCells += neighbors.filter { !it.isLive }
        }

        // Any dead cell with exactly three live neighbours becomes a live cell, as if by
        // reproduction.
        nextGeneration += deadCells.filter { countLiveCells(it.neighbors) == 3 }

        return Universe(nextGeneration)
    }

    private fun countLiveCells(cells: Set<Cell>) = cells.count { it.isLive }

    private val Cell.isLive: Boolean get() = this in liveCells
}
