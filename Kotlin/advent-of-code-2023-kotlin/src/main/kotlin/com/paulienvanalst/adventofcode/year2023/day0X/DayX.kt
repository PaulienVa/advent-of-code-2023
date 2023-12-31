package com.paulienvanalst.adventofcode.year2023.day0X

import com.paulienvanalst.adventofcode.year2023.utils.println
import com.paulienvanalst.adventofcode.year2023.utils.readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("0X","Day0X_test")
    check(part1(testInput) == 1)

    val input = readInput("0X", "Day0X")
    part1(input).println()
    part2(input).println()
}