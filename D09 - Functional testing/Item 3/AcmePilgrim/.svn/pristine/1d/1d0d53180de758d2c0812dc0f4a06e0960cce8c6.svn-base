<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<display:table name="anecdotes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<security:authorize access="hasRole('PILGRIM')">
		<jstl:if test="${requestURI=='anecdote/pilgrim/list.do'}">
			<display:column title="${action}" sortable="false">
				<a href="anecdote/pilgrim/editUpdate.do?anecdoteId=${row.id}"><spring:message code="anecdote.edit"/></a>
				<br/>
			</display:column>
		</jstl:if>
	</security:authorize>

	<spring:message code="anecdote.title" var="title"></spring:message>
	<display:column property="title" title="${title}" sortable="false"></display:column>
	
	<spring:message code="anecdote.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column>
	
	<spring:message code="anecdote.creationMoment" var="creationMoment"></spring:message>
	<display:column  property="creationMoment" title="${creationMoment}" format="{0,date,dd-MM-yyyy  HH:mm}">
	</display:column>
		
	<spring:message code="anecdote.pilgrim.name" var="pilgrim.name"></spring:message>
	<display:column property="pilgrim.name" title="${pilgrim.name}" sortable="false"></display:column>	
	
	<spring:message code="anecdote.route.name" var="route.name"></spring:message>
	<display:column property="route.name" title="${route.name}" sortable="false"></display:column>
	
		
</display:table>
<security:authorize access="hasRole('PILGRIM')">
		<input type="button" name="create" value="<spring:message code="anecdote.create"/>" 
		onclick="javascript: window.location.replace('anecdote/pilgrim/create.do')"/>
	</security:authorize>
