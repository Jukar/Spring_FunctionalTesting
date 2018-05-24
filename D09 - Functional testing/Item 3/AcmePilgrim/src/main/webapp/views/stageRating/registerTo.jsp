<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="routes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="route.actions" var="action"></spring:message>
		<display:column title="action">
			<a href="item/supplier/edit.do?itemId=${row.id}"><spring:message code="route.details"/></a>
			<br/>
		</display:column>
	
		<jstl:if test="${requestURI=='/item/list'}">
			<spring:message code="route.actions" var="action"></spring:message>
			<display:column title="test">
				<a href="item/supplier/edit.do?itemId=${row.id}"><spring:message code="route.details"/></a>
				<br/>
			</display:column>
		</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('PILGRIM')">
		<spring:message code="route.actions" var="action"></spring:message>
		<display:column title="action">
			<a href="route/pilgrim/registerTo.do?routeId=${row.id}"><spring:message code="route.register"/></a>
			<br/>
		</display:column>
	</security:authorize>
	

	<spring:message code="route.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="true"></display:column>
	
	<spring:message code="route.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column>
	
	<spring:message code="route.ratingL" var="ratingL"></spring:message>
	<display:column property="ratingL" title="${ratingL}" sortable="true"></display:column>
		
	<spring:message code="route.ratingD" var="ratingD"></spring:message>
	<display:column property="ratingD" title="${ratingD}" sortable="true"></display:column>	
</display:table>
