fun isRomanNumber(number: String) : Boolean {

    val historySet = HashSet<Char>()

    val size = number.length
    if (size == 0)
        return false;

    var ch : Char

    var countI = 0
    var countX = 0
    var countC = 0
    var i = 0

    while (i < size) {
        ch = number[i]
        when(ch) {
            'I' -> {
                // No more than 3 'I's in a row
                countI++
                if (countI > 3) return false

                countX = 0
                countC = 0
            }
            'V' -> {
                // Can't have more than 1 'V'
                if (historySet.contains('V')) return false
                // Only a single 'I' can precede 'V'
                if (countI > 1) return false

                countI = 0
                countX = 0
                countC = 0
            }
            'X' -> {
                // No more than 3 'X's in a row
                countX++
                if (countX > 3) return false

                // 'V' cannot come before 'X'
                if (historySet.contains('V')) return false

                // Only a single 'I' can precede 'X' and if it is there than it must be in the end of the string
                if (historySet.contains('I')) {
                    if (countI != 1 || i != size - 1)
                        return false
                }
                countI = 0
                countC = 0
            }
            'L' -> {
                // Can't have more then 1 'L' and only 'I' and 'V' are not allowed before 'L'
                if (historySet.contains('L') || historySet.contains('I') || historySet.contains('V'))
                    return false

                // Only a single 'X' can precede 'L'
                if (countX > 1) return false

                countI = 0
                countX = 0
                countC = 0
            }
            'C' -> {
                // No more than 3 'C's in a row
                countC++
                if (countC > 3) return false

                // Only 'X' or 'C' can precede 'C'
                if (historySet.contains('L') || historySet.contains('I') || historySet.contains('V'))
                    return false

                // Only a single 'X' can precede 'C'
                if (countX > 1) return false

                // When X precede C than More then 1 C is not allowed
                if (historySet.contains('X') && (countC >= 2))  return false;

                countI = 0
                countX = 0
            }
            else ->  return false
        }
        historySet.add(ch)
        i++
    }
    return true
}

fun main(args: Array<String>) {
}
