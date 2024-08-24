class Student(name: String, age: Int): Human(name, age) {
    var courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord){
        courses.add(course)
    }
    fun weightedAverage(): Double{
        val totalWeightedGrades = courses.sumOf {it.grade * it.credits}
        val totalCredits = courses.sumOf { it.credits }
        return totalWeightedGrades/totalCredits
    }
    fun minMaxGrades(): Pair<Double,Double> = Pair(courses.minOf { it.grade }, courses.maxOf { it.grade })
}