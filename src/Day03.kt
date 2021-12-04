fun main() {

    fun getMostCommonBit(list: List<String>, index: Int): Char {
        var count = 0
        list.forEach { count += if (it.toCharArray()[index] == '1') 1 else -1 }
        return if (count >= 0) '1' else '0'
    }

    fun part1(input: List<String>): Int {
        var (gamma, epsilon) = listOf("", "")

        input.first().toCharArray().forEachIndexed { i, _ ->
            gamma += if (getMostCommonBit(input, i) == '1') '0' else '1'
            epsilon += getMostCommonBit(input, i)
        }

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon,2)
    }

    fun part2(input: List<String>): Int {
        var inputListClone = input.toMutableList()

        run oxygen@ {
            input.first().toCharArray().forEachIndexed { i, _ ->
                val mostCommon = getMostCommonBit(inputListClone, i)

                inputListClone.toMutableList().forEach {
                    if (it[i] != mostCommon)
                        inputListClone.remove(it)

                    if (inputListClone.size == 1)
                        return@oxygen
                }
            }
        }
        val oxygen = inputListClone.first()

        inputListClone = input.toMutableList()

        run scrubber@ {
            input.first().toCharArray().forEachIndexed { i, _ ->
                val mostCommon = getMostCommonBit(inputListClone, i)

                inputListClone.toMutableList().forEach {
                    if (it[i] != if (mostCommon == '1') '0' else '1')
                        inputListClone.remove(it)

                    if (inputListClone.size == 1)
                        return@scrubber
                }
            }
        }
        val scrubber = inputListClone.first()

        return Integer.parseInt(oxygen, 2) * Integer.parseInt(scrubber, 2)
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
