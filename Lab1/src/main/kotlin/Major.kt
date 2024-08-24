class Major(var name: String) {
    var students = mutableListOf<Student>()

    fun addStudent(student: Student){
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {

        return Triple(0.0,0.0,0.0)
    }

    fun stats(courseName: String): Triple<Double, Double, Double>{
        return Triple(0.0,0.0,0.0)
    }

}