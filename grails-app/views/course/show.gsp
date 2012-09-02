
<%@ page import="coursebuffet.Course" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-course" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!-- li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li -->
				<!-- li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li -->
			</ul>
		</div>
		<div id="show-course" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list course">
			
				<g:if test="${courseInstance?.level}">
				<li class="fieldcontain">
					<span id="level-label" class="property-label"><g:message code="course.level.label" default="Level" /></span>
					
						<span class="property-value" aria-labelledby="level-label"><g:fieldValue bean="${courseInstance}" field="level"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${courseInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="course.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${courseInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${courseInstance?.subject}">
				<li class="fieldcontain">
					<span id="subject-label" class="property-label"><g:message code="course.subject.label" default="Subject" /></span>
					
						<span class="property-value" aria-labelledby="subject-label"><g:fieldValue bean="${courseInstance}" field="subject"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${courseInstance?.offeredBy}">
				<li class="fieldcontain">
					<span id="offeredBy-label" class="property-label"><g:message code="course.offeredBy.label" default="Offered By" /></span>
					
						<span class="property-value" aria-labelledby="offeredBy-label"><a href="${courseInstance?.offeredBy?.website}">${courseInstance?.offeredBy?.encodeAsHTML()}</a></span>
					
				</li>
				</g:if>
			
				<g:if test="${courseInstance?.offeredVia}">
				<li class="fieldcontain">
					<span id="offeredVia-label" class="property-label"><g:message code="course.offeredVia.label" default="Offered Via" /></span>
					
						<span class="property-value" aria-labelledby="offeredVia-label"><a href="${courseInstance?.offeredVia?.website}">${courseInstance?.offeredVia?.encodeAsHTML()}</a></span>
					
				</li>
				</g:if>
			
				<g:if test="${courseInstance?.website}">
				<li class="fieldcontain">
					<span id="website-label" class="property-label"><g:message code="course.website.label" default="website" /></span>
					
						<span class="property-value" aria-labelledby="website-label"><a href="${courseInstance?.website}">${courseInstance?.website?.encodeAsHTML()}</a></span>
					
				</li>
				</g:if>
			
				<g:if test="${courseInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="course.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label">${courseInstance?.description?.encodeAsHTML()}</span>
					
				</li>
				</g:if>
			
			</ol>
            <!--
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${courseInstance?.id}" />
					<g:link class="edit" action="edit" id="${courseInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
            -->
		</div>
	</body>
</html>
