class Lotto(private val n: Int, private val lottoRange: IntRange, secretNumbers: List<Int>? = null) {
    private val secretNumbers = secretNumbers  ?: pickNDistinct(lottoRange, n)

    fun getN() = n
    fun getLottoRange() = lottoRange
    fun getSecretNumbers()= secretNumbers

    // returns a list with n distinct ints from range
    fun pickNDistinct(range: IntRange, n: Int): List<Int> {
        if(n>range.count()) return emptyList()
        val pickedNumbers = mutableSetOf<Int>()
        while (pickedNumbers.size <n ){
            val randomNum = range.random()
            pickedNumbers.add(randomNum)
        }
        return pickedNumbers.toList()
    }

    // return the number of distinct ints in list
    fun numDistinct(list: List<Int>): Int {
        return list.toSet().size
    }

    // return the number of ints in both list1 and list2
    fun numCommon(list1: List<Int>, list2: List<Int>): Int {
        return list1.intersect(list2).size
    }

    // is guess legal? (consists of n different ints from range)
    fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n): Boolean {
        return guess.toSet().size == count && guess.all {it in range}
    }

    // if guess is legal return the number of ints in guess that also appear in secret, otherwise 0
    fun checkGuess(guess: List<Int>, secret: List<Int> = secretNumbers): Int {
        return if (isLegalLottoGuess(guess)) numCommon(guess, secret) else 0
    }


}