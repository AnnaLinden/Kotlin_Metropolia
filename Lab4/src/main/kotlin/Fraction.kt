class Fraction(private val numerator: Int, private val denominator: Int, private val sign: Int = 1) : Comparable<Fraction> {

    init {
        if (denominator == 0) throw IllegalArgumentException("Denominator cannot be zero")
    }

    private val simplifiedNumerator: Int
    private val simplifiedDenominator: Int
    private val finalSign: Int

    init {
        var n = numerator
        var d = denominator
        var s = sign

        if (d < 0) {
            d *= -1
            s *= -1
        }

        val gcd = gcd(n, d)
        n /= gcd
        d /= gcd
        if (n < 0) {
            n *= -1
            s *= -1
        }

        simplifiedNumerator = n
        simplifiedDenominator = d
        finalSign = s
    }

    private fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % temp
            x = temp
        }
        return x
    }

    // New mult method
    fun mult(other: Fraction): Fraction {
        val newNumerator = simplifiedNumerator * other.simplifiedNumerator * finalSign * other.finalSign
        val newDenominator = simplifiedDenominator * other.simplifiedDenominator
        return Fraction(newNumerator, newDenominator)
    }

    operator fun unaryMinus(): Fraction {
        return Fraction(simplifiedNumerator, simplifiedDenominator, -finalSign)
    }

    operator fun plus(other: Fraction): Fraction {
        val commonDenominator = simplifiedDenominator * other.simplifiedDenominator
        val newNumerator = finalSign * simplifiedNumerator * other.simplifiedDenominator +
                other.finalSign * other.simplifiedNumerator * simplifiedDenominator
        return Fraction(newNumerator, commonDenominator)
    }

    operator fun minus(other: Fraction): Fraction {
        return this + -other
    }

    operator fun times(other: Fraction): Fraction {
        return mult(other)  // Use the mult method for multiplication
    }

    operator fun div(other: Fraction): Fraction {
        return this * Fraction(other.simplifiedDenominator, other.simplifiedNumerator, other.finalSign)
    }

    fun negate(): Fraction {
        return Fraction(simplifiedNumerator, simplifiedDenominator, -finalSign)
    }

    fun add(other: Fraction): Fraction {
        return this + other
    }

    override fun toString(): String {
        return if (simplifiedDenominator == 1) {
            "${simplifiedNumerator * finalSign}/1"
        } else {
            if (finalSign == -1) "-$simplifiedNumerator/$simplifiedDenominator" else "$simplifiedNumerator/$simplifiedDenominator"
        }
    }

    override fun compareTo(other: Fraction): Int {
        val leftValue = finalSign * simplifiedNumerator * other.simplifiedDenominator
        val rightValue = other.finalSign * other.simplifiedNumerator * simplifiedDenominator
        return leftValue.compareTo(rightValue)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraction) return false
        return simplifiedNumerator == other.simplifiedNumerator &&
                simplifiedDenominator == other.simplifiedDenominator &&
                finalSign == other.finalSign
    }

    override fun hashCode(): Int {
        return 31 * simplifiedNumerator + 31 * simplifiedDenominator + finalSign
    }
}
