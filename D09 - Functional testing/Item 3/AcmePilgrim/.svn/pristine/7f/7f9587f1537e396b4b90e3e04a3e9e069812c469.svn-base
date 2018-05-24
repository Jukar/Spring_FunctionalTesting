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

<form:form action="lodge/innkeeper/edit.do" modelAttribute="LodgeAssessmentForm">

	<form:hidden path="bookingId"/>

	<acme:textbox code="booking.lodgeAssessment.comments" path="comments"/>
	<acme:textbox code="booking.lodgeAssessment.locationRate" path="locationRate"/>
	<acme:textbox code="booking.lodgeAssessment.roomsRate" path="roomsRate"/>
	<acme:textbox code="booking.lodgeAssessment.serviceRate" path="serviceRate"/>
	<acme:textbox code="booking.lodgeAssessment.priceRate" path="priceRate"/>

	<acme:submit code="lodge.save" name="save"/>
	<acme:cancel code="lodge.cancel" url="lodge/list.do"/>
</form:form>