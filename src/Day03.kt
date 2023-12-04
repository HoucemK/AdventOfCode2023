fun main() {
    fun part1Day03(input: List<String>): Long {
        val matx = input.map { it.toList() }
        val map = buildMap<Pair<Int, Int>, String> {
            matx.forEachIndexed { index, chars ->
                val num = mutableListOf<Char>()
                val i = index
                var pair = 0 to 0
                for (j in chars.indices) {
                    if (matx[i][j].isDigit()) {
                        num.add(matx[i][j])
                        if (num.size == 1)
                            pair = i to j
                        if (j==chars.lastIndex){
                            put(pair, num.joinToString(""))
                            num.clear()
                        }
                    } else {
                        if (num.isNotEmpty())
                            put(pair, num.joinToString(""))
                        num.clear()
                    }
                }
            }
        }
        return map.filter{ haveAdjacent(it.key, it.value, matx) }.map {it.value.toLong() }.sum()
    }
    
    fun part2Day03(input: List<String>): Int {
        val matrix = input.map { it.toList() }
        val map = buildMap<Pair<Int, Int>, String> {
            matrix.forEachIndexed { index, chars ->
                val num = mutableListOf<Char>()
                val i = index
                var pair = 0 to 0
                for (j in chars.indices) {
                    if (matrix[i][j].isDigit()) {
                        num.add(matrix[i][j])
                        if (num.size == 1)
                            pair = i to j
                        if (j==chars.lastIndex){
                            put(pair, num.joinToString(""))
                            num.clear()
                        }
                    } else {
                        if (num.isNotEmpty())
                            put(pair, num.joinToString(""))
                        num.clear()
                    }
                }
            }
        }
        val result = map.map {
            PartsWithStars(
                it.value,
                getStars(it.key, it.value, matrix)
            )
        }
            .filter { it.listOfStars.isNotEmpty() }
            .flatMap { it.listOfStars.map {c-> c to it.value } }
            .groupBy { it.first }
            .filter { it.value.count() == 2 }
            .map { it.value[0].second.toInt() * it.value[1].second.toInt() }.sum()
        
        return result
    }


    val input = readInput("day3")
    part2Day03(input).println()
}

fun haveAdjacent(cor: Pair<Int, Int>, number: String, matrix: List<List<Char>>): Boolean {
    val n = matrix.size
    val m = matrix[0].size
    var result = false
    for (i in cor.first-1..cor.first+1) {
        for (j in cor.second-1.. cor.second+number.length) {
            if (i in 0..<n && j in 0..<m) {
                val char = matrix[i][j]
                if (char != '.' && char.isDigit().not()) {
                    result = true
                }
            }
        }
    }
    return result
}

fun getStars(cor: Pair<Int, Int>, number: String, matrix: List<List<Char>>): List<Pair<Int,Int>> {
    val n = matrix.size
    val m = matrix[0].size
    val list : MutableList<Pair<Int,Int>> = mutableListOf()
    for (i in cor.first-1..cor.first+1) {
        for (j in cor.second-1.. cor.second+number.length) {
            if (i in 0..<n && j in 0..<m) {
                val char = matrix[i][j]
                if (char == '*' ) {
                    list += i to j
                }
            }
        }
    }
    return list
}

data class PartsWithStars(
    val value : String,
    val listOfStars: List<Pair<Int,Int>>,
)

