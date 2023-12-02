fun main() {
    val input = readInput("day2")
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Long {
    val ans = input
            .map { s ->
                val (i, d) = s.split(":")
                (i to d)
            }
            .filter {
                val l = it.second
                        .replace(",", "").replace(";", "").trimStart(' ')
                        .split(' ')
                        .chunked(2) { x ->
                            x[0] to x[1]
                        }
                l.none { nn ->
                    (nn.second == "blue" && nn.first.toInt() > 14)
                            || (nn.second == "green" && nn.first.toInt() > 13)
                            || (nn.second == "red" && nn.first.toInt() > 12)
                }
            }.sumOf { it.first.split(' ')[1].toInt() }
    return ans.toLong()
}

fun part2(input: List<String>): Int {
    val ans = input
            .map { s ->
                val (i, d) = s.split(":")
                (i to d)
            }
            .map {
                val l = it.second
                        .replace(",", "").replace(";", "").trimStart(' ')
                        .split(' ')
                        .chunked(2)
                        { x -> x[0] to x[1] }
                l.groupBy { g -> g.second }
                        .map { cd ->
                            cd.value.map { lk ->
                                lk.first
                            }
                        }
            }

    return ans.sumOf { m ->
        var res = 1
        m.forEach { n -> res *= n.maxOf { it.toInt() } }
        res
    }
}
