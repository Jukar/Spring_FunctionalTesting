<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="stageRatings" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="stageRating.actions" var="action"></spring:message>
	
	
	<spring:message code="stageRating.startMoment" var="startMoment"></spring:message>
	<display:column property="startMoment" title="${startMoment}" sortable="true"></display:column>
	
	<spring:message code="stageRating.endMoment" var="endMoment"></spring:message>
	<display:column property="endMoment" title="${endMoment}" sortable="true"></display:column>
	
	<spring:message code="stageRating.comment" var="comment"></spring:message>
	<display:column property="comment" title="${comment}" sortable="true"></display:column>
	
	<spring:message code="stageRating.assessment.ratingLenght" var="assessment.ratingLenght"></spring:message>
	<display:column property="assessment.ratingLenght" title="${assessment.ratingLenght}" sortable="true"></display:column>
	
	<spring:message code="stageRating.assessment.ratingDifficulty" var="assessment.ratingDifficulty"></spring:message>
	<display:column property="assessment.ratingDifficulty" title="${assessment.ratingDifficulty}" sortable="true"></display:column>
	
</display:table>
