<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="administrator">
<fieldset>
	<legend align="left">
		<b><spring:message code="administrator.personalInfo" /></b>
	</legend>
	
	<form:label path="name">
		<b><spring:message code="administrator.name"/>:</b>
	</form:label>
	<jstl:out value="${administrator.name}" />
	<br/>
	
	<form:label path="surname">
		<b><spring:message code="administrator.surname"/>:</b>
	</form:label>
	<jstl:out value="${administrator.surname}" />
	<br/>

	<form:label path="emailAddress">
		<b><spring:message code="administrator.emailAddress"/>:</b>
	</form:label>
	<jstl:out value="${administrator.emailAddress}" />
	<br/>
	
	<form:label path="contactPhone">
		<b><spring:message code="administrator.contactPhone"/>:</b>
	</form:label>
	<jstl:out value="${administrator.contactPhone}" />
	<br/>
	
	<form:label path="url">
		<b><spring:message code="administrator.url"/>:</b>
	</form:label>
	<jstl:out value="${administrator.url}" />
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