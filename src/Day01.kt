fun main() {
    fun part1(input: List<String>): Int {
        var increments = 0

        for (i in 1 until input.size) {
            if (input[i].toInt() > input[i - 1].toInt()) {
                increments++
            }
        }

        return increments
    }

    fun part2(input: List<String>): Int {
        var increments = 0

        var previousRun = 0
        for (i in input.indices) {
            if (i + 2 < input.size) {
                var currentRun = 0
                // could also do "currentRun += input[i].toInt() + input[i + 1].toInt() + input[i + 2].toInt()" instead
                for (j in i..(i + 2)) {
                    currentRun += input[j].toInt()
                }

                if (i > 0 && currentRun > previousRun) {
                    increments++
                }

                previousRun = currentRun
            }
        }

        return increments
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
