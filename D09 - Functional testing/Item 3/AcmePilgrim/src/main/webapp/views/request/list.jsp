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

<display:table name="requests" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="request.actions" var="action"></spring:message>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column title="action">
		<jstl:if test="${row.status=='PENDING'}">
				<a href="request/administrator/cancel.do?requestId=${row.id}"><spring:message code="request.edit.cancel"></spring:message></a>
				<a href="request/administrator/accept.do?requestId=${row.id}"><spring:message code="request.edit.accept"></spring:message></a>
		</jstl:if>
		<br/>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('INNKEEPER')">
		<display:column title="action">
			<a href="request/innkeeper/edit.do?requestId=${row.id}"><spring:message code="request.edit"></spring:message></a>
		</display:column>
	</security:authorize>
	
	<spring:message code="request.title" var="title"></spring:message>
	<display:column property="title" title="${title}" sortable="false"></display:column>
	
	<spring:message code="request.creationMoment" var="creationMoment"></spring:message>
	<display:column property="creationMoment" title="${creationMoment}" sortable="true" format="{0,date,dd/MM/yyyy  HH:mm}"></display:column>
	
	<spring:message code="request.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column>
	
	<spring:message code="request.status" var="status"></spring:message>
	<display:column property="status" title="${status}" sortable="false"></display:column>
	
	<spring:message code="request.comments" var="comments"></spring:message>
	<display:column property="comments" title="${comments}" sortable="false"></display:column>
		
	<spring:message code="lodge.name" var="name"></spring:message>
	<display:column property="lodge.name" title="${name}" sortable="true"></display:column>
	
</display:table>
	
	<security:authorize access="hasRole('INNKEEPER')">
		<input type="button" name="create" value="<spring:message code="request.create"/>" 
		onclick="javascript: window.location.replace('request/innkeeper/create.do')"/>
	</security:authorize>