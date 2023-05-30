package com.github.bkhablenko.terminal.formatter

import com.github.bkhablenko.game.Cell
import com.github.bkhablenko.game.Universe
import com.github.bkhablenko.terminal.geometry.GridDimension
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("TerminalUniverseFormatter")
class TerminalUniverseFormatterTest {

    companion object {
        private val ANSI_ESCAPE_SEQUENCE_REGEX = "\u001B\\[.*?m".toRegex()
    }

    @Test
    fun `should format Universe as String`() {
        val gliderSeed = setOf(
            Cell(3, 1),
            Cell(4, 2),
            Cell(2, 3),
            Cell(3, 3),
            Cell(4, 3),
        )
        val expectedString = """
            · · · · · · ·
            · · · ■ · · ·
            · · · · ■ · ·
            · · ■ ■ ■ · ·
            · · · · · · ·
        """.trimIndent()

        val universe = Universe(gliderSeed)
        val universeFormatter = TerminalUniverseFormatter(GridDimension(height = 5, width = 7))
        val coloredString = universeFormatter.format(universe)

        assertThat(coloredString.replace(ANSI_ESCAPE_SEQUENCE_REGEX, ""), equalTo(expectedString))
    }
}
