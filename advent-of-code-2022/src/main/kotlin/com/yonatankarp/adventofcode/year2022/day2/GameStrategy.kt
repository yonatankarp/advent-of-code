package com.yonatankarp.adventofcode.year2022.day2

import com.yonatankarp.adventofcode.year2022.day2.GameChoice.PAPER
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.ROCK
import com.yonatankarp.adventofcode.year2022.day2.GameChoice.SCISSORS
import com.yonatankarp.adventofcode.year2022.day2.RoundEndStrategy.DRAW
import com.yonatankarp.adventofcode.year2022.day2.RoundEndStrategy.LOSE
import com.yonatankarp.adventofcode.year2022.day2.RoundEndStrategy.WIN

abstract class GameStrategy {
    abstract fun roundResult(roundEndStrategy: RoundEndStrategy): Int

    protected fun win(choice: GameChoice) = GAME_WIN_SCORE + choice.score
    protected fun draw(choice: GameChoice) = GAME_TIE_SCORE + choice.score
    protected fun lose(choice: GameChoice) = GAME_LOSE_SCORE + choice.score

    companion object {
        private const val GAME_WIN_SCORE = 6
        private const val GAME_TIE_SCORE = 3
        private const val GAME_LOSE_SCORE = 0
    }
}

class RockStrategy : GameStrategy() {
    override fun roundResult(roundEndStrategy: RoundEndStrategy) =
        when (roundEndStrategy) {
            WIN -> win(PAPER)
            LOSE -> lose(SCISSORS)
            DRAW -> draw(ROCK)
        }
}

class PaperStrategy : GameStrategy() {
    override fun roundResult(roundEndStrategy: RoundEndStrategy) =
        when (roundEndStrategy) {
            WIN -> win(SCISSORS)
            LOSE -> lose(ROCK)
            DRAW -> draw(PAPER)
        }
}

class ScissorsStrategy : GameStrategy() {
    override fun roundResult(roundEndStrategy: RoundEndStrategy) =
        when (roundEndStrategy) {
            WIN -> win(ROCK)
            LOSE -> lose(PAPER)
            DRAW -> draw(SCISSORS)
        }
}

