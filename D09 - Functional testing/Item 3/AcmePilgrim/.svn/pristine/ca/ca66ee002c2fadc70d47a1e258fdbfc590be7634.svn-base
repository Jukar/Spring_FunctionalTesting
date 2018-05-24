<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="message/edit.do" modelAttribute="message">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender" />
	<form:hidden path="folder" />
	<form:hidden path="moment" />
	
	<jstl:if test="${isReply=='false'}">
		<div>
			<form:select path="recipient">
				<form:option label="----" value="0" />
				<form:options items="${recipients}" itemLabel="name" itemValue="id" />
				<spring:message code="message.recipient" />:
			</form:select>
		</div>

		<div>
			<form:label path="subject">
				<spring:message code="message.subject" />:
			</form:label>
			<form:input path="subject" />
			<form:errors cssClass="error" path="subject" />
		</div>
	</jstl:if>
	
	<jstl:if test="${isReply!='false'}">
		<form:hidden path="recipient" />
		<form:hidden path="subject" />
	</jstl:if>
	
	<div>
		<form:label path="body">
			<spring:message code="message.body" />:
		</form:label>
		<form:textarea path="body" />
		<form:errors cssClass="error" path="body" />
	</div>
	<div>
		<form:label path="urlAttachment">
			<spring:message code="message.urlAttachment" />:
		</form:label>
		<form:textarea path="urlAttachment" />
		<form:errors cssClass="error" path="urlAttachment" />
	</div>
	
	<div>
		<input type="submit" name="save"
			value="<spring:message code="message.create"/>" /> 
		&nbsp; 
		<input type="button" name="cancel"
			value="<spring:message code="message.cancel"/>"
			onClick="javascript: window.location.replace('message/list.do');" 
		/>
	</div>
</form:form>