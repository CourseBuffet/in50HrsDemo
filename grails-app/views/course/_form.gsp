<%@ page import="coursebuffet.Course" %>



<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'level', 'error')} required">
	<label for="level">
		<g:message code="course.level.label" default="Level" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="level" type="number" value="${courseInstance.level}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="course.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${courseInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'subject', 'error')} required">
	<label for="subject">
		<g:message code="course.subject.label" default="Subject" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="subject" name="subject.id" from="${coursebuffet.Subject.list()}" optionKey="id" required="" value="${courseInstance?.subject?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'offeredBy', 'error')} required">
	<label for="offeredBy">
		<g:message code="course.offeredBy.label" default="Offered By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="offeredBy" name="offeredBy.id" from="${coursebuffet.University.list()}" optionKey="id" required="" value="${courseInstance?.offeredBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'offeredVia', 'error')} required">
	<label for="offeredVia">
		<g:message code="course.offeredVia.label" default="Offere Via" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="offeredVia" name="offeredVia.id" from="${coursebuffet.Provider.list()}" optionKey="id" required="" value="${courseInstance?.offeredVia?.id}" class="many-to-one"/>
</div>

