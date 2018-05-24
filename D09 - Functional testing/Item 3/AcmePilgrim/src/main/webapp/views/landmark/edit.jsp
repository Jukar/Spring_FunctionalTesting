<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI }" modelAttribute="landmark">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="actor"/>
	
	<acme:textbox code="landmark.name" path="name"/>
	<acme:textbox code="landmark.lndDescription" path="lndDescription"/>
	<acme:textbox code="landmark.location.title" path="location.title"/>
	<acme:textarea code="landmark.location.description" path="location.description"/>
	<acme:textbox code="landmark.location.longitude" path="location.longitude"/>
	<acme:textbox code="landmark.location.latitude" path="location.latitude"/>
	<acme:textbox code="landmark.location.altitude" path="location.altitude"/>
	<acme:textbox code="landmark.url" path="url"/>
	<acme:select items="${stages}" itemLabel="name" code="landmark.stage" path="stage"/>
	
	
	<acme:submit name="save" code="landmark.save"/>	
	
	<security:authorize access="hasRole('PILGRIM')">
		<acme:cancel url="landmark/pilgrim/list.do" code="landmark.return"/>
	</security:authorize>
	<security:authorize access="hasRole('INNKEEPER')">
		<acme:cancel url="landmark/innkeeper/list.do" code="landmark.return"/>
	</security:authorize>
</form:form>