
<%@ page import="coursebuffet.RegisteredStudent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'registeredStudent.label', default: 'RegisteredStudent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-registeredStudent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <!--
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                -->
			</ul>
		</div>
		<div id="show-registeredStudent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list registeredStudent">
			
				<g:if test="${registeredStudentInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="registeredStudent.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${registeredStudentInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
			</ol>

            <h1>Saved Courses</h1>
            <ul class="property-list">
                <g:each in="${registeredStudentInstance.savedCourses}" status="i" var="courseInstance">
                    <li class="property-label">
                        <g:link controller='course' action="show" id="${courseInstance.id}">${fieldValue(bean: courseInstance, field: "title")}</g:link>
                        <g:link controller='registeredStudent' action="commitCourse" id="${courseInstance.id}">[ Started? ]</g:link>
                    </li>
                </g:each>
                <g:each in="${registeredStudentInstance.commitedCourses}" status="i" var="courseInstance">
                    <li class="property-label">
                        <g:link controller='course' action="show" id="${courseInstance.id}">${fieldValue(bean: courseInstance, field: "title")}</g:link>
                        <g:link controller='registeredStudent' action="finishCourse" id="${courseInstance.id}">[ Finished? ]</g:link>
                    </li>
                </g:each>
                <g:each in="${registeredStudentInstance.doneCourses}" status="i" var="courseInstance">
                    <li class="property-label">
                        <g:link controller='course' action="show" id="${courseInstance.id}">${fieldValue(bean: courseInstance, field: "title")}</g:link> [ Done! ]
                    </li>
                </g:each>
            </h1>

            <!--
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${registeredStudentInstance?.id}" />
					<g:link class="edit" action="edit" id="${registeredStudentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
            -->
		</div>
	</body>
</html>
