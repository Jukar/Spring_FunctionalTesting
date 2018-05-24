<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="anecdote/pilgrim/edit.do" modelAttribute="anecdoteForm">
	<acme:textbox code="anecdote.title" path="title"/>
	<acme:textarea code="anecdote.description" path="description"/>
	<form:label path="route"><spring:message code="anecdote.route" /></form:label>
	<form:select path="route">
		<form:option label="----" value="0" />
		<form:options items="${routes}" itemLabel="name" itemValue="id" />
		<spring:message code="anecdote.route" />:
	</form:select>
	
	<acme:submit name="save" code="anecdote.save"/>	
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="anecdote.cancel"/>" 
	onclick="javascript: window.location.replace('anecdote/pilgrim/list.do')"/>
	
</form:form> 

<%-- <form:form action="anecdote/pilgrim/edit.do" modelAttribute="anecdote">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="creationMoment"/>
	
	<form:label path="title">
		<spring:message code="anecdote.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br/>
	
	<form:label path="description">
		<spring:message code="anecdote.description"/>
	</form:label>
	<form:input path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br/>
	
	<form:label path="eventMoment">
		<spring:message code="anecdote.eventMoment"/>
	</form:label>
	<form:input path="eventMoment"/>
	<form:errors cssClass="error" path="eventMoment"/>
	<br/>
	
	<form:select path="route">
		<form:option label="----" value="0" />
		<form:options items="${routes}" itemLabel="id" itemValue="name" />
		<spring:message code="anecdote.route" />:
	</form:select>

	<div>
		<input type="submit" name="save"
			value="<spring:message code="anecdote.create"/>" /> 
			&nbsp; 
		<input type="button" name="cancel"
			value="<spring:message code="anecdote.cancel"/>"
			onClick="javascript: window.location.replace('anecdote/pilgrim/list.do');" />
	</div>
</form:form> --%>