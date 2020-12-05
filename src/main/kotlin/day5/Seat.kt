package day5

import java.lang.IllegalArgumentException

class Seat(input: String) {
    val allRows = IntRange(0,127)
    val row: Int
    val rowIdentifier: String

    val allColumns = IntRange(0,7)
    val column: Int
    val columnIdentifier: String

    val seatId: Int
        get() = (this.row * 8) + this.column

    init {
        assert(input.length == 10)
        rowIdentifier = input.substring(0,7)
        columnIdentifier = input.substring(7, 10)

        var currentRowRange = allRows
        for(char in rowIdentifier.toCharArray()) {
            val halfCount = currentRowRange.count() / 2
            currentRowRange = when(char) {
                'F' -> IntRange(currentRowRange.first, currentRowRange.first + halfCount - 1)
                'B' -> IntRange(currentRowRange.last - halfCount + 1, currentRowRange.last)
                else -> throw IllegalArgumentException( "Row values can only be F or B, found value $char")
            }
        }
        row = currentRowRange.first

        var currentColumnRange = allColumns
        for(char in columnIdentifier.toCharArray()) {
            val halfCount = currentColumnRange.count() / 2
            currentColumnRange = when(char) {
                'L' -> IntRange(currentColumnRange.first, currentColumnRange.first + halfCount - 1)
                'R' -> IntRange(currentColumnRange.last - halfCount + 1, currentColumnRange.last)
                else -> throw IllegalArgumentException( "Column values can only be L or R, found value $char")
            }
        }
        column = currentColumnRange.first
    }
}