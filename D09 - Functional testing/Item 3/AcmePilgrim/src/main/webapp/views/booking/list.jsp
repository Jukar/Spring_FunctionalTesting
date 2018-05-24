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

<display:table name="bookings" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<security:authorize access="hasRole('PILGRIM')">
		<display:column>
			<a href="booking/pilgrim/edit.do?bookingId=${row.id}"><spring:message code="booking.edit"></spring:message></a>
		</display:column>
	</security:authorize>

	<spring:message code="booking.creationMoment" var="creationMoment"></spring:message>
	<display:column property="creationBookMoment" title="${creationMoment}" format="{0,date,dd/MM/yyyy  HH:mm}" sortable="true"></display:column>
	
	<spring:message code="booking.arrivalDate" var="arrivalDate"></spring:message>
	<display:column property="arrivalDate" title="${arrivalDate}" format="{0,date,dd/MM/yyyy  HH:mm}" sortable="true"></display:column>
	
	<spring:message code="booking.numberOfNights" var="numberOfNights"></spring:message>
	<display:column property="numberOfNights" title="${numberOfNights}" sortable="false"></display:column>
	
	<spring:message code="booking.numberOfBeds" var="numberOfBeds"></spring:message>
	<display:column property="numberOfBeds" title="${numberOfBeds}" sortable="false"></display:column>
	
	<spring:message code="booking.pricePerNight" var="pricePerNight"></spring:message>
	<display:column property="pricePerNight.value" title="${pricePerNight}" sortable="false"></display:column>
	
	<spring:message code="booking.comments" var="bookComments"></spring:message>
	<display:column property="bookComments" title="${bookComments}" sortable="false"></display:column>
</display:table>

<security:authorize access="hasRole('INNKEEPER')">
	<input type="submit" name="newInvoice"
		value="<spring:message code="invoice.edit"/>"
		onClick="javascript: window.location.replace('invoice/innkeeper/create.do');"
	/>
</security:authorize>