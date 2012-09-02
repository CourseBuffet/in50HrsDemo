<%@ page import="coursebuffet.Course" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
		<title><g:message code="default.index.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="index-course" class="content scaffold-index" role="main">
			<h1><g:message code="default.index.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${courseInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${courseInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="GET" action="list" >
				<g:hiddenField name="searchtype" value="detailed" />
				<fieldset class="form">
					<g:render template="lookup_form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="index" class="list" value="${message(code: 'default.button.index.label', default: 'Lookup')}" />
				</fieldset>
			</g:form>

			<g:form method="GET" action="list" >
				<g:hiddenField name="searchtype" value="interest" />
				<fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'subject', 'error')}">
                        <label for="interest"> Interest </label>
                        <g:field id="interest" name="interest" />
                    </div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="index-interest" class="list" value="${message(code: 'default.button.index.label', default: 'Lookup')}" />
				</fieldset>
			</g:form>

            <g:if test="${session.user.viewedCourses}">
            <h1>Courses viewed</h1>
            <div class="nav" role="navigation">
                <ul>
                    <g:each in="${session.user.viewedCourses}" var="courseInstance">
                    <li><g:link action="show" id="${courseInstance.id}">${fieldValue(bean: courseInstance, field: "title")}</g:link></li>
                    </g:each>
                </ul>
            </div>
            </g:if>
		</div>
	</body>
</html>
