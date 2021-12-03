import kotlin.math.ceil
import kotlin.streams.toList

fun main() {
    fun getMostCommonBitAtPosition(inputList: List<Int>, position: Int): Int {
        var oneCount = 0
        for (report in inputList) {
            val value = (report shr position) and 1
            if (value > 0) {
                oneCount++
            }
        }


        return if (oneCount >= ceil(inputList.size / 2.0)) 1 else 0
    }

    fun part1(input: List<String>): Int {
        var gamma = 0
        var epsilon = 0

        val inputAsInt = input.stream().mapToInt { value -> value.toInt(2) }.toList()

        for (i in (input[0].length - 1) downTo 0) {
            val mostCommonBit = getMostCommonBitAtPosition(inputAsInt, i)

            gamma = (gamma shl 1) + mostCommonBit
            epsilon = (epsilon shl 1) + (mostCommonBit xor 1)
        }

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val oxygen: Int
        val co2scrubber: Int

        val inputAsInt = input.stream().mapToInt { value -> value.toInt(2) }.toList()
        var oxygenCandidates = ArrayList(inputAsInt) as List<Int>
        var co2Candidates = ArrayList(inputAsInt) as List<Int>

        // Get oxygen value
        var currentPosition = input[0].length - 1
        do {
            val mostCommonBit = getMostCommonBitAtPosition(oxygenCandidates, currentPosition)

            oxygenCandidates = oxygenCandidates.stream()
                .filter { value -> (value shr currentPosition) and 1 == mostCommonBit }
                .toList()
            currentPosition--
        } while (oxygenCandidates.size > 1 && currentPosition >= 0)

        // Get CO2 value
        currentPosition = input[0].length - 1
        do {
            val mostCommonBit = getMostCommonBitAtPosition(co2Candidates, currentPosition)

            co2Candidates = co2Candidates.stream()
                .filter { value -> (value shr currentPosition) and 1 == (mostCommonBit xor 1) }
                .toList()
            currentPosition--
        } while (co2Candidates.size > 1 && currentPosition >= 0)

        oxygen = oxygenCandidates[0]
        co2scrubber = co2Candidates[0]

        return oxygen * co2scrubber
    }

    // Check implementation using provided example
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    // Output challenge solution
    val input = readInput("Day03")
    printResult(1, part1(input))
    printResult(2, part2(input))
}
