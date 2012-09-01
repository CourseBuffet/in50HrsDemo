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
				<fieldset class="form">
					<g:render template="lookup_form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="index" class="list" value="${message(code: 'default.button.index.label', default: 'Lookup')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
