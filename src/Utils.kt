import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "input/$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Splits into space-separate parts of input and maps each part.
 */
fun <R> List<String>.parts(map: (List<String>) -> R): List<R> = buildList {
 var cur = ArrayList<String>()
 for (s in this@parts) {
  if (s == "") {
   add(map(cur))
   cur = ArrayList()
   continue
  }
  cur.add(s)
 }
 if (cur.isNotEmpty()) add(map(cur))
}

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
