package com.yonatankarp.adventofcode.aoc2022.day06

import com.yonatankarp.adventofcode.utils.resourceAsString
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource


@Suppress("Junit5MalformedParameterized")
class Day06Test {

    @Nested
    inner class Part1 {
        @ParameterizedTest
        @ArgumentsSource(Part1ExampleInputArgumentsProvider::class)
        fun `Part 1 - Example`(testCase: TestCase) {
            val day06 = Day06(testCase.input)
            assertEquals(testCase.output, day06.solvePart1())
        }

        @Test
        fun `Part 1 - Real Input`() {
            val day06 = Day06(resourceAsString("2022/day06.txt"))
            assertEquals(1794, day06.solvePart1())
        }
    }

    @Nested
    inner class Part2 {
        @ParameterizedTest
        @ArgumentsSource(Part2ExampleInputArgumentsProvider::class)
        fun `Part 2 - Example`(testCase: TestCase) {
            val day06 = Day06(testCase.input)
            assertEquals(testCase.output, day06.solvePart2())
        }

        @Test
        fun `Part 2 - Real Input`() {
            val day06 = Day06(resourceAsString("2022/day06.txt"))
            assertEquals(2851, day06.solvePart2())
        }
    }

    data class TestCase(val input: String, val output: Int)

    internal class Part1ExampleInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> =
            Stream.of(
                Arguments.of(TestCase(input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb", output = 7)),
                Arguments.of(TestCase(input = "bvwbjplbgvbhsrlpgdmjqwftvncz", output = 5)),
                Arguments.of(TestCase(input = "nppdvjthqldpwncqszvftbrmjlhg", output = 6)),
                Arguments.of(TestCase(input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", output = 10)),
                Arguments.of(TestCase(input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", output = 11)),
            )
    }

    internal class Part2ExampleInputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> =
            Stream.of(
                Arguments.of(TestCase(input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb", output = 19)),
                Arguments.of(TestCase(input = "bvwbjplbgvbhsrlpgdmjqwftvncz", output = 23)),
                Arguments.of(TestCase(input = "nppdvjthqldpwncqszvftbrmjlhg", output = 23)),
                Arguments.of(TestCase(input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", output = 29)),
                Arguments.of(TestCase(input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", output = 26)),
            )
    }
}
