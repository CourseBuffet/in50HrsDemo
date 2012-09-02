package coursebuffet

import org.springframework.dao.DataIntegrityViolationException

class RegisteredStudentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def addCourse(Integer id) {
        if(session.user.registered) {
            session.user.refresh()
            session.user.savedCourses << Course.get(id)
            session.user.save(failOnError: true, flush: true)
            redirect(action: 'show', id: session.user.id)
        } else {
            redirect(action: 'create')
        }
    }

    def commitCourse(Integer id) {
        session.user.refresh()
        session.user.savedCourses.remove(Course.get(id))
        session.user.commitedCourses << Course.get(id)
        session.user.save(failOnError: true, flush: true)
        redirect(action: 'show', id: session.user.id)
    }

    def finishCourse(Integer id) {
        session.user.refresh()
        session.user.commitedCourses.remove(Course.get(id))
        session.user.doneCourses << Course.get(id)
        session.user.save(failOnError: true, flush: true)
        redirect(action: 'show', id: session.user.id)
    }

/*
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [registeredStudentInstanceList: RegisteredStudent.list(params), registeredStudentInstanceTotal: RegisteredStudent.count()]
    }
*/

    def create() {
        [registeredStudentInstance: new RegisteredStudent(params)]
    }

    def save() {
        params.viewedCourses = session.user.viewedCourses
        def registeredStudentInstance = new RegisteredStudent(params)
        if (!registeredStudentInstance.save(flush: true)) {
            render(view: "create", model: [registeredStudentInstance: registeredStudentInstance])
            return
        }

        //flash.message = message(code: 'default.created.message', args: [message(code: 'registeredStudent.label', default: 'RegisteredStudent'), registeredStudentInstance.id])
        session.user = registeredStudentInstance
        redirect(action: "show", id: registeredStudentInstance.id)
    }

    def show(Long id) {
        def registeredStudentInstance = RegisteredStudent.get(id)
        if (!registeredStudentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registeredStudent.label', default: 'RegisteredStudent'), id])
            redirect(action: "list")
            return
        }

        [registeredStudentInstance: registeredStudentInstance]
    }

    def edit(Long id) {
        def registeredStudentInstance = RegisteredStudent.get(id)
        if (!registeredStudentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registeredStudent.label', default: 'RegisteredStudent'), id])
            redirect(action: "list")
            return
        }

        [registeredStudentInstance: registeredStudentInstance]
    }

    def update(Long id, Long version) {
        def registeredStudentInstance = RegisteredStudent.get(id)
        if (!registeredStudentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registeredStudent.label', default: 'RegisteredStudent'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (registeredStudentInstance.version > version) {
                registeredStudentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'registeredStudent.label', default: 'RegisteredStudent')] as Object[],
                          "Another user has updated this RegisteredStudent while you were editing")
                render(view: "edit", model: [registeredStudentInstance: registeredStudentInstance])
                return
            }
        }

        registeredStudentInstance.properties = params

        if (!registeredStudentInstance.save(flush: true)) {
            render(view: "edit", model: [registeredStudentInstance: registeredStudentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'registeredStudent.label', default: 'RegisteredStudent'), registeredStudentInstance.id])
        redirect(action: "show", id: registeredStudentInstance.id)
    }

    def delete(Long id) {
        def registeredStudentInstance = RegisteredStudent.get(id)
        if (!registeredStudentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'registeredStudent.label', default: 'RegisteredStudent'), id])
            redirect(action: "list")
            return
        }

        try {
            registeredStudentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'registeredStudent.label', default: 'RegisteredStudent'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'registeredStudent.label', default: 'RegisteredStudent'), id])
            redirect(action: "show", id: id)
        }
    }
}
