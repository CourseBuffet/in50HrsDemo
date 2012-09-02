package coursebuffet

import org.springframework.dao.DataIntegrityViolationException

class CourseController {

    def beforeInterceptor = { if(!session.user) { session.user = new GuestStudent() } }

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        //redirect(action: "list", params: params)
    }

    private void clean(map, key, Closure value) {
        if(map[key]) { map[key] = value(map[key]) } else { map.remove(key) }
    }

    private void cleanDomain(map, key, Closure value) {
        if(map["${key}.id"] != '1') { map[key] = value(map["${key}.id"]) } else { map.remove(key) }
    }

    private Object listDetailed(params) {
        clean(params, 'level') { it as Integer }
        cleanDomain(params, 'subject') { Subject.get(it) }
        cleanDomain(params, 'offeredBy') { University.get(it) }

        def courseParams = params.findAll{ k, v -> ['level', 'title', 'subject', 'offeredBy', 'offeredVia'].contains(k) }
        Course.findAllWhere(courseParams)
    }
    
    private Object listInterests(params) {
        Course.findAllByTitleIlike("%${params.interest}%")
    }

    def list(Integer max, Integer offset) {
        params.max = max = max ?: 10
        params.offset = offset = offset ?: 0

        def courseInstances = params.searchtype == 'detailed' ? listDetailed(params) : listInterests(params)
        courseInstances = courseInstances[offset..[(offset + max), courseInstances.size() - 1].min()]
        [courseInstanceList: courseInstances, courseInstanceTotal: courseInstances.size(), params: params]
    }

    def create() {
        [courseInstance: new Course(params)]
    }

    def save() {
        def courseInstance = new Course(params)
        if (!courseInstance.save(flush: true)) {
            render(view: "create", model: [courseInstance: courseInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'course.label', default: 'Course'), courseInstance.id])
        redirect(action: "show", id: courseInstance.id)
    }

    def show(Long id) {
        def courseInstance = Course.get(id)
        if (!courseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), id])
            redirect(action: "list")
            return
        }

        session.user.viewedCourses << courseInstance

        [courseInstance: courseInstance]
    }

    def edit(Long id) {
        def courseInstance = Course.get(id)
        if (!courseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), id])
            redirect(action: "list")
            return
        }

        [courseInstance: courseInstance]
    }

    def update(Long id, Long version) {
        def courseInstance = Course.get(id)
        if (!courseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (courseInstance.version > version) {
                courseInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'course.label', default: 'Course')] as Object[],
                          "Another user has updated this Course while you were editing")
                render(view: "edit", model: [courseInstance: courseInstance])
                return
            }
        }

        courseInstance.properties = params

        if (!courseInstance.save(flush: true)) {
            render(view: "edit", model: [courseInstance: courseInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'course.label', default: 'Course'), courseInstance.id])
        redirect(action: "show", id: courseInstance.id)
    }

    def delete(Long id) {
        def courseInstance = Course.get(id)
        if (!courseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), id])
            redirect(action: "list")
            return
        }

        try {
            courseInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'course.label', default: 'Course'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'course.label', default: 'Course'), id])
            redirect(action: "show", id: id)
        }
    }
}
