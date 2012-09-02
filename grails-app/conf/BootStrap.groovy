import coursebuffet.*

class BootStrap {

    def init = { servletContext ->
        new Subject(name: 'ALL').save(failOnError: true)
        new University(name: 'ALL').save(failOnError: true)

        [   [name: 'Udacity', website: 'http://udacity.com'],
            [name: 'Stanford', website: 'http://stanford.edu']].each { new University(it).save(failOnError: true) }
        [   [name: 'Coursera', website: 'http://coursera.org'],
            [name: 'Udacity', website: 'http://udacity.com']].each { new Provider(it).save(failOnError: true) }

            [
["Computer Science",229,"Machine Learning","https://www.coursera.org/course/ml","Learn about the most effective machine learning techniques, and gain practice implementing them and getting them to work for yourself.","Stanford", "Coursera"],
["Computer Science",255,"Cryptography","https://www.coursera.org/course/crypto","Learn about the inner workings of cryptographic primitives and how to apply this knowledge in real-world applications!","Stanford", "Coursera"],
["Computer Science",101,"Introduction to Computer Science","http://www.coursera.org/overview/Course/cs101","In this course you will learn key concepts in computer science and learn how to write your own computer programs in the context of building a web crawler.","Stanford", "Coursera"],
["Computer Science",101,"Introduction to Computer Science","http://www.udacity.com/overview/Course/cs101","In this course you will learn key concepts in computer science and learn how to write your own computer programs in the context of building a web crawler.","Udacity", "Udacity"],
["Physics",100,"Intro to Physics","http://www.udacity.com/overview/Course/ph100","Study physics abroad in Europe -- virtually! Learn the basics of physics on location in Italy, the Netherlands and the UK, by answering some of the discipline's major questions from over the last 2000 years.","Udacity", "Udacity"],
["Mathematics",101,"Intro to Statistics","http://www.udacity.com/overview/Course/st101","Statistics is about extracting meaning from data. In this class, we will introduce techniques for visualizing relationships in data and systematic techniques for understanding the relationships using mathematics.","Udacity", "Udacity"],
["Computer Science",215,"Algorithms","http://www.udacity.com/overview/Course/cs215","Ever played the Kevin Bacon game? This class will show you how it works by giving you an introduction to the design and analysis of algorithms, enabling you to discover how individuals are connected.","Udacity", "Udacity"],
["Computer Science",222,"Making Math Matter","http://www.udacity.com/overview/Course/cs222","In this course you will examine real world problems -- rescue the Apollo 13 astronauts, stop the spread of epidemics, and fight forest fires -- involving differential equations and figure out how to solve them using numerical methods.","Udacity", "Udacity"],
["Computer Science",253,"Web Development","http://www.udacity.com/overview/Course/cs253","Starting from the basics of how the web works, this class will walk you through everything you need to know to build your own blog application and scale it to support large numbers of users.","Udacity", "Udacity"],
["Computer Science",258,"Software Testing","http://www.udacity.com/overview/Course/cs258","When writing software, destruction can be just as valuable as creation. Learn how to catch bugs and break software as you discover different testing methods that will help you build better software.","Udacity", "Udacity"],
["Computer Science",259,"Software Debugging","http://www.udacity.com/overview/Course/cs259","In this class you will learn how to debug programs systematically, how to automate the debugging process and build several automated debugging tools in Python.","Udacity", "Udacity"],
["Computer Science",262,"Programing Languages","http://www.udacity.com/overview/Course/cs262","This class will give you an introduction to the fundamentals of programming languages. Key concepts include how to specify and process valid strings, sentences and program structures.","Udacity", "Udacity"],
["Computer Science",313,"Intro to Theoretical Computer Science","http://www.udacity.com/overview/Course/cs313","This class teaches you about basic concepts in theoretical computer science -- such as NP-completeness -- and what they imply for solving tough algorithmic problems.","Udacity", "Udacity"],
["Entrepreneurship",245,"How to Build a Startup","http://www.udacity.com/overview/Course/ep245","Learn the key tools and steps to build a successful startup (or at least reduce the risk of failure). An introduction to the basics of Steve Blank's famous Customer Development process, where entrepreneurs get out of the building to gather massive amounts of customer and marketplace feedback, and then use that feedback to continuously iterate and evolve their startup business models, improving the chances of success at every step.","Udacity", "Udacity"],
["Computer Science",212,"Design of Computer Programs","http://www.udacity.com/overview/Course/cs212","Learn new concepts, patterns, and methods that will expand your programming abilities, helping move you from a novice to an expert programmer.","Udacity", "Udacity"],
["Computer Science",373,"Artificial Intelligence ","http://www.udacity.com/overview/Course/cs373","Learn how to program all the major systems of a robotic car from the leader of Google and Stanford's autonomous driving teams. This class will teach you basic methods in Artificial Intelligence, including: probabilistic inference, planning and search, localization, tracking and control, all with a focus on robotics. Extensive programming examples and assignments will apply these methods in the context of building self-driving cars.","Udacity", "Udacity"],
["Computer Science",387,"Applied Cryptography","http://www.udacity.com/overview/Course/cs387","Cryptography is present in everyday life, from paying with a credit card to using the telephone. Learn all about making and breaking puzzles in computing.","Udacity", "Udacity"],
            ].each { data ->
                new Course(level: data[1], title: data[2], subject: Subject.findOrCreateByName(data[0]).save(failOnError: true), offeredBy: University.findByName(data[-2]), offeredVia: Provider.findByName(data[-1]), website: data[3], description: data[4][0..[254, data[4].length() - 1].min()]).save(failOnError: true)
            }

    println Course.getAll()

    }
    def destroy = {
    }
}
