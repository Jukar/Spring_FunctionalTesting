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

<form:form action="lodge/innkeeper/edit.do" modelAttribute="lodgeForm">
	<form:hidden path="lodgeId"/>

	<acme:textbox code="lodge.name" path="name"/>
	<acme:textarea code="lodge.address" path="address"/>
	
	<acme:textbox code="lodge.coordinates.title" path="coordinates.title"/>
	<acme:textarea code="lodge.coordinates.description" path="coordinates.description"/>
	<acme:textbox code="lodge.coordinates.longitude" path="coordinates.longitude"/>
	<acme:textbox code="lodge.coordinates.latitude" path="coordinates.latitude"/>
	<acme:textbox code="lodge.coordinates.altitude" path="coordinates.altitude"/>
	
	<acme:select code="lodge.stage" items="${stages}" itemLabel="name" path="stage"/>
	
	<acme:textbox code="lodge.webSite" path="webSite"/>
	<acme:textbox code="lodge.contactPhone" path="contactPhone"/>
	<acme:textarea code="lodge.description" path="lodgeDescription"/>
	<acme:textbox code="lodge.numberBeds" path="numberBeds"/>
	<acme:textbox code="lodge.pricePerNight.value" path="pricePerNight.value"/>
	<acme:textbox code="lodge.pricePerNight.currency" path="pricePerNight.currency"/>

	<acme:submit code="lodge.save" name="save"/>
	
<%--<jstl:if test="${lodge.id!=0}">
		<acme:submit code="lodge.delete" name="delete"/>
	</jstl:if>--%>
	 
	<acme:cancel code="lodge.cancel" url="lodge/innkeeper/list.do"/>
	
</form:form>