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

<form:form action="route/administrator/edit.do" modelAtribute="route">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="stageOrders" />
	<form:hidden path="actived" />

	<acme:textbox code="route.name" path="name"/>
	<acme:textbox code="route.description" path="description"/>
	
	
	

	<input type="submit" name="save" value="<spring:message code="route.save"/>" />
	<br />
	
	<jstl:if test="${route.id!=0}">
		<input type="submit" name="delete" value="<spring:message code="route.delete"/>"
			onclick="return confirm('<spring:message code="route.confirm.delete"/>')"
				 />
		<br />
	</jstl:if>
	
	<input type="button" name="cancel" value="<spring:message code="route.cancel"/>"
			onclick="javascript: window.location.replace('route/list.do')" />
</form:form>