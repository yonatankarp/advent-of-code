package com.yonatankarp.adventofcode.aoc2022.day11

typealias MonkeyId = Int
typealias WorryLevel = Long

class Day11(input: List<String>) {

    private val monkeys = input
        .filter { it.isNotBlank() }
        .associate {
            val monkey = Monkey.parse(it)
            monkey.id to monkey
        }

    private data class Monkey(
        val id: MonkeyId,
        val items: MutableList<WorryLevel>,
        val operation: Pair<String, String>,
        val divider: Int,
        val trueId: MonkeyId,
        val falseId: MonkeyId
    ) {
        var numberOfInteractions: Int = 0
            private set

        fun interact(reliefOp: (Long) -> Long): Pair<MonkeyId, WorryLevel> {
            numberOfInteractions++

            val item = items.removeFirst()
            val value =
                if (operation.second == "old") item
                else operation.second.toLong()

            return when (operation.first) {
                "+" -> item + value
                "*" -> item * value
                else -> throw IllegalArgumentException("Unknown operation: ${operation.first}")
            }.let {
                val worry = reliefOp(it)

                val targetId = when {
                    worry % divider == 0L -> trueId
                    else -> falseId
                }

                targetId to worry
            }
        }

        companion object {
            @Suppress("RegExpRepeatedSpace")
            private val regex = """
                Monkey (\d+):
                  Starting items: ((\d+,? ?)+)
                  Operation: new = old (.) (\w+)
                  Test: divisible by (\d+)
                    If true: throw to monkey (\d+)
                    If false: throw to monkey (\d+)
            """.trimIndent().toRegex()

            fun parse(input: String) =
                regex
                    .find(input)!!
                    .groupValues
                    .let { groups ->
                        Monkey(
                            id = groups[1].toInt(),
                            items = groups[2].split(", ").map { it.toLong() }.toMutableList(),
                            operation = Pair(groups[4], groups[5]),
                            divider = groups[6].toInt(),
                            trueId = groups[7].toInt(),
                            falseId = groups[8].toInt()
                        )
                    }
        }
    }

    private fun simulate(rounds: Int, reliefOp: (Long) -> Long) =
        repeat(rounds) {
            monkeys.values.forEach { monkey ->
                while (monkey.items.isNotEmpty()) {
                    val (toId, item) = monkey.interact(reliefOp)
                    monkeys[toId]!!.items.add(item)
                }
            }
        }

    private fun calculateMonkeyBusiness() =
        monkeys
            .values
            .sortedByDescending { it.numberOfInteractions }
            .take(2)
            .fold(1L) { acc, monkey -> acc * monkey.numberOfInteractions }

    fun solvePart1(): Long {
        simulate(20) { it / 3 }
        return calculateMonkeyBusiness()
    }

    fun solvePart2(): Long {
        // all the divisors used for tests are primes. therefore, we can use the
        // LCM (least common denominator) as the modulo for the numbers, so they
        // won't explode before round 10k
        val lcd = monkeys
            .map { it.value.divider }
            .reduce(Int::times)
        simulate(10_000) { it % lcd }
        return calculateMonkeyBusiness()
    }
}
