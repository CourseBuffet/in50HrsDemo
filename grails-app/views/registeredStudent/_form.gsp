<%@ page import="coursebuffet.RegisteredStudent" %>



<div class="fieldcontain ${hasErrors(bean: registeredStudentInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="registeredStudent.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${registeredStudentInstance?.email}"/>
</div>

