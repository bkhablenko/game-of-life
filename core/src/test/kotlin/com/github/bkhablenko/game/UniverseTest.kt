package com.github.bkhablenko.game

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

@DisplayName("Universe")
class UniverseTest {

    @Test
    fun `should evolve correctly`() {
        val seed = readCells("/fixtures/glider-0.txt").toSet()
        var universe = Universe(seed)

        (1..4).forEach { generation ->
            val expectedCells = readCells("/fixtures/glider-$generation.txt")

            universe = universe.tick()
            assertThat(universe.liveCells, containsInAnyOrder(*expectedCells))
        }
    }

    private fun readCells(classPathResource: String): Array<Cell> {
        val liveCells = mutableSetOf<Cell>()
        readLines(classPathResource).forEachIndexed { y, line ->
            liveCells += line
                .indicesOf { it != '.' }
                .map { x -> Cell(x, y) }
        }
        return liveCells.toTypedArray()
    }

    private fun readLines(classPathResource: String): List<String> {
        val inputStream = javaClass.getResourceAsStream(classPathResource) ?: throw FileNotFoundException()
        return inputStream.bufferedReader().readLines()
    }

    private fun String.indicesOf(predicate: (Char) -> Boolean) = indices.filter { index -> predicate(this[index]) }
}
