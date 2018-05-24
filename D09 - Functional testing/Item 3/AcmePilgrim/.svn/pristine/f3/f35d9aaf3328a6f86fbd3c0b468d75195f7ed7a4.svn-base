<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<display:table name="complaints" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
 	<spring:message code="complaint.actions" var="Actions"></spring:message>
	<display:column title="action">
		<jstl:if test="${row.status == 'OPEN'}">
			<a href="discuss/create.do?complaintId=${row.id}">
			<spring:message code="complaint.discuss"/></a> 
			<br/>
		</jstl:if>
	<security:authorize access="hasRole('PILGRIM')">
		<jstl:if test="${row.status == 'OPEN'}">
			<a href="complaint/pilgrim/cancel.do?complaintId=${row.id}">
			<spring:message code="complaint.cancel"/></a> 
			<br/>
		</jstl:if>
	</security:authorize>
		<security:authorize access="hasRole('ADMIN')">
			<jstl:if test="${row.status == 'OPEN'}">
				<jstl:if test="${row.administrator != null}">
					<a href="complaint/administrator/close.do?complaintId=${row.id}">
					<spring:message code="complaint.close"/></a> 
					<br/>
				</jstl:if>
				<jstl:if test="${row.administrator == null}">
					<a href="complaint/administrator/handle.do?complaintId=${row.id}">
					<spring:message code="complaint.handle"/></a> 
					<br/>
				</jstl:if>
			</jstl:if>
		</security:authorize>
	</display:column>
	
	
	<spring:message code="complaint.title" var="title"></spring:message>
	<display:column property="title" title="${title}" sortable="false"></display:column>
	
	<spring:message code="complaint.creationMoment" var="creationMoment"></spring:message>
	<display:column property="creationMoment" title="${creationMoment}" format="{0,date,dd-MM-yyyy  HH:mm}" sortable="false"></display:column>
	
	<spring:message code="complaint.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column>
	
	<spring:message code="complaint.resolution" var="resolution"></spring:message>
	<display:column property="resolution" title="${resolution}" sortable="false"></display:column>
	
	<spring:message code="complaint.status" var="status"></spring:message>
	<display:column property="status" title="${status}" sortable="false"></display:column>	
	
	<spring:message code="complaint.administrator.name" var="administrator.name"></spring:message>
	<display:column property="administrator.name" title="${administrator.name}" sortable="false"></display:column>

		
</display:table>
<security:authorize access="hasRole('PILGRIM')">
	<input type="button" name="create" value="<spring:message code="complaint.create"/>" 
	onclick="javascript: window.location.replace('complaint/pilgrim/create.do')"/>
</security:authorize>