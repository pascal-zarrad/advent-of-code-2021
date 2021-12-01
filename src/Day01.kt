package day1

import printResult
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var increments = 0;

        if (input.size < 2) {
            return increments;
        }

        for (i in 1 until input.size) {
            if (input[i].toInt() > input[i- 1].toInt()) {
                increments++;
            }
        }

        return increments;
    }

    fun part2(input: List<String>): Int {
        var increments = 0;

        if (input.size < 2) {
            return increments;
        }

        for (i in 1 until input.size) {
            if (input[i].toInt() > input[i- 1].toInt()) {
                increments++;
            }
        }

        return increments;
    }

    // Check implementation using provided example
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    // Output challenge solution
    val input = readInput("Day01")
    printResult(1, part1(input))
    printResult(2, part2(input))
}
