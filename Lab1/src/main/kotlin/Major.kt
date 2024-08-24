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
fun main() {
    // Initialize a new Major
    val major = Major("True Programming")

    // Create and add students with their courses
    val student1 = Student("Enzio Benzino", 21)
    student1.addCourse(CourseRecord("Kotlin basics", 2023, 5, 5.0))
    student1.addCourse(CourseRecord("Java basics", 2023, 5, 1.0))
    student1.addCourse(CourseRecord("Scala basics", 2023, 3, 2.0))
    major.addStudent(student1)

    val student2 = Student("Abebe Bikila", 23)
    student2.addCourse(CourseRecord("Kotlin basics", 2023, 5, 2.0))
    major.addStudent(student2)

    val student3 = Student("Günther Radulic", 20)
    student3.addCourse(CourseRecord("Kotlin basics", 2023, 5, 4.0))
    student3.addCourse(CourseRecord("Kotlin advanced", 2023, 5, 5.0))
    major.addStudent(student3)

    // Calculate and print statistics for the major
    val (min, max, avg) = major.stats()
    println("Major stats - Min: $min, Max: $max, Avg: $avg")

    // Calculate and print statistics for a specific course
    val (courseMin, courseMax, courseAvg) = major.stats("Kotlin basics")
    println("Stats for 'Kotlin basics' - Min: $courseMin, Max: $courseMax, Avg: $courseAvg")
}
