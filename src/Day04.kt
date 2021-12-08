fun main() {
    data class Entry(val number: Int, var marked: Boolean)

    fun isBingo(cols: List<Entry>, rows: List<Entry>): Boolean {
        return cols.all { it.marked } || rows.all { it.marked }
    }

    fun getResults(input: List<String>): List<Int> {
        val bingoFields = mutableListOf<Int>()
        val results = mutableListOf<Int>()

        val markNumbers = input
            .first()
            .split(",")
            .map { it.trim().toInt() }

        val entries = input.subList(2, input.size).flatMap { line ->
            if (line.isNotEmpty()) {
                line.split(" ")
                    .filter { it.isNotEmpty() }
                    .map { Entry(it.trim().toInt(), false) }
            } else emptyList()
        }

        markNumbers.forEach { markNumber ->
            entries.forEachIndexed { i, entry ->
                if (entry.number == markNumber) {
                    entry.marked = true
                    val index = i / 25
                    val start = index * 25
                    val (col, row ) = listOf((i - start) % 5, (i - start) / 5)
                    val cols = (0 until 5).map { entries[start + it * 5 + col] }
                    val rows = entries.subList(start + row * 5, start + row * 5 + 5)
                    if (isBingo(cols, rows)) {
                        val field = entries.subList(start, start + 25)
                        val unmarkedEntries = field.filter { !it.marked }.sumOf { it.number }

                        if (!bingoFields.contains(index)) {
                            bingoFields += index
                            results += unmarkedEntries * markNumber
                        }
                    }
                }
            }
        }

        return results
    }

    fun part1(input: List<String>): Int {
        return getResults(input).first()
    }

    fun part2(input: List<String>): Int {
        return getResults(input).last()
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
