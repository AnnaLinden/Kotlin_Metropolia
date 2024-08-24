class Student(name: String, age: Int): Human(name, age) {
    var courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord){
        courses.add(course)
    }

    fun weightedAverage(year: Int? = null): Double {
        val filteredCourses = if (year != null) {
            courses.filter { it.yearCompleted == year }
        } else {
            courses
        }

        val totalWeightedGrades = filteredCourses.sumOf { it.grade * it.credits }
        val totalCredits = filteredCourses.sumOf { it.credits }
        return if (totalCredits > 0) totalWeightedGrades / totalCredits else 0.0
    }



    fun minMaxGrades(): Pair<Double,Double> =
        Pair(courses.minOf { it.grade }, courses.maxOf { it.grade })
}