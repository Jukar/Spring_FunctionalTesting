<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 <fieldset>
	<legend align="left">
		<spring:message code="dashboard.keeper.fieldset0"/>
	</legend>
	<display:table name="pilgrimsWithBookings" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>


 <fieldset>
	<legend align="left">
		<spring:message code="dashboard.keeper.fieldset1"/>
	</legend>
	<display:table name="pilgrimWithMoreBookings" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.keeper.fieldset2"/>
	</legend>
	<display:table name="pilgrimByBirthdayDate" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" format="{0,date,dd/MM/yyyy  HH:mm}" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>


<fieldset>
	<legend align="left">
		<spring:message code="dashboard.keeper.fieldset3"/>
	</legend>
	<display:table name="lodgesByBooking" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="lodge.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="lodge.address" var="address"/>
		<display:column property="address" title="${address}" sortable="false" />
		
		<spring:message code="lodge.numberBeds" var="numberBeds"/>
		<display:column property="numberBeds" title="${numberBeds}" sortable="false" />
		
		<spring:message code="lodge.pricePerNight.value" var="value"></spring:message>
		<display:column property="pricePerNight.value" title="${value}" sortable="true"></display:column>
	
		<spring:message code="lodge.pricePerNight.currency" var="currency"></spring:message>
		<display:column property="pricePerNight.currency" title="${currency}" sortable="true"></display:column>
	
		<spring:message code="lodge.published" var="published"/>
		<display:column property="published" title="${published}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.keeper.fieldset4"/>
	</legend>
	<display:table name="lodgesByRating" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="lodge.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="lodge.address" var="address"/>
		<display:column property="address" title="${address}" sortable="false" />
		
		<spring:message code="lodge.numberBeds" var="numberBeds"/>
		<display:column property="numberBeds" title="${numberBeds}" sortable="false" />
		
		<spring:message code="lodge.pricePerNight.value" var="value"></spring:message>
		<display:column property="pricePerNight.value" title="${value}" sortable="true"></display:column>
	
		<spring:message code="lodge.pricePerNight.currency" var="currency"></spring:message>
		<display:column property="pricePerNight.currency" title="${currency}" sortable="true"></display:column>
	
		<spring:message code="lodge.published" var="published"/>
		<display:column property="published" title="${published}" sortable="false" />
	</display:table>
</fieldset>


<fieldset>
	<legend align="left">
		<spring:message code="dashboard.keeper.fieldset5"/>
	</legend>
	<display:table name="lodgesByPrice" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="lodge.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="lodge.address" var="address"/>
		<display:column property="address" title="${address}" sortable="false" />
		
		<spring:message code="lodge.numberBeds" var="numberBeds"/>
		<display:column property="numberBeds" title="${numberBeds}" sortable="false" />
		
		<spring:message code="lodge.pricePerNight" var="pricePerNight"/>
		<display:column property="pricePerNight" title="${pricePerNight}" sortable="false" />
		
		<spring:message code="lodge.published" var="published"/>
		<display:column property="published" title="${published}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.keeper.fieldset6"></spring:message>
	</legend>
	<jstl:out value="${occupancyRate}"></jstl:out>
</fieldset>
