<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="complaint/pilgrim/edit.do" modelAttribute="complaint">
	<acme:textbox code="complaint.title" path="title"/>
	<acme:textarea code="complaint.description" path="description"/>
	<acme:textbox code="complaint.resolution" path="resolution"/>
		
	<acme:submit name="save" code="complaint.save"/>	
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="complaint.cancel"/>" 
	onclick="javascript: window.location.replace('complaint/pilgrim/list.do')"/>
	
</form:form>