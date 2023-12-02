package com.paulienvanalst.adventofcode.year2023.day02

import com.paulienvanalst.adventofcode.year2023.utils.println
import com.paulienvanalst.adventofcode.year2023.utils.readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { possibleGames(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { powerOfMinimalCubes(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("02","Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("02", "Day02")
    part1(input).println()
    check(part2(testInput) == 2286)
    part2(input).println()
}

private const val MAX_GREEN = 13
private const val MAX_RED = 12
private const val MAX_BLUE = 14

private val greenRegex = "(\\d)+(?=\\sgreen)".toRegex()
private val blueRegex = "(\\d)+(?=\\sblue)".toRegex()
private val redRegex = "(\\d)+(?=\\sred)".toRegex()

fun powerOfMinimalCubes(line: String): Int {
    val minimalGreen = greenRegex.findAll(line).flatMap { it.groupValues }.map { it.toInt() }.max()
    val minimalRed = redRegex.findAll(line).flatMap { it.groupValues }.map { it.toInt() }.max()
    val minimalBlue = blueRegex.findAll(line).flatMap { it.groupValues }.map { it.toInt() }.max()
    return minimalGreen * minimalRed * minimalBlue
}

fun possibleGames(line: String): Int {
    val gameId = "(?!Game)(\\d)+".toRegex().find(line)?.groupValues?.get(0)?.toInt()
    val nrGreenImpossible = greenRegex.findAll(line).flatMap { it.groupValues }.map { it.toInt() }.count { it > MAX_GREEN }
    val nrRedImpossible = redRegex.findAll(line).flatMap { it.groupValues }.map { it.toInt() }.count { it > MAX_RED }
    val nrBlueImpossible = blueRegex.findAll(line).flatMap { it.groupValues }.map { it.toInt() }.count { it > MAX_BLUE }
    val isGamePossible = nrGreenImpossible == 0 && nrBlueImpossible == 0 && nrRedImpossible == 0
    return if (isGamePossible) {
//        println("Game is possible $gameId")
        gameId ?: 0
    } else {
        0
    }
}