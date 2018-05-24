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

<form:form action="stage/administrator/edit.do" modelAtribute="stage">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="stageRatings" />

	<acme:textbox code="stage.name" path="name"/>
	<acme:textarea code="stage.description" path="description"/>
	
	<acme:textbox code="stage.origin.title" path="origin.title"/>
	<acme:textbox code="stage.origin.description" path="origin.description"/>
	<acme:textbox code="stage.origin.longitude" path="origin.longitude"/>
	<acme:textbox code="stage.origin.latitude" path="origin.latitude"/>
	<acme:textbox code="stage.origin.altitude" path="origin.altitude"/>
	
	<acme:textbox code="stage.destination.title" path="destination.title"/>
	<acme:textbox code="stage.destination.description" path="destination.description"/>
	<acme:textbox code="stage.destination.longitude" path="destination.longitude"/>
	<acme:textbox code="stage.destination.latitude" path="destination.latitude"/>
	<acme:textbox code="stage.destination.altitude" path="destination.altitude"/>
	
	<acme:textbox code="stage.lenghtKm" path="lenghtKm"/>
	<acme:textbox code="stage.difficultyLevel" path="difficultyLevel"/>
	<acme:textbox code="stage.briefTextDescription" path="briefTextDescription"/>
	

	<input type="submit" name="save" value="<spring:message code="stage.save"/>" />
	<br />
	
	<jstl:if test="${stage.id!=0}">
		<input type="submit" name="delete" value="<spring:message code="stage.delete"/>"
			onclick="return confirm('<spring:message code="stage.confirm.delete"/>')"
				 />
		<br />
	</jstl:if>
	
	<input type="button" name="cancel" value="<spring:message code="stage.cancel"/>"
			onclick="javascript: window.location.replace('stage/list.do')" />
</form:form>