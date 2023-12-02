package com.paulienvanalst.adventofcode.year2023.day01

import com.paulienvanalst.adventofcode.year2023.utils.println
import com.paulienvanalst.adventofcode.year2023.utils.readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { "${it.first { ch -> ch.isDigit() }}${it.last { ch -> ch.isDigit() }}".toInt() }
    }

    fun part2(input: List<String>): Long {
        val findCalibration = input.map { it.findCalibration() }
        return findCalibration.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("01","Day01_test")
    part1(testInput).println()
    check(part1(testInput) == 142)

    val input = readInput("01","Day01")
    part1(input).println()

    val testInput2 = readInput("01","Day01_test2")
    part2(testInput2).println()
    check(part2(testInput2) == 281L)
    part2(input).println() //55701
}

fun String.findCalibration(): Long {
    val litteralDigits = mapOf(
        "one" to 1, "two" to 2, "three" to 3, "four" to 4,
        "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
    val newString = this.windowed(5, 1, true).map {
        val find = litteralDigits.keys.find { k -> it.contains(k) }
        if (find != null) {
            return@map it.replace(find, litteralDigits[find].toString())
        } else {
            return@map it
        }
    }.joinToString()
    return "${newString.first { ch -> ch.isDigit() }}${newString.last { ch -> ch.isDigit() }}".toLong()

}