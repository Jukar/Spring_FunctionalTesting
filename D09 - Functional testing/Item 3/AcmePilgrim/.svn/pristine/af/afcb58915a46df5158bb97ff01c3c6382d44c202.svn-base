<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="stages" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="stage.actions" var="action"></spring:message>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column title="action">
		<a href="stage/administrator/edit.do?stageId=${row.id}"><spring:message code="stage.edit"/></a>
		<br/>
	</display:column>
	</security:authorize>
	
	
	

	<spring:message code="stage.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="true"></display:column>
	
	<%-- <spring:message code="stage.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column> --%>
	
	<spring:message code="stage.origin" var="origin"></spring:message>
	<display:column property="origin.title" title="${origin}" sortable="true"></display:column>
	
	<spring:message code="stage.destination" var="destination"></spring:message>
	<display:column property="destination.title" title="${destination}" sortable="true"></display:column>
	
	<spring:message code="stage.lenghtKm" var="lenghtKm"></spring:message>
	<display:column property="lenghtKm" title="${lenghtKm}" sortable="true"></display:column>
	
	<spring:message code="stage.lenghtMi" var="lenghtMi"></spring:message>
	<display:column property="lenghtMi" title="${lenghtMi}" sortable="true"></display:column>
	
	<spring:message code="stage.difficultyLevel" var="difficultyLevel"></spring:message>
	<display:column property="difficultyLevel" title="${difficultyLevel}" sortable="true"></display:column>
	
	<%-- <spring:message code="stage.briefTextDescription" var="briefTextDescription"></spring:message>
	<display:column property="briefTextDescription" title="${briefTextDescription}" sortable="false">
	</display:column>--%>
	
	<spring:message code="stage.ratingL" var="ratingL"></spring:message>
	<display:column property="ratingL" title="${ratingL}" sortable="true"></display:column>
		
	<spring:message code="stage.ratingD" var="ratingD"></spring:message>
	<display:column property="ratingD" title="${ratingD}" sortable="true"></display:column>
</display:table>


<%-- <security:authorize access="hasRole('ADMIN')">

	<input type="button" name="create" value="<spring:message code="stage.create"/>"
		onClick="javascript: window.location.replace('stage/administrator/create.do');"/>
		
</security:authorize>


<input type="text" value="" id="textSearch"/>
<input type="button" id="buttonSearch" value="<spring:message code="stage.search"/>" />

<script type="text/javascript">
	$(document).ready(function(){
		$("#buttonSearch").click(function(){
			window.location.replace('stage/listByKeyword.do?keyword=' + $("#textSearch").val());
		});
		$("#buttonSearch").onsubmit(function(){
			window.location.replace('stage/listByKeyword.do?keyword=' + $("#textSearch").val());
		});
	});
</script> --%>