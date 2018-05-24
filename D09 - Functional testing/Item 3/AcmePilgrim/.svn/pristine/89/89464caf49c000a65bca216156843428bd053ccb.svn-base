<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="stageRating/pilgrim/edit.do" modelAtribute="stageRating">
	<form:hidden path="assessment.creationMoment"/>

	<acme:textbox code="stageRating.startMoment" path="startMoment"/>
	<acme:textbox code="stageRating.endMoment" path="endMoment"/>
	<acme:textbox code="stageRating.comment" path="comment"/>
	
	<acme:textbox code="stageRating.assessment.ratingLenght" path="assessment.ratingLenght"/>
	<acme:textbox code="stageRating.assessment.ratingDifficulty" path="assessment.ratingDifficulty"/>
	<acme:textbox code="stageRating.assessment.commentAssessment" path="assessment.commentAssessment"/>
	
	<form:label path="register">
		<spring:message code="stageRating.registers"/>:
	</form:label>
	<div>
		<form:select path="register">
			<form:option label="----" value="0" />
			<form:options items="${registers}" itemLabel="id" itemValue="id" />
			<spring:message code="stageRating.registers" />:
		</form:select>
	</div>
	
	<form:label path="stage">
		<spring:message code="stageRating.stages"/>:
	</form:label>
	<div>
		<form:select path="stage">
			<form:option label="----" value="0" />
			<form:options items="${stages}" itemLabel="name" itemValue="id" />
			<spring:message code="stageRating.stages" />:
		</form:select>
	</div>
	
<div>
	<input type="submit" name="save"
		value="<spring:message code="stageRating.save"/>" /> 
	&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="stageRating.cancel"/>"
		onClick="javascript: window.location.replace('route/pilgrim/list.do');" 
	/>
</div>
</form:form>
