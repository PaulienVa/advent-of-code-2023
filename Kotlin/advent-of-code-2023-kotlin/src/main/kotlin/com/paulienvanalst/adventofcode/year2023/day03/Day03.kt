package com.paulienvanalst.adventofcode.year2023.day03

import com.paulienvanalst.adventofcode.year2023.utils.println
import com.paulienvanalst.adventofcode.year2023.utils.readInput


fun main() {
    fun part1(input: List<String>): Int {
        val parts = input.findPartsWithAdjacentSymbol()
        return parts.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("03", "Day03_test")
    check(part1(testInput) == 4361)

    val input = readInput("03", "Day03")
    part1(input).println()
    part2(input).println()
}

fun List<String>.findPartsWithAdjacentSymbol(): List<Int> {
    return this.mapIndexed { index, s -> index to findPotentialParts(s) }
        .map { this.withAdjactentSymbol(it).toList() }.flatten()
}

fun List<String>.withAdjactentSymbol(indexToPotentialPart: Pair<Int, Sequence<Pair<Int, Coordinates>>>): Sequence<Int> {
    val (currentLine, potentialParts) = indexToPotentialPart
    return potentialParts.map { it.first to it.second }.filter { it.hasAdjacentSymbol(this, currentLine) }
        .map { it.first }
}

private fun Pair<Int, Coordinates>.hasAdjacentSymbol(lines: List<String>, currentLine: Int): Boolean {
    val start = if (this.second.startX != 0) this.second.startX - 1 else 0
    val end = if (this.second.endX  == (lines[currentLine].length - 1)) this.second.endX else this.second.endX + 1
    val foundInLine = if (this.second.startX == 0) {
        val nextCell = lines[currentLine].get(this.second.endX + 1)
        !nextCell.isDigit() && nextCell != '.'
    } else if (this.second.endX == lines[currentLine].length - 1) {
        val previousCell = lines[currentLine].get(this.second.startX - 1)
        !previousCell.isDigit() && previousCell != '.'
    } else  {
        val previousCell = lines[currentLine].get(this.second.startX - 1)
        val nextCell = lines[currentLine].get(this.second.endX + 1)
        !previousCell.isDigit() && previousCell != '.' || !nextCell.isDigit() && nextCell != '.'
    }
    val foundAround = when (currentLine) {
        0 -> {
            lines[currentLine + 1].substring(start..end).find { !it.isDigit() && it != '.' } != null
        }
        lines.size - 1 -> {
            lines[currentLine - 1].substring(start..end).find { !it.isDigit() && it != '.' } != null
        }
        else -> {
            val lineAbove = lines[currentLine - 1].substring(start..end).find { !it.isDigit() && it != '.' } != null
            val lineUnderNeath = lines[currentLine + 1].substring(start..end).find { !it.isDigit() && it != '.' } != null
            lineAbove || lineUnderNeath
        }
    }

    return foundInLine || foundAround
}


fun findPotentialParts(line: String): Sequence<Pair<Int, Coordinates>> {
    val partsRegex = "[0-9]+".toRegex()
    return partsRegex.findAll(line).flatMap { it.groups }.filterNotNull()
        .map { it.value.toInt() to Coordinates(it.range.first, it.range.last) }
}

data class Coordinates(val startX: Int, val endX: Int)