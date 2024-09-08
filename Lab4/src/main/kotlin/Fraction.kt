class Fraction(numerator: Int, denominator: Int, val sign: Int = 1) {

    var numerator = numerator
        private set
    var denominator = denominator
        private set

    init {
        normalize()
    }

    fun add(fraction: Fraction): Fraction {
        val lcd = lcd(fraction)
        val num1 = numerator * lcd / denominator
        val num2 = fraction.numerator * lcd / fraction.denominator

        val resNum = sign * num1 + fraction.sign * num2
        val resSign = if (resNum < 0) -1 else 1
        val resDen = lcd

        return Fraction(kotlin.math.abs(resNum), resDen, resSign)
    }

    fun mult(fraction: Fraction): Fraction {
        val resNum = numerator * fraction.numerator
        val resDen = denominator * fraction.denominator
        val resSign = sign * fraction.sign
        return Fraction(resNum, resDen, resSign)
    }

    operator fun div(fraction: Fraction): Fraction {
        return mult(Fraction(fraction.denominator, fraction.numerator, fraction.sign))
    }

    fun negate(): Fraction {
        return Fraction(numerator, denominator, sign * -1)
    }

    private fun lcd(fraction: Fraction): Int {
        return denominator * fraction.denominator / gcd(denominator, fraction.denominator)
    }

    private fun normalize() {
        val gcd = gcd(numerator, denominator)
        numerator /= gcd
        denominator /= gcd
    }

    private fun gcd(a: Int, b: Int): Int {
        var x = kotlin.math.abs(a)
        var y = kotlin.math.abs(b)
        while (y != 0) {
            val temp = y
            y = x % temp
            x = temp
        }
        return x
    }

    override fun toString(): String {
        return if (sign < 0) "-$numerator/$denominator" else "$numerator/$denominator"
    }

    operator fun unaryMinus(): Fraction = negate()

    operator fun plus(fraction: Fraction): Fraction = add(fraction)

    operator fun minus(fraction: Fraction): Fraction = add(-fraction)

    operator fun times(fraction: Fraction): Fraction = mult(fraction)

    operator fun compareTo(fraction: Fraction): Int {
        if (sign < fraction.sign) {
            return -1
        } else if (sign > fraction.sign) {
            return 1
        }

        val lcd = lcd(fraction)
        val num1 = numerator * lcd / denominator
        val num2 = fraction.numerator * lcd / fraction.denominator
        return num1 - num2
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Fraction) return false
        return compareTo(other) == 0
    }
}
