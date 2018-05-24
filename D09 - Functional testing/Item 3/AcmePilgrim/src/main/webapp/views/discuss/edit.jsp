<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="discuss/edit.do" modelAttribute="discuss">

	<form:hidden path="momentDiscuss"/>
	<form:hidden path="actor"/>
	<form:hidden path="complaint"/>
	
	<div>
		<form:label path="message">
			<spring:message code="discuss.message" />:
		</form:label>
		<form:textarea path="message" />
		<form:errors cssClass="error" path="message" />
	</div>

	<div>
		<input type="submit" name="save"
			value="<spring:message code="discuss.create"/>" /> 
		&nbsp; 
		<security:authorize access="hasRole('ADMIN')">
			<input type="button" name="cancel"
				value="<spring:message code="discuss.cancel"/>"
				onClick="javascript: window.location.replace('complaint/administrator/list.do');" />
		</security:authorize>
		<security:authorize access="hasRole('PILGRIM')">
			<input type="button" name="cancel"
				value="<spring:message code="discuss.cancel"/>"
				onClick="javascript: window.location.replace('complaint/pilgrim/list.do');" />
		</security:authorize>
	</div>
</form:form>