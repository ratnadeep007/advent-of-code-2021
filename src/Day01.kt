fun main() {
    fun part1(input: List<String>): Int {
        var largeCount = 0
        var lastNumber: Int = input[0].toInt()
        for (i in 1 until input.size) {
            if (input[i].toInt() > lastNumber) largeCount += 1
            lastNumber = input[i].toInt()
        }

        return largeCount
    }

    fun part2(input: List<String>): Int {
        val sumList: MutableList<String> = mutableListOf()
        for (i in 0 .. input.size) {
            if ((i+1 < input.size - 1) or (i+2 < input.size - 1)) {
                val sum = input[i].toInt() + input[i + 1].toInt() + input[i + 2].toInt()
                sumList.add(sum.toString())
            }
        }
        println(sumList.size)

        return part1(sumList)
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
//    println(part1(input))
    println(part2(input))
}
