package com.github.bkhablenko.terminal.formatter

import com.github.bkhablenko.game.Universe

interface UniverseFormatter {

    fun format(universe: Universe): String
}
