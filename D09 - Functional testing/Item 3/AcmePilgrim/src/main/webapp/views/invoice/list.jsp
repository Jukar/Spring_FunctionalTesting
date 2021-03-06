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

<display:table name="invoices" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<security:authorize access="hasRole('PILGRIM')">
		<display:column>
			<jstl:if test="${row.paidMoment==null}">
				<a href="invoice/pilgrim/pay.do?invoiceId=${row.id}">
					<spring:message code="invoice.pay"></spring:message>
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	<spring:message code="invoice.moment" var="moment"></spring:message>
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy  HH:mm}" sortable="true"></display:column>
	
	<spring:message code="invoice.paidMoment" var="paidMoment"></spring:message>
	<display:column property="paidMoment" title="${paidMoment}" format="{0,date,dd/MM/yyyy  HH:mm}" sortable="true"></display:column>
	
	<spring:message code="invoice.description" var="description"></spring:message>
	<display:column property="description" title="${description}" sortable="false"></display:column>
	
	<spring:message code="invoice.amount" var="totalCost"></spring:message>
	<display:column property="totalCost.value" title="${totalCost}" sortable="false"></display:column>
	<spring:message code="invoice.currency" var="totalCost"></spring:message>
	<display:column property="totalCost.currency" title="${totalCost}" sortable="false"></display:column>
	
	<spring:message code="invoice.comment" var="comment"></spring:message>
	<display:column property="comment" title="${comment}" sortable="false"></display:column>
	
	<spring:message code="invoice.booking" var="booking"></spring:message>
	<display:column property="booking.id" title="${booking}" sortable="false"></display:column>
</display:table>