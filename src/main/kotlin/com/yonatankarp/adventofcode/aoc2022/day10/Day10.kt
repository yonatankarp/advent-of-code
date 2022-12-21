package com.yonatankarp.adventofcode.aoc2022.day10

class Day10(private val input: List<String>) {

    private fun Int.getCycleSignal(register: Int) =
        if (this % 40 == 20) this * register
        else 0

    fun solvePart1(): Int {
        var register = 1
        var cycle = 1
        return input
            .filter { it.isNotBlank() }
            .map { instruction ->
                cycle++

                var signal = cycle.getCycleSignal(register)

                if (instruction.startsWith("addx")) {
                    // addx operation takes 2 cycles
                    cycle++
                    val (_, value) = instruction.split(" ")
                    register += value.toInt()
                    signal += cycle.getCycleSignal(register)
                }

                signal
            }
            .sumOf { it }
    }

    private fun getCRTCharacter(index: Int, register: Int): String {
        val adjustedIndex = index % 40
        return when (adjustedIndex) {
            in register - 1..register + 1 -> "#"
            else -> "."
        }.let {
            // If that's the 1st character of the new line break line
            if (adjustedIndex == 0) System.lineSeparator() + it
            else it
        }
    }

    fun solvePart2(): String {
        val crt = StringBuilder()
        var register = 1
        var cycle = 1
        input.filter { it.isNotBlank() }
            .forEach { instruction ->
                crt.append(getCRTCharacter(cycle - 1, register))
                cycle++

                if (instruction.startsWith("addx")) {
                    crt.append(getCRTCharacter(cycle - 1, register))
                    cycle++
                    register += instruction.substringAfter("addx ").toInt()
                }
            }

        return crt
            .toString()
            .removePrefix(System.lineSeparator()) // Remove the 1st break line
    }
}
