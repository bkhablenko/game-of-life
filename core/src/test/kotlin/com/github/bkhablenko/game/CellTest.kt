package com.github.bkhablenko.game

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Cell")
class CellTest {

    @Test
    fun `should return its neighbors`() {
        val cell = Cell(67, 83)

        val expectedCells = arrayOf(
            Cell(66, 82),
            Cell(66, 83),
            Cell(66, 84),

            Cell(67, 82),
            Cell(67, 84),

            Cell(68, 82),
            Cell(68, 83),
            Cell(68, 84),
        )
        assertThat(cell.neighbors, containsInAnyOrder(*expectedCells))
    }
}
