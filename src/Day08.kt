import kotlin.math.*
import kotlin.streams.toList

fun main() {
    fun part1(input: List<String>): Int {
        var outputSum = 0
        for (instruction in input) {
            val patternAndInputs = instruction.split(" | ")

            outputSum += patternAndInputs[1].split(" ").count { inputPattern ->
                intArrayOf(2, 3, 4, 7).contains(inputPattern.length)
            }
        }

        return outputSum
    }

    fun part2(input: List<String>): Int {
        // TODO: Was too tired to do more at that day, finish later on.
        return input.size
    }

    // Check implementation using provided example
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
//    check(part2(testInput) == 168)

    // Output challenge solution
    val input = readInput("Day08")
    printResult(1, part1(input))
//    printResult(2, part2(input))
}
