
<%@ page import="coursebuffet.Course" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-course" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!-- li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li -->
			</ul>
		</div>
		<div id="list-course" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="level" title="${message(code: 'course.level.label', default: 'Level')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'course.title.label', default: 'Title')}" />
					
						<th><g:message code="course.subject.label" default="Subject" /></th>
					
						<th><g:message code="course.offeredBy.label" default="Offered By" /></th>
					
						<th><g:message code="course.offeredVia.label" default="Offered Via" /></th>
					
						<th><g:message code="course.savetoprofile.label" default="Save to profile" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${courseInstanceList}" status="i" var="courseInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: courseInstance, field: "level")}</td>
					
						<td><g:link action="show" id="${courseInstance.id}">${fieldValue(bean: courseInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: courseInstance, field: "subject")}</td>
					
						<td>${fieldValue(bean: courseInstance, field: "offeredBy")}</td>
					
						<td>${fieldValue(bean: courseInstance, field: "offeredVia")}</td>
					
						<td><g:link controller="registeredStudent" action="addCourse" id="${courseInstance.id}">Save to profile</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${courseInstanceTotal}" params="${params}"/>
			</div>
		</div>
	</body>
</html>
