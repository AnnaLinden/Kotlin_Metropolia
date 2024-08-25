class FractionMutable(private var numerator: Int, private var denominator: Int, private var sign: Int = 1) {
    init {
        if (denominator == 0) {
            throw IllegalArgumentException("Denominator cannot be zero")
        }
        if (denominator < 0) {
            denominator *= -1
            sign *= -1
        }
        simplify()
    }

    private fun simplify() {
        val gcd = gcd(numerator, denominator)
        numerator /= gcd
        denominator /= gcd
        if (numerator < 0) {
            numerator *= -1
            sign *= -1
        }
    }

    private fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

    fun negate() {
        sign *= -1
    }

    fun add(other: FractionMutable) {
        if (sign == other.sign) {
            numerator = numerator * other.denominator + other.numerator * denominator
        } else {
            numerator = numerator * other.denominator - other.numerator * denominator
        }
        denominator *= other.denominator
        simplify()
    }

    fun mult(other: FractionMutable) {
        numerator *= other.numerator
        denominator *= other.denominator
        sign *= other.sign
        simplify()
    }

    fun div(other: FractionMutable) {
        if (other.numerator == 0) {
            throw ArithmeticException("Cannot divide by zero fraction")
        }
        numerator *= other.denominator
        denominator *= other.numerator
        sign *= other.sign
        simplify()
    }

    fun intPart(): Int {
        return (numerator / denominator) * sign
    }

    override fun toString(): String {
        if (denominator == 1 && numerator != 0) {
            return "${numerator * sign}/1"
        }
        return if (sign == -1) "-$numerator/$denominator" else "$numerator/$denominator"
    }
}
