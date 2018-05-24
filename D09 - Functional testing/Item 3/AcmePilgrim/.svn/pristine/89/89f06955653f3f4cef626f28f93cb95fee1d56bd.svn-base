<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="landmarks" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<security:authorize access="hasRole('PILGRIM')">
		<jstl:if test="${requestURI=='landmark/pilgrim/list.do'}">
			<display:column title="${action}" sortable="false">
				<a href="landmark/pilgrim/edit.do?landmarkId=${row.id}"><spring:message code="landmark.edit"/></a>
				<br/>
				<a href="landmark/pilgrim/delete.do?landmarkId=${row.id}"><spring:message code="landmark.delete"/></a>
			</display:column>
		</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('INNKEEPER')">
		<jstl:if test="${requestURI=='landmark/innkeeper/list.do'}">
			<display:column title="${action}" sortable="false">
				<a href="landmark/innkeeper/edit.do?landmarkId=${row.id}"><spring:message code="landmark.edit"/></a>
				<br/>
				<a href="landmark/innkeeper/delete.do?landmarkId=${row.id}"><spring:message code="landmark.delete"/></a>
			</display:column>
		</jstl:if>
	</security:authorize>


	<spring:message code="landmark.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="true"></display:column>
	
	<spring:message code="landmark.lndDescription" var="lndDescription"></spring:message>
	<display:column property="lndDescription" title="${lndDescription}" sortable="false"></display:column>
		
	<spring:message code="landmark.location.longitude" var="longitude"></spring:message>
	<display:column property="location.longitude" title="${longitude}" sortable="true"></display:column>	
	
	<spring:message code="landmark.location.latitude" var="latitude"></spring:message>
	<display:column property="location.latitude" title="${latitude}" sortable="true"></display:column>
	
	<spring:message code="landmark.location.altitude" var="altitude"></spring:message>
	<display:column property="location.altitude" title="${altitude}" sortable="true"></display:column>
	
</display:table>

<security:authorize access="hasRole('PILGRIM')">
	<input type="button" name="create" value="<spring:message code="landmark.create"/>" 
	onclick="javascript: window.location.replace('landmark/pilgrim/create.do')"/>
</security:authorize>
<security:authorize access="hasRole('INNKEEPER')">
	<input type="button" name="create" value="<spring:message code="landmark.create"/>" 
	onclick="javascript: window.location.replace('landmark/innkeeper/create.do')"/>
</security:authorize>