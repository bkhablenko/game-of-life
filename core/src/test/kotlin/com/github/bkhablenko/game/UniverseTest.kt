package com.github.bkhablenko.game

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.io.FileNotFoundException

@DisplayName("Universe")
class UniverseTest {

    @DisplayName("given a stable pattern")
    @Nested
    inner class StablePatternsTest {

        @ParameterizedTest
        @ValueSource(strings = [
            // https://conwaylife.com/wiki/Beehive
            "beehive",
            // https://conwaylife.com/wiki/Block
            "block",
            // https://conwaylife.com/wiki/Loaf
            "loaf",
        ])
        fun `should evolve correctly`(pattern: String) {
            val seed = readCells("/patterns/stable/$pattern.txt")
            var universe = Universe(seed.toSet())

            repeat(3) {
                universe = universe.tick()
                assertThat(universe.liveCells, containsInAnyOrder(*seed))
            }
        }
    }

    @DisplayName("given an oscillator pattern")
    @Nested
    inner class OscillatorPatternsTest {

        @ParameterizedTest
        @ValueSource(strings = [
            // https://conwaylife.com/wiki/Blinker
            "blinker",
            // https://conwaylife.com/wiki/Toad
            "toad",
        ])
        fun `should evolve correctly`(pattern: String) {
            val seed = readCells("/patterns/oscillator/$pattern-0.txt")
            var universe = Universe(seed.toSet())

            val expectedCells = readCells("/patterns/oscillator/$pattern-1.txt")

            universe = universe.tick()
            assertThat(universe.liveCells, containsInAnyOrder(*expectedCells))
        }
    }

    @DisplayName("given a spaceship pattern")
    @Nested
    inner class SpaceshipPatternsTest {

        @ParameterizedTest
        @CsvSource(value = [
            // https://conwaylife.com/wiki/Glider
            "glider, 4"
        ])
        fun `should evolve correctly`(pattern: String, period: Int) {
            val seed = readCells("/patterns/spaceship/$pattern-0.txt").toSet()
            var universe = Universe(seed)

            (1..period).forEach { generation ->
                val expectedCells = readCells("/patterns/spaceship/$pattern-$generation.txt")

                universe = universe.tick()
                assertThat(universe.liveCells, containsInAnyOrder(*expectedCells))
            }
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
