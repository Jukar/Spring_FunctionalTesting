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

<form:form action="pilgrim/register.do" modelAtribute="pilgrimForm">
<fieldset>
	<legend align="left">
		<spring:message code="pilgrim.userAccount" />
	</legend>	
	<acme:textbox code="pilgrim.userAccount.username" path="userName"/>
	<acme:password code="pilgrim.userAccount.password" path="password"/>
	<acme:password code="pilgrim.userAccount.confirmPassword" path="confirmPassword"/>
</fieldset>
<fieldset>
	<legend align="left">
		<spring:message code="pilgrim.personalInfo" />
	</legend>
		
	<acme:textbox code="pilgrim.name" path="name"/>
	<acme:textbox code="pilgrim.surname" path="surname"/>
	<acme:textbox code="pilgrim.emailAddress" path="emailAddress"/>
	<acme:textbox code="pilgrim.contactPhone" path="contactPhone"/>
	<acme:textbox code="pilgrim.url" path="url"/>
	<form:label path="birthDate">
		<spring:message code="pilgrim.birthDate" />
	</form:label>
	<form:input path="birthDate"/>
	<form:errors path="birthDate" cssClass="error" />
	<acme:textbox code="pilgrim.nationality" path="nationality"/>
</fieldset>	
<fieldset>	
	<legend align="left">
	<spring:message code="pilgrim.creditCard" />
	</legend>
			
	<acme:textbox code="pilgrim.creditCard.holderName" path="creditCard.holderName"/>
	<acme:textbox code="pilgrim.creditCard.brandName" path="creditCard.brandName"/>
	<acme:textbox code="pilgrim.creditCard.number" path="creditCard.number"/>
	<acme:textbox code="pilgrim.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
	<acme:textbox code="pilgrim.creditCard.expirationYear" path="creditCard.expirationYear"/>
	<acme:textbox code="pilgrim.creditCard.cvvCode" path="creditCard.cvvCode"/>	
</fieldset>		
	<acme:checkbox code="pilgrim.accepConditions" path="accepConditions"/>
	
	<input type="submit" name="save" value="<spring:message code="pilgrim.save"/>" />
	&nbsp;
	<input type="button" name="cancel" value="<spring:message code="pilgrim.cancel"/>" 
	onclick="javascript: window.location.replace('/AcmePilgrim')"/>
</form:form>