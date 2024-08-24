class Major(var name: String) {
    var students = mutableListOf<Student>()

    fun addStudent(student: Student){
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage()}

        val minAverage = averages.minOrNull() ?: 0.0
        val maxAverage = averages.maxOrNull() ?: 0.0
        val averageAverage = if (averages.isNotEmpty()) averages.average() else 0.0

        return Triple(minAverage,maxAverage,averageAverage)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {

        students.forEach { student ->
            student.courses.filter { it.name == courseName }.also { filteredCourses ->
                if (filteredCourses.isNotEmpty()) {
                    val weightedSum = filteredCourses.sumOf { it.grade * it.credits }
                    val totalCredits = filteredCourses.sumOf { it.credits }
                    val average = weightedSum / totalCredits
                    println("Student: ${student.name}, Course: $courseName, WeightedSum: $weightedSum, TotalCredits: $totalCredits, Average: $average")
                }
            }
        }

        val averages = students.mapNotNull { student ->
            student.courses.filter { it.name == courseName }.takeIf { it.isNotEmpty() }?.let {
                it.sumOf { course -> course.grade * course.credits } / it.sumOf { course -> course.credits }
            }
        }

        return Triple(
            averages.minOrNull() ?: 0.0,
            averages.maxOrNull() ?: 0.0,
            if (averages.isNotEmpty()) averages.average() else 0.0
        )
    }

}

