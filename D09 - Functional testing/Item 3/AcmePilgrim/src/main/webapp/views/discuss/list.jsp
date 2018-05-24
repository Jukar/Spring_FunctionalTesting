<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<display:table name="discusses" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="discuss.momentDiscuss" var="momentDiscuss"></spring:message>
	<display:column property="momentDiscuss" title="${momentDiscuss}" format="{0,date,dd/MM/yyyy  HH:mm}" sortable="true"></display:column>
	
	<spring:message code="discuss.actor" var="name"></spring:message>
	<display:column property="actor.name" title="${name}" sortable="false"></display:column>
	
	<spring:message code="discuss.message" var="message"></spring:message>
	<display:column  property="message" title="${message}" sortable="false">
	</display:column>
		
</display:table>

<input type="button" name="create" value="<spring:message code="discuss.create"/>" 
	onclick="javascript: window.location.replace('discuss/create.do?complaintId=${complaintId}')"/>
<security:authorize access="hasRole('PILGRIM')">
	<input type="button" name="cancel" value="<spring:message code="discuss.cancel"/>" 
		onclick="javascript: window.location.replace('complaint/pilgrim/list.do')"/>
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
	<input type="button" name="cancel" value="<spring:message code="discuss.cancel"/>" 
		onclick="javascript: window.location.replace('complaint/administrator/list.do')"/>
</security:authorize>
<security:authorize access="hasRole('INNKEEPER')">
	<input type="button" name="cancel" value="<spring:message code="discuss.cancel"/>" 
		onclick="javascript: window.location.replace('complaint/inkeeper/listRefered.do')"/>
</security:authorize>