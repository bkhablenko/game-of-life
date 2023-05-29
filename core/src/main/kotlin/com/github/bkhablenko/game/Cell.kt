package com.github.bkhablenko.game

import com.github.bkhablenko.ranges.times

data class Cell(val x: Int, val y: Int) {

    companion object {
        private fun fromIntPair(pair: Pair<Int, Int>) = with(pair) { Cell(first, second) }
    }

    /**
     * @return cells that are horizontally, vertically, or diagonally adjacent to this one.
     */
    val neighbors: Set<Cell> by lazy {
        ((x - 1..x + 1) * (y - 1..y + 1) - (x to y))
            .map { fromIntPair(it) }
            .toSet()
    }
}
