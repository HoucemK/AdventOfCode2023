fun main() {
    fun part1(input: List<String>): Long =
            input
                .map { it.filter { it in '0'..'9' } }
                .map { "${it.first()}${it.last()}" }.sumOf { it.toLong() }


    fun part2(input: List<String>): Long {
        val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val l = input.map { s ->
            val result = buildString {
                for (i in s.indices)
                    if (s[i] in '0'..'9')
                        append(s[i])
                    else {
                        val substring = s.substring(i)
                        for (j in numbers.indices){
                            if (substring.startsWith(numbers[j]))
                                append(j+1)
                        }
                    }
            }
            "${result.first()}${result.last()}".toLong()
        }
       return l.sum()
    }

    val input = readInput("day1")
    part1(input).println()
    part2(input).println()
}
