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

<form:form action="invoice/innkeeper/edit.do" modelAttribute="invoice">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="paidMoment"/>
	<form:hidden path="moment"/>
	
	<acme:textarea code="invoice.description" path="description"/>
	<acme:textbox code="invoice.amount" path="totalCost.value"/>
	<acme:textbox code="invoice.currency" path="totalCost.currency"/>
	<acme:textarea code="invoice.comment" path="comment"/>
	
	<div>
		<form:select path="booking">
			<form:option label="----" value="0" />
			<form:options items="${bookings}" itemLabel="id" itemValue="id" />
			<spring:message code="invoice.booking" />:
		</form:select>
	</div>
	
<input type="submit" name="save"
			value="<spring:message code="invoice.save"/>" /> 
		&nbsp; 
<acme:cancel code="invoice.cancel" url="booking/innkeeper/list.do"/>
</form:form>

