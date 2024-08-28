fun main(){
    playLotto()
}

fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
    println("Give $n numbers from $low to $high, separated by commas:")
    while (true) {
        val input = readLine()?.trim()
        val numbers = input?.split(",")?.mapNotNull { it.trim().toIntOrNull() }?.filter { it in low..high }?.distinct()
        if (numbers != null && numbers.size == n) {
            return numbers
        }
        println("Invalid input. Please ensure you enter $n distinct numbers from $low to $high, separated by commas:")
    }
}

fun findLotto(lotto: Lotto): Pair<Int, List<Int>> {
    var steps = 0
    var currentGuess = List(lotto.getN()) { lotto.getLottoRange().random() }
    var correct = lotto.checkGuess(currentGuess)

    val maxAttempts = 1000

    while (correct < lotto.getN() && steps < maxAttempts) {
        steps++
        currentGuess = List(lotto.getN()) { lotto.getLottoRange().random() }
        correct = lotto.checkGuess(currentGuess)
    }

    return Pair(steps, currentGuess)
}

fun playLotto() {
    while (true) {
        val lotto = Lotto(7, 1..40)
        val userGuess = readNDistinct(1, 40, 7)
        val correctGuesses = lotto.checkGuess(userGuess)
        val (steps, correctGuess) = findLotto(lotto)
        println("lotto numbers: ${lotto.getSecretNumbers()}, you got $correctGuesses correct")
        println("computer guess in $steps steps is $correctGuess")

        println("More? (Y/N):")
        if (readLine()?.trim()?.uppercase() != "Y") {
            println("Thank you for playing!")
            break
        }
    }
}