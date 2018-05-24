<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- DASHBOARD LEVEL C -->
<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset1"/>
	</legend>
	<display:table name="routesByRegister" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="route.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="route.actived" var="actived"/>
		<display:column property="actived" title="${actived}" sortable="false" />
		
		<spring:message code="route.ratingL" var="ratingL"/>
		<display:column property="ratingL" title="${ratingL}" sortable="false" />
		
		<spring:message code="route.ratingD" var="ratingD"/>
		<display:column property="ratingD" title="${ratingD}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset2"/>
	</legend>
	<display:table name="pilgrimsByRegisterDesc" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}" sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>



<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset3"/>
	</legend>
	<display:table name="routesByVotes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="route.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="route.actived" var="actived"/>
		<display:column property="actived" title="${actived}" sortable="false" />
		
		<spring:message code="route.ratingL" var="ratingL"/>
		<display:column property="ratingL" title="${ratingL}" sortable="false" />
		
		<spring:message code="route.ratingD" var="ratingD"/>
		<display:column property="ratingD" title="${ratingD}" sortable="false" />
	</display:table>
</fieldset>

<!-- DASHBOARD LEVEL B -->
<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset4"></spring:message>
	</legend>
	<jstl:out value="${totalNumber}"></jstl:out>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset5"></spring:message>
	</legend>
	<jstl:out value="${averageNumber}"></jstl:out>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset6"/>
	</legend>
	<display:table name="pilgrimsMoreAnecdotes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}" sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>

<!-- DASHBOARD LEVEL A -->

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset7"></spring:message>
	</legend>
	<jstl:out value="${rate}"></jstl:out>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset8"/>
	</legend>
	<display:table name="pligrimsMoreComplaints" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}" sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>