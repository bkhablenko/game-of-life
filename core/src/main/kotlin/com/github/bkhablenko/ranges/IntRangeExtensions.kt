package com.github.bkhablenko.ranges

/**
 * @return the Cartesian product of two integer ranges [this] and [other].
 */
operator fun IntRange.times(other: IntRange) = flatMap { first -> other.map { second -> first to second } }
