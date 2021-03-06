<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="request/innkeeper/edit.do" modelAttribute="requestForm">
	<form:hidden path="requestId"/>
	<acme:select path="lodge" code="request.lodge" itemLabel="name" items="${lodges}"/>
	<acme:textbox code="request.title" path="title" />
	<acme:textarea code="request.description" path="description" />
	<acme:textarea code="request.comments" path="comments" />
	<%-- <acme:textbox code="request.status" path="status" readonly="true"/> --%>
	
	<acme:submit name="save" code="request.save"/>
	
	<input type="button" name="cancel" value="<spring:message code="request.cancel"/>"
			onclick="javascript: window.location.replace('request/innkeeper/list.do')" />
</form:form>