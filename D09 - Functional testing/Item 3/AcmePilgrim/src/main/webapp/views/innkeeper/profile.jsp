<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="innkeeper">
	<fieldset>
		<legend align="left">
			<b><spring:message code="innkeeper.personalInfo" /></b>
		</legend>
		
		<acme:formOut code="innkeeper.name" value="${innkeeper.name}" path="name"/>
		<acme:formOut code="innkeeper.surname" value="${innkeeper.surname}" path="surname"/>
		<acme:formOut code="innkeeper.emailAddress" value="${innkeeper.emailAddress}" path="emailAddress"/>
		<acme:formOut code="innkeeper.contactPhone" value="${innkeeper.contactPhone}" path="contactPhone"/>
		<acme:formOut code="innkeeper.url" value="${innkeeper.url}" path="url"/>	
	</fieldset>	
</form:form>


<fieldset>
	<legend align="left">
		<b><spring:message code="innkeeper.lodges" /></b>
	</legend>
	
	<display:table name="innkeeper.lodges" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="lodge.name" var="name"></spring:message>
		<display:column property="name" title="${name}" sortable="true"></display:column>
		
		<spring:message code="lodge.address" var="address"></spring:message>
		<display:column property="address" title="${address}" sortable="false"></display:column>
			
		<spring:message code="lodge.coordinates" var="title"></spring:message>
		<display:column property="coordinates.title" title="${title}" sortable="true"></display:column>	
		
		<spring:message code="lodge.webSite" var="webSite"></spring:message>
		<display:column property="webSite" title="${webSite}" sortable="false"></display:column>
			
		<spring:message code="lodge.contactPhone" var="contactPhone"></spring:message>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false"></display:column>
		
		<%-- <spring:message code="lodge.description" var="description"></spring:message>
		<display:column property="description" title="${description}" sortable="false"></display:column> --%>
		
		<spring:message code="lodge.numberBeds" var="numberBeds"></spring:message>
		<display:column property="numberBeds" title="${numberBeds}" sortable="false"></display:column>
		
		<spring:message code="lodge.pricePerNight.value" var="value"></spring:message>
		<display:column property="pricePerNight.value" title="${value}" sortable="true"></display:column>
		
		<spring:message code="lodge.pricePerNight.currency" var="currency"></spring:message>
		<display:column property="pricePerNight.currency" title="${currency}" sortable="true"></display:column>
	</display:table>
</fieldset>	