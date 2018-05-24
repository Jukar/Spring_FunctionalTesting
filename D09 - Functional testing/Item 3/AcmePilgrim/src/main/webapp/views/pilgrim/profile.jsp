<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="pilgrim">
<fieldset>
	<legend align="left">
		<b><spring:message code="pilgrim.personalInfo" /></b>
	</legend>
	
	<form:label path="name">
		<b><spring:message code="pilgrim.name"/>:</b>
	</form:label>
	<jstl:out value="${pilgrim.name}" />
	<br/>
	
	<form:label path="surname">
		<b><spring:message code="pilgrim.surname"/>:</b>
	</form:label>
	<jstl:out value="${pilgrim.surname}" />
	<br/>

	<form:label path="emailAddress">
		<b><spring:message code="pilgrim.emailAddress"/>:</b>
	</form:label>
	<jstl:out value="${pilgrim.emailAddress}" />
	<br/>
	
	<form:label path="contactPhone">
		<b><spring:message code="pilgrim.contactPhone"/>:</b>
	</form:label>
	<jstl:out value="${pilgrim.contactPhone}" />
	<br/>
	
	<form:label path="url">
		<b><spring:message code="pilgrim.url"/>:</b>
	</form:label>
	<jstl:out value="${pilgrim.url}" />
	<br/>

	<form:label path="birthDate">
		<b><spring:message code="pilgrim.birthDate"/>:</b>
	</form:label>
	<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${pilgrim.birthDate}" />
	<br/>
		
	<form:label path="nationality">
		<b><spring:message code="pilgrim.nationality"/>:</b>
	</form:label>
	<jstl:out value="${pilgrim.nationality}" />
	<br/>

	
</fieldset>	

</form:form>
<display:table name="routes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="route.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="false"></display:column>
	
	<spring:message code="route.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column>
	
	<spring:message code="route.ratingL" var="ratingL"></spring:message>
	<display:column property="ratingL" title="${ratingL}" sortable="false"></display:column>
		
	<spring:message code="route.ratingD" var="ratingD"></spring:message>
	<display:column property="ratingD" title="${ratingD}" sortable="false"></display:column>	
</display:table>