<%@ page import="coursebuffet.Course" %>



<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'level', 'error')}">
	<label for="level">
		<g:message code="course.level.label" default="Level" />
	</label>
	<g:field name="level" type="number" value="101"/>
</div>

<!-- div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="course.title.label" default="Title" />
	</label>
	<g:textField name="title"/>
</div -->

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'subject', 'error')}">
	<label for="subject">
		<g:message code="course.subject.label" default="Subject" />
	</label>
	<g:select id="subject" name="subject.id" from="${coursebuffet.Subject.list()}" optionKey="id" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'offeredBy', 'error')}">
	<label for="offeredBy">
		<g:message code="course.offeredBy.label" default="Offered By" />
	</label>
	<g:select id="offeredBy" name="offeredBy.id" from="${coursebuffet.University.list()}" optionKey="id" class="many-to-one"/>
</div>

<!-- div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'offeredVia', 'error')}">
	<label for="offeredVia">
		<g:message code="course.offeredVia.label" default="Offere Via" />
	</label>
	<g:select id="offeredVia" name="offeredVia.id" from="${coursebuffet.Provider.list()}" optionKey="id" class="many-to-one"/>
</div -->

