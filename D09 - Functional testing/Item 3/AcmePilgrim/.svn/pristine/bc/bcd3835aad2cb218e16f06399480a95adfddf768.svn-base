<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="anecdote/pilgrim/editUpdate.do" commandName="anecdote">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="creationMoment"/>
	<form:hidden path="pilgrim"/>
	
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

	<div>
		<input type="submit" name="save"
			value="<spring:message code="anecdote.create"/>" /> 
			&nbsp; 
		<input type="submit" name="delete"
			value="<spring:message code="anecdote.delete"/>"
			onClick="javascript: window.location.replace('anecdote/pilgrim/list.do');"
		/>
		&nbsp;
		<input type="button" name="cancel"
			value="<spring:message code="anecdote.cancel"/>"
			onClick="javascript: window.location.replace('anecdote/pilgrim/list.do');" />
	</div>
</form:form>