import kotlin.collections.ArrayList
import kotlin.streams.toList

// This solution is way more complex than it has to be and might have been 10x cleaner.
// Perfect example on how not to solve the puzzle in a clean manner :)
fun main() {
    fun generateCalloutList(input: List<String>): List<Int> {
        return input[0].split(",")
            .stream()
            .mapToInt { value -> value.toInt() }
            .toList()
    }

    fun collectBoards(input: List<String>): ArrayList<ArrayList<LinkedHashMap<Int, Boolean>>> {
        val boards = ArrayList<ArrayList<LinkedHashMap<Int, Boolean>>>()
        boards.add(ArrayList())
        var currentBoard = 0
        var currentRow = 0
        for (i in 1 until input.size) {
            if (input[i] == "") {
                continue
            }

            if (currentRow == 5) {
                currentBoard++
                currentRow = 0
                boards.add(ArrayList())
            }

            boards[currentBoard].add(LinkedHashMap())
            for (cell in input[i].split(" ")) {
                val cellWithoutSpaces = cell.trim()

                if (cellWithoutSpaces != "") {
                    val cellNumeric = cell.toInt()

                    boards[currentBoard]
                    boards[currentBoard][currentRow][cellNumeric] = false
                }
            }

            currentRow++
        }

        return boards
    }

    fun markCalledOutField(
        boards: ArrayList<ArrayList<LinkedHashMap<Int, Boolean>>>,
        i: Int,
        calloutNumbers: List<Int>
    ) {
        boards.forEach { board ->
            board.forEach { row ->
                row.forEach { (cell, _) ->
                    if (cell == calloutNumbers[i]) {
                        row[cell] = true
                    }
                }
            }
        }
    }

    fun checkForWin(
        board: ArrayList<LinkedHashMap<Int, Boolean>>,
    ): Boolean {
        var hitsInARow: Int

        // Horizontal
        for (row in board) {
            hitsInARow = 0
            for ((_, marked) in row) {
                if (marked) {
                    ++hitsInARow
                }
            }

            if (hitsInARow == 5) {
                return true
            }
        }

        // Vertical
        for (columnNumber in 0 until 5) {
            hitsInARow = 0
            for (row in board) {
                if (row[row.keys.elementAt(columnNumber)] == true) {
                    ++hitsInARow
                }
            }

            if (hitsInARow == 5) {
                return true
            }
        }

        return false
    }

    fun calculateUnmarkedSum(
        board: ArrayList<LinkedHashMap<Int, Boolean>>,
        calledOutNumber: Int,
    ): Int {
        var unmarkedFieldsSum = 0

        board.forEach { row ->
            row.forEach { (cell, marked) ->
                if (!marked) {
                    unmarkedFieldsSum += cell
                }
            }
        }

        return unmarkedFieldsSum * calledOutNumber
    }

    fun part1(input: List<String>): Int {
        val calloutNumbers = generateCalloutList(input)

        // Collect boards
        val boards = collectBoards(input)

        // Play
        for (i in calloutNumbers.indices) {
            markCalledOutField(boards, i, calloutNumbers)

            for (board in boards) {
                if (checkForWin(board)) {
                    return calculateUnmarkedSum(board, calloutNumbers[i])

                }
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        val calloutNumbers = generateCalloutList(input)

        // Collect boards
        val boards = collectBoards(input)

        // Play
        for (i in calloutNumbers.indices) {
            markCalledOutField(boards, i, calloutNumbers)

            var hadWinner: Boolean
            do {
                hadWinner = false
                var lastWinner: ArrayList<LinkedHashMap<Int, Boolean>> = ArrayList()
                for (board in boards) {
                    if (checkForWin(board)) {
                        if (boards.size == 1) {
                            return calculateUnmarkedSum(board, calloutNumbers[i])
                        }

                        hadWinner = true
                        lastWinner = board
                        break
                    }
                }
                if (hadWinner) {
                    boards.remove(lastWinner)
                }
            } while (hadWinner)
        }

        return 0
    }

    // Check implementation using provided example
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    // Output challenge solution
    val input = readInput("Day04")
    printResult(1, part1(input))
    printResult(2, part2(input))
}
