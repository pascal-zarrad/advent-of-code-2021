fun main() {
    data class Vector2D(val x: Int, val y: Int)

    fun prepareHydrothermalVentInput(input: List<String>): ArrayList<Pair<Vector2D, Vector2D>> {
        val lines = ArrayList<Pair<Vector2D, Vector2D>>()

        // Build List with points
        input.forEach { value ->
            val line = value.split(" -> ")

            val startPoint = line[0].split(",")
            val startPointXY = Vector2D(startPoint[0].toInt(), startPoint[1].toInt())
            val endPoint = line[1].split(",")
            val endPointXY = Vector2D(endPoint[0].toInt(), endPoint[1].toInt())

            lines.add(Pair(startPointXY, endPointXY))
        }
        return lines
    }

    fun drawHorizontalLines(
        linePair: Pair<Vector2D, Vector2D>,
        intersections: HashMap<Int, HashMap<Int, Int>>
    ): Boolean {
        // Horizontal lines
        if (linePair.first.y == linePair.second.y) {
            var firstX = linePair.first.x
            var secondX = linePair.second.x
            if (secondX < firstX) {
                val swap = firstX
                firstX = secondX
                secondX = swap
            }
            for (i in firstX..secondX) {
                val horizontalList = intersections.getOrDefault(linePair.first.y, HashMap())
                horizontalList[i] = horizontalList.getOrDefault(i, 0) + 1
                intersections[linePair.first.y] = horizontalList
            }

            return true
        }

        return false
    }

    fun drawVerticalLines(
        linePair: Pair<Vector2D, Vector2D>,
        intersections: HashMap<Int, HashMap<Int, Int>>
    ): Boolean {
        if (linePair.first.x == linePair.second.x) {
            var firstY = linePair.first.y
            var secondY = linePair.second.y
            if (secondY < firstY) {
                val swap = firstY
                firstY = secondY
                secondY = swap
            }
            for (i in firstY..secondY) {
                val horizontalList = intersections.getOrDefault(i, HashMap())
                horizontalList[linePair.first.x] = horizontalList.getOrDefault(linePair.first.x, 0) + 1
                intersections[i] = horizontalList
            }

            return true
        }

        return false
    }

    fun drawDiagonalLines(
        linePair: Pair<Vector2D, Vector2D>,
        intersections: HashMap<Int, HashMap<Int, Int>>
    ) {
        val startPoint = linePair.first
        val endPoint = linePair.second
        var yChange = startPoint.y
        if (startPoint.x < endPoint.x) {
            for (i in startPoint.x..endPoint.x) {
                val horizontalList = intersections.getOrDefault(yChange, HashMap())
                horizontalList[i] = horizontalList.getOrDefault(i, 0) + 1
                intersections[yChange] = horizontalList
                if (endPoint.y > startPoint.y) ++yChange else --yChange
            }
        } else {
            for (i in startPoint.x downTo endPoint.x) {
                val horizontalList = intersections.getOrDefault(yChange, HashMap())
                horizontalList[i] = horizontalList.getOrDefault(i, 0) + 1
                intersections[yChange] = horizontalList
                if (endPoint.y > startPoint.y) ++yChange else --yChange
            }
        }
    }

    fun calculateIntersections(intersections: HashMap<Int, HashMap<Int, Int>>): Int {
        var multipleIntersection = 0
        intersections.forEach { (_, verticalMap) ->
            verticalMap.forEach { (_, count) ->
                if (count >= 2) {
                    multipleIntersection++
                }
            }
        }

        return multipleIntersection
    }

    fun part1(input: List<String>): Int {
        val lines = prepareHydrothermalVentInput(input)

        // Build lines and intersections
        val intersections = HashMap<Int, HashMap<Int, Int>>()
        lines.stream().forEach { value ->
            // Horizontal lines
            drawHorizontalLines(value, intersections)

            // Vertical lines
            drawVerticalLines(value, intersections)
        }

        return calculateIntersections(intersections)
    }

    fun part2(input: List<String>): Int {
        val lines = prepareHydrothermalVentInput(input)

        // Build lines and intersections
        val intersections = HashMap<Int, HashMap<Int, Int>>()
        lines.stream().forEach { value ->
            val diagonal = !(drawHorizontalLines(value, intersections) || drawVerticalLines(value, intersections))

            // Diagonal lines
            if (diagonal) {
                drawDiagonalLines(value, intersections)
            }
        }

        return calculateIntersections(intersections)
    }

    // Check implementation using provided example
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    // Output challenge solution
    val input = readInput("Day05")
    printResult(1, part1(input))
    printResult(2, part2(input))
}
