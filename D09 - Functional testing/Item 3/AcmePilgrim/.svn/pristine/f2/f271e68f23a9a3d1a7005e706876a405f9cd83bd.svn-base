<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="routes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="route.actions" var="action"></spring:message>
	<display:column title="action">
		<a href="stage/listByRoute.do?routeId=${row.id}"><spring:message code="route.stages"/></a>
		<br/>
		
	<security:authorize access="hasRole('ADMIN')">
		<a href="route/administrator/edit.do?routeId=${row.id}"><spring:message code="route.edit"/></a>
		<br/>
	</security:authorize>
	
	<security:authorize access="hasRole('PILGRIM')">
		<jstl:if test="${requestURI=='route/list.do'}">
		<a href="route/pilgrim/registerOnRoute.do?routeId=${row.id}"><spring:message code="route.register"/></a>
		<br/>
		</jstl:if>

		<jstl:if test="${requestURI=='route/pilgrim/list.do'}">
		<a href="stageRating/pilgrim/listByRoute.do?routeId=${row.id}"><spring:message code="route.assessments"/></a>
		<br/>
		</jstl:if>
		
		<jstl:if test="${requestURI=='route/list.do'}">
		<a href="stageRating/pilgrim/edit.do?routeId=${row.id}"><spring:message code="route.assess"/></a>
		<br/>
		</jstl:if>
	</security:authorize>
	</display:column>

	<spring:message code="route.name" var="name"></spring:message>
	<display:column property="name" title="${name}" sortable="true"></display:column>
	
	<spring:message code="route.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column>
	
	<spring:message code="route.ratingL" var="ratingL"></spring:message>
	<display:column property="ratingL" title="${ratingL}" sortable="true"></display:column>
		
	<spring:message code="route.ratingD" var="ratingD"></spring:message>
	<display:column property="ratingD" title="${ratingD}" sortable="true"></display:column>	
</display:table>


<security:authorize access="hasRole('ADMIN')">

	<input type="button" name="create" value="<spring:message code="route.create"/>"
		onClick="javascript: window.location.replace('route/administrator/create.do');"/>
		
</security:authorize>

<input type="text" value="" id="textSearch"/>
<input type="button" id="buttonSearch" value="<spring:message code="route.search"/>" />

<script type="text/javascript">
	$(document).ready(function(){
		$("#buttonSearch").click(function(){
			window.location.replace('route/listByKeyword.do?keyword=' + $("#textSearch").val());
		});
		$("#buttonSearch").onsubmit(function(){
			window.location.replace('route/listByKeyword.do?keyword=' + $("#textSearch").val());
		});
	});
</script>

<%-- Seria lo suyo permitir buscar por mis propias Rutas
			<jstl:if test="${requestURI=='/route/pilgrim/list'}">
				window.location.replace('route/pilgrim/listByKeyword.do?keyword=' + $("#textSearch").val());
			</jstl:if>
			<jstl:if test="${requestURI=='/route/list'}">
				window.location.replace('route/listByKeyword.do?keyword=' + $("#textSearch").val());
			</jstl:if> --%>