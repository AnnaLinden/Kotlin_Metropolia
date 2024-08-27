fun printHello(){
    println("Hello world")
}
fun second() {
    println("Hello Kotlin")
}

fun third (){
    println("Give me a string?: ")
    val stringInput = readLine()
    println("You entered: $stringInput ")
}

fun score(x: Double, y: Double): Int {
    val distance = Math.sqrt(x*x + y*y)


    return when{
        distance<=1.0 -> 10
        distance <=5.0 -> 5
        distance <=10.0 -> 1
        else -> 0
    }
}

fun main(args: Array<String>) {
    //println("Paramters are: " + args.joinToString())
    second()
    //third()
    //printHello()
    val testCases = listOf(
        Pair(-9.0, 9.0) to 0,
        Pair(0.0, 10.0) to 1,
        Pair(-5.0, 0.0) to 5,
        Pair(0.0, -1.0) to 10,
        Pair(0.0, 0.0) to 10,
        Pair(-0.1, -0.1) to 10,
        Pair(0.7, 0.7) to 10,
        Pair(0.8, -0.8) to 5,
        Pair(-3.5, 3.5) to 5,
        Pair(-3.6, -3.6) to 1,
        Pair(-7.0, 7.0) to 1,
        Pair(7.1, -7.1) to 0
    )

    testCases.forEach { (coordinates, expected) ->
        val (x, y) = coordinates
        val result = score(x, y)
        println("Testing score($x, $y): Expected = $expected, Result = $result")
        checkResult(x, y, expected, result)
    }
}

fun checkResult(x: Double, y: Double, expected: Int, result: Int) {
    if (expected != result) {
        println("Error: Test score($x, $y) failed. Expected $expected, got $result")
    }
}