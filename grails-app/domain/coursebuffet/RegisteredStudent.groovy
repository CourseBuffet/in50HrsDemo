package coursebuffet

class RegisteredStudent {

    String email

    static hasMany = [
        viewedCourses: Course,
        savedCourses: Course,
        commitedCourses: Course,
        doneCourses: Course
    ]

    boolean registered = true

    static constraints = {
    }
}
