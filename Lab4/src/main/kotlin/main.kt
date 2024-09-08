fun main() {
    val a = Fraction(1, 2, -1)
    println(a)  // Output: -1/2
    println(a.add(Fraction(1, 3)))  // Output: -1/6
    println(a.mult(Fraction(5, 2, -1)))  // Output: 5/4
    println(a.div(Fraction(2, 1)))  // Output: 1/4
    println(-Fraction(1, 6) + Fraction(1, 2))  // Output: 1/3
    println(Fraction(2, 3) * Fraction(3, 2))  // Output: 1/1
    println(Fraction(1, 2) > Fraction(2, 3))  // Output: false
}
