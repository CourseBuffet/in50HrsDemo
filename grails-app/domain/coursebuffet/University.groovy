package coursebuffet

class University {

    String name
    String website

    String toString() { name }

    static constraints = {
        website(nullable: true, url:true)
    }
}
