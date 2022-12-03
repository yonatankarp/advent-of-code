package com.yonatankarp.adventofcode.year2022.day2

enum class GameChoice(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

enum class RoundEndStrategy {
    WIN,
    LOSE,
    DRAW
}
