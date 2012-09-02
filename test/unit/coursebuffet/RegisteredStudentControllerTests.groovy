package coursebuffet



import org.junit.*
import grails.test.mixin.*

@TestFor(RegisteredStudentController)
@Mock(RegisteredStudent)
class RegisteredStudentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/registeredStudent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.registeredStudentInstanceList.size() == 0
        assert model.registeredStudentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.registeredStudentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.registeredStudentInstance != null
        assert view == '/registeredStudent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/registeredStudent/show/1'
        assert controller.flash.message != null
        assert RegisteredStudent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/registeredStudent/list'

        populateValidParams(params)
        def registeredStudent = new RegisteredStudent(params)

        assert registeredStudent.save() != null

        params.id = registeredStudent.id

        def model = controller.show()

        assert model.registeredStudentInstance == registeredStudent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/registeredStudent/list'

        populateValidParams(params)
        def registeredStudent = new RegisteredStudent(params)

        assert registeredStudent.save() != null

        params.id = registeredStudent.id

        def model = controller.edit()

        assert model.registeredStudentInstance == registeredStudent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/registeredStudent/list'

        response.reset()

        populateValidParams(params)
        def registeredStudent = new RegisteredStudent(params)

        assert registeredStudent.save() != null

        // test invalid parameters in update
        params.id = registeredStudent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/registeredStudent/edit"
        assert model.registeredStudentInstance != null

        registeredStudent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/registeredStudent/show/$registeredStudent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        registeredStudent.clearErrors()

        populateValidParams(params)
        params.id = registeredStudent.id
        params.version = -1
        controller.update()

        assert view == "/registeredStudent/edit"
        assert model.registeredStudentInstance != null
        assert model.registeredStudentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/registeredStudent/list'

        response.reset()

        populateValidParams(params)
        def registeredStudent = new RegisteredStudent(params)

        assert registeredStudent.save() != null
        assert RegisteredStudent.count() == 1

        params.id = registeredStudent.id

        controller.delete()

        assert RegisteredStudent.count() == 0
        assert RegisteredStudent.get(registeredStudent.id) == null
        assert response.redirectedUrl == '/registeredStudent/list'
    }
}
