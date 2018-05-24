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

<form:form action="innkeeper/register.do" modelAttribute="innkeeperForm">

	<fieldset>
		<legend align="left">
			<spring:message code="innkeeper.userAccount" />
		</legend>	
		<acme:textbox code="pilgrim.userAccount.username" path="userName"/>
		<acme:password code="innkeeper.userAccount.password" path="password"/>
		<acme:password code="innkeeper.userAccount.confirmPassword" path="confirmPassword"/>
	</fieldset>
	<fieldset>
		<legend align="left">
			<spring:message code="innkeeper.personalInfo" />
		</legend>
			
		<acme:textbox code="innkeeper.name" path="name"/>
		<acme:textbox code="innkeeper.surname" path="surname"/>
		<acme:textbox code="innkeeper.emailAddress" path="emailAddress"/>
		<acme:textbox code="innkeeper.contactPhone" path="contactPhone"/>
		<acme:textbox code="innkeeper.url" path="url"/>
		<acme:textbox code="pilgrim.birthDate" path="birthDate"/>
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
	
	<acme:checkbox code="innkeeper.accepConditions" path="accepConditions"/>
	
	<acme:submit name="save" code="innkeeper.save"/>
	<acme:cancel code="innkeeper.cancel" url="/AcmePilgrim"/>
</form:form>