package coursebuffet

class Provider {

    String name
    String website

    String toString() { name }

    static constraints = {
        website(url: true)
    }
}
