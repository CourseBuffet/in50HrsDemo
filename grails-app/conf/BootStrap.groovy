import coursebuffet.*

class BootStrap {

    def init = { servletContext ->
        new Subject(name: 'ALL').save(failOnError: true)
        new University(name: 'ALL').save(failOnError: true)

        [   [name: 'Harvard', website: 'http://harvard.edu'],
            [name: 'Stanford', website: 'http://stanford.edu']].each { new University(it).save(failOnError: true) }
        [   [name: 'Coursera', website: 'http://coursera.com'],
            [name: 'edX', website: 'http://edX.com']].each { new Provider(it).save(failOnError: true) }

        101.upto(105) { level -> 
            ['Microbiology', 'Genetics', 'Behavioural Neurology'].each { course ->
                new Course(level: level, title: course, subject: Subject.findOrCreateByName('Biology').save(failOnError: true), offeredBy: University.findByName('Harvard'), offeredVia: Provider.findByName('Coursera')).save(failOnError: true)
            }

            ['Solid State Chemistry', 'Analytical Chemistry', 'Organic Chemistry'].each { course ->
                new Course(level: level, title: course, subject: Subject.findOrCreateByName('Chemistry').save(failOnError: true), offeredBy: University.findByName('Stanford'), offeredVia: Provider.findByName('edX')).save(failOnError: true)
            }

        }

    }
    def destroy = {
    }
}
