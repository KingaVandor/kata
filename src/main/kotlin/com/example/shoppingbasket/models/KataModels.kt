package com.example.shoppingbasket.models

enum class Hand {
    TIE,
    HIGH,
    PAIR,
    TWO_PAIRS,
    THREES,
    STRAIGHT,
    FLUSH,
    FULL,
    FOURS,
    STRAIGHT_FLUSH,


}

enum class Player() {
    Black,
    White
}

enum class Rank(val nominal: Int, val trueValue: Int) {
    TWO(2, 2),
    THREE(3, 3),
    FOUR(4, 4),
    FIVE(5, 5),
    SIX(6, 6),
    SEVEN(7, 7),
    EIGHT(8, 8),
    NINE(9, 9),
    TEN(10, 10),
    JACK(10, 11),
    QUEEN(10, 12),
    KING(10, 13),
    ACE(10, 14),
}

data class Score(
    val hand: Hand,
    val rank: Rank? = null,
    val value: Int? = null,
    val player: Player? = null,
)



enum class Coins(val value: Int) {
    QUARTERS(25),
    DIMES(10),
    NICKELS(5),
    PENNIES(1),

}

enum class WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

enum class Month(val days: Int) {
    JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31), AUGUST(31), SEPT(30), OCT(31), NOV(30), DEC(
        31
    )
}

enum class Door {
    CLOSED, OPEN,
}

enum class Direction(val row: Int, val col: Int) {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1)
}

enum class Neighbours(val row: Int, val col: Int) {
    TOP_LEFT(1, -1), TOP(1, 0), TOP_RIGHT(1, 1),
    LEFT(0, -1), RIGHT(0, 1),
    BOTTOM_LEFT(-1, -1), BOTTOM(-1, 0), BOTTOM_RIGHT(-1, 1),

}

enum class TennisPlayer {
    A, B
}

enum class TennisScore {
    LOVE, FIFTEEN, THIRTY, FORTY, ADVANTAGE, DEUCE, GAME
}

