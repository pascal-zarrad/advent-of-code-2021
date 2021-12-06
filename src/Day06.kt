fun main() {
    data class LanternFish(var timer: Byte, val amount: Long)

    fun simulate(inputCsv: String, days: Int): Long {
        val lanternFishList = inputCsv.split(",")
            .map { timer ->
                timer.toByte()
            }.map { lanternFishTimer ->
                LanternFish(lanternFishTimer, 1)
            }.toMutableList()

        for (i in 0 until days) {
            var newFishCount = 0L
            lanternFishList.forEach { lanternFish ->
                when (lanternFish.timer) {
                    0.toByte() -> {
                        lanternFish.timer = 6
                        newFishCount += lanternFish.amount
                    }
                    else -> {
                        lanternFish.timer = lanternFish.timer.dec()
                    }
                }
            }

            if (newFishCount > 0) {
                lanternFishList.add(LanternFish(8, newFishCount))
            }
        }

        return lanternFishList
            .stream()
            .mapToLong { lanternFish -> lanternFish.amount }
            .sum()
    }

    fun part1(input: List<String>): Long {
        return simulate(input[0], 80)
    }

    fun part2(input: List<String>): Long {
        return simulate(input[0], 256)
    }

    // Check implementation using provided example
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539L)

    // Output challenge solution
    val input = readInput("Day06")
    printResult(1, part1(input))
    printResult(2, part2(input))
}
