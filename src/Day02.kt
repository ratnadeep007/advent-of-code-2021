fun main() {
    fun convertStringToInstructionPair(input: List<String>): List<Pair<String, Int>> {
        val instructionList = mutableListOf<Pair<String, Int>>()
        for (i in input.indices) {
            val command = input[i].split(" ")[0]
            val value = input[i].split(" ")[1]
            val p = Pair(command, value.toInt())
            instructionList.add(p)
        }
        return instructionList
    }

    fun part1(input: List<String>): Int {
        var forwardAndDepth = Pair(0, 0)
        val instructions = convertStringToInstructionPair(input)

        for (i in instructions.indices) {
            when(instructions[i].first) {
                "forward" -> {
                    forwardAndDepth = forwardAndDepth.copy(first = forwardAndDepth.first +  instructions[i].second)
                }
                "down" -> {
                    forwardAndDepth = forwardAndDepth.copy(second = forwardAndDepth.second + instructions[i].second)
                }
                "up" -> {
                    forwardAndDepth = forwardAndDepth.copy(second = forwardAndDepth.second - instructions[i].second)
                }
            }
        }

        return forwardAndDepth.first * forwardAndDepth.second
    }

    fun part2(input: List<String>): Int {
        var forwardDepthAndAim = Triple(0, 0, 0)
        val instructions = convertStringToInstructionPair(input)

        for (i in instructions.indices) {
            when(instructions[i].first) {
                "forward" -> {
                    forwardDepthAndAim = forwardDepthAndAim.copy(
                        first = forwardDepthAndAim.first + instructions[i].second,
                        second = (forwardDepthAndAim.third * instructions[i].second) + forwardDepthAndAim.second
                    )
                }
                "down" -> {
                    forwardDepthAndAim = forwardDepthAndAim.copy(third = forwardDepthAndAim.third + instructions[i].second)
                }
                "up" -> {
                    forwardDepthAndAim = forwardDepthAndAim.copy(third = forwardDepthAndAim.third - instructions[i].second)
                }
            }
        }

        println(forwardDepthAndAim)

        return forwardDepthAndAim.first * forwardDepthAndAim.second
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
