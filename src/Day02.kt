fun main() {
    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var verticalPosition = 0

        for (instruction in input) {
            val instructionParts = instruction.split(" ")
            if (instructionParts.size == 2) {
                val operation = instructionParts[0]
                val amount = instructionParts[1].toInt()

                when (operation.lowercase()) {
                    "forward" -> horizontalPosition += amount
                    "up" -> verticalPosition -= amount
                    "down" -> verticalPosition += amount
                }
            }
        }

        return horizontalPosition * verticalPosition
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        for (instruction in input) {
            val instructionParts = instruction.split(" ")
            if (instructionParts.size == 2) {
                val operation = instructionParts[0]
                val amount = instructionParts[1].toInt()

                when (operation.lowercase()) {
                    "forward" -> {
                        horizontalPosition += amount
                        depth += aim * amount
                    }
                    "up" -> aim -= amount
                    "down" -> aim += amount
                }
            }
        }

        return horizontalPosition * depth
    }

    // Check implementation using provided example
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    // Output challenge solution
    val input = readInput("Day02")
    printResult(1, part1(input))
    printResult(2, part2(input))
}
