import kotlin.math.*
import kotlin.streams.toList

fun main() {
    fun getCrabPositions(input: String): List<Int> {
        return input.split(",")
            .stream()
            .mapToInt { value -> value.toInt() }
            .sorted()
            .toList()
    }

    fun getCrabPositionRange(crabPositions: List<Int>): IntRange {
        val lowestPosition = crabPositions.minOf { value -> value }
        val highestPosition = crabPositions.maxOf { value -> value }

        return lowestPosition..highestPosition
    }

    fun part1(input: List<String>): Int {
        val crabPositions = getCrabPositions(input[0])

        return getCrabPositionRange(crabPositions).minOf { i ->
            crabPositions.sumOf { crabPosition ->
                abs(crabPosition - i)
            }
        }
    }

    fun part2(input: List<String>): Int {
        val crabPositions = getCrabPositions(input[0])

        return getCrabPositionRange(crabPositions).minOf { i ->
            crabPositions.sumOf { crabPosition ->
                abs(crabPosition - i) * (abs(crabPosition - i) + 1) / 2
            }
        }
    }

    // Check implementation using provided example
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    // Output challenge solution
    val input = readInput("Day07")
    printResult(1, part1(input))
    printResult(2, part2(input))
}
