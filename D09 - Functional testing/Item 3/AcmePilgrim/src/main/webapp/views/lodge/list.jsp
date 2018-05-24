<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="lodges" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="lodge.actions" var="action"></spring:message>
	<display:column title="${action}">
	
		<a href="innkeeper/profile.do?lodgeId=${row.id}"><spring:message code="lodge.innkeeper"/></a>
	
		<security:authorize access="hasRole('PILGRIM')">
		
			<br/>
			<a href="booking/innkeeper/book.do?lodgeId=${row.id}"><spring:message code="lodge.book"/></a>
			
		</security:authorize>
	
		<security:authorize access="hasRole('INNKEEPER')">
		
		<br/>
		<a href="lodge/innkeeper/edit.do?lodgeId=${row.id}"><spring:message code="lodge.edit"/></a>
		
		<jstl:if test="${row.bookings.size()!=0}">
			<br/>
			<a href="booking/innkeeper/listByLodge.do?lodgeId=${row.id}"><spring:message code="lodge.bookings"/></a>
		</jstl:if>
		
		<%-- <jstl:if test="${row.request==null}">
			<br/>
			<a href="request/innkeeper/edit.do?lodgeId=${row.id}"><spring:message code="lodge.requestNew"/></a>
		</jstl:if> --%>
		
		<%-- <jstl:if test="${row.request!=null}">
			<br/>
			<a href="request/innkeeper/edit.do?lodgeId=${row.id}"><spring:message code="lodge.requestEdit"/></a>
		</jstl:if> --%>
		
		<jstl:if test="${row.published != true}">
			<br/>
			<a href="lodge/innkeeper/publish.do?lodgeId=${row.id}"><spring:message code="lodge.publish"/></a>
		</jstl:if>
		
		</security:authorize>
	</display:column>

	<spring:message code="lodge.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="true"></display:column>
	
	<spring:message code="lodge.address" var="address"></spring:message>
	<display:column property="address" title="${address}" sortable="false"></display:column>
	
	<spring:message code="lodge.stage" var="stage"></spring:message>
	<display:column property="stage.name" title="${stage}" sortable="true"></display:column>
	
	<spring:message code="lodge.coordinates" var="title"></spring:message>
	<display:column property="coordinates.title" title="${title}" sortable="true"></display:column>	
	
	<spring:message code="lodge.webSite" var="webSite"></spring:message>
	<display:column property="webSite" title="${webSite}" sortable="false"></display:column>
		
	<spring:message code="lodge.contactPhone" var="contactPhone"></spring:message>
	<display:column property="contactPhone" title="${contactPhone}" sortable="false"></display:column>
	
	<%-- <spring:message code="lodge.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column> --%>
	
	<spring:message code="lodge.numberBeds" var="numberBeds"></spring:message>
	<display:column property="numberBeds" title="${numberBeds}" sortable="true "></display:column>
	
	<spring:message code="lodge.pricePerNight.value" var="value"></spring:message>
	<display:column property="pricePerNight.value" title="${value}" sortable="true"></display:column>
	
	<spring:message code="lodge.pricePerNight.currency" var="currency"></spring:message>
	<display:column property="pricePerNight.currency" title="${currency}" sortable="true"></display:column>
	
	<spring:message code="lodge.published" var="published"></spring:message>
	<display:column property="published" title="${published}" sortable="true"></display:column>
	
</display:table>

<%-- <security:authorize access="hasRole('INNKEEPER')">

	<input type="button" name="create" value="<spring:message code="lodge.create"/>"
		onClick="javascript: window.location.replace('lodge/innkeeper/create.do');"/>
		
</security:authorize> --%>