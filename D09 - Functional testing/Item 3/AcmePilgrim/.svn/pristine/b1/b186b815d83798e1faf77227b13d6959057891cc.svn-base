<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<display:table name="pilgrims" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<security:authorize access="hasRole('ADMIN')">
			<spring:message code="pilgrim.actions" var="action"></spring:message>
			<display:column title="action">
				<a href="pilgrim/administrator/profile.do?pilgrimId=${row.id}"><spring:message code="pilgrim.details"/></a>
				<br/>
			</display:column>
	</security:authorize>

	<spring:message code="pilgrim.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="false"></display:column>
	
	<spring:message code="pilgrim.surname" var="surname"></spring:message>
	<display:column property="surname" title="${surname}" sortable="false"></display:column>
	
	<spring:message code="pilgrim.emailAddress" var="emailAddress"></spring:message>
	<display:column property="emailAddress" title="${emailAddress}" sortable="false"></display:column>
	
	<spring:message code="pilgrim.contactPhone" var="contactPhone"></spring:message>
	<display:column property="contactPhone" title="${contactPhone}" sortable="false"></display:column>
	
	<spring:message code="pilgrim.url" var="url"></spring:message>
	<display:column property="url" title="${url}" sortable="false"></display:column>
	
	<spring:message code="pilgrim.birthDate" var="birthDate"></spring:message>
	<display:column  property="birthDate" title="${birthDate}" format="{0,date,dd-MM-yyyy  HH:mm}">
	</display:column>
		
	<spring:message code="pilgrim.nationality" var="nationality"></spring:message>
	<display:column property="nationality" title="${nationality}" sortable="false"></display:column>	
	
</display:table>



<input type="text" value="" id="textSearch"/>
<input type="button" id="buttonSearch" value="<spring:message code="pilgrim.search"/>" />


<script type="text/javascript">
	$(document).ready(function(){
		$("#buttonSearch").click(function(){
			window.location.replace('pilgrim/administrator/listByKeyword.do?keyword=' + $("#textSearch").val());
		});
		$("#buttonSearch").onsubmit(function(){
			window.location.replace('pilgrim/administrator/listByKeyword.do?keyword=' + $("#textSearch").val());
		});
	});
</script>
