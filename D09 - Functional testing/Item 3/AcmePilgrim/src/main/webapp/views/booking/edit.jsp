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

<form:form action="booking/pilgrim/edit.do" modelAttribute="bookingForm">
	<form:hidden path="bookingId"/>

	<acme:select path="lodge" code="booking.lodge" itemLabel="name" items="${lodges}"/>
	
	<acme:textbox code="booking.arrivalDate" path="arrivalDate" />
	<acme:textbox code="booking.numberOfNights" path="numberOfNights"/>
	<acme:textbox code="booking.numberOfBeds" path="numberOfBeds"/>
	
	<acme:textbox code="booking.pricePerNight.value" path="pricePerNight.value"/>
	<acme:textbox code="booking.pricePerNight.currency" path="pricePerNight.currency" />
	
	<acme:textarea code="booking.bookComments" path="bookComments"/>
	
	<acme:submit code="booking.save" name="save"/>
	&nbsp;
	<acme:cancel code="booking.cancel" url="booking/pilgrim/list.do"/>
	
</form:form>