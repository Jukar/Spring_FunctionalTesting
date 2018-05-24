<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- DASHBOARD LEVEL C -->
 <fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset1"/>
	</legend>
	<display:table name="routesByRegister" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="route.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="route.actived" var="actived"/>
		<display:column property="actived" title="${actived}" sortable="false" />
		
		<spring:message code="route.ratingL" var="ratingL"/>
		<display:column property="ratingL" title="${ratingL}" sortable="false" />
		
		<spring:message code="route.ratingD" var="ratingD"/>
		<display:column property="ratingD" title="${ratingD}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset2"/>
	</legend>
	<display:table name="pilgrimsByRegisterDesc" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}"  format="{0,date,dd/MM/yyyy}" sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>



<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset3"/>
	</legend>
	<display:table name="routesByVotes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="route.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="route.actived" var="actived"/>
		<display:column property="actived" title="${actived}" sortable="false" />
		
		<spring:message code="route.ratingL" var="ratingL"/>
		<display:column property="ratingL" title="${ratingL}" sortable="false" />
		
		<spring:message code="route.ratingD" var="ratingD"/>
		<display:column property="ratingD" title="${ratingD}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset21"/>
	</legend>
	<display:table name="keepersByLodge" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="innkeeper.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="innkeeper.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="innkeeper.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="innkeeper.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset22"/>
	</legend>
	<display:table name="lodgesByBooking" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="lodge.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="lodge.address" var="address"/>
		<display:column property="address" title="${address}" sortable="false" />
		
		<spring:message code="lodge.website" var="webSite"/>
		<display:column property="webSite" title="${webSite}" sortable="false" />
		
		<spring:message code="lodge.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="lodge.numberBeds" var="numberBeds"/>
		<display:column property="numberBeds" title="${numberBeds}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset23"/>
	</legend>
	<display:table name="stagesByAVG" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="stage.name" var="name"></spring:message>
		<display:column property="name" title="${name}" sortable="true"></display:column>
		
		<spring:message code="stage.origin" var="origin"></spring:message>
		<display:column property="origin.title" title="${origin}" sortable="true"></display:column>
	
		<spring:message code="stage.destination" var="destination"></spring:message>
		<display:column property="destination.title" title="${destination}" sortable="true"></display:column>
	
		<spring:message code="stage.lenghtKm" var="lenghtKm"></spring:message>
		<display:column property="lenghtKm" title="${lenghtKm}" sortable="true"></display:column>
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset24"/>
	</legend>
	<display:table name="pilgrimsByBirthday" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}"  format="{0,date,dd/MM/yyyy}"  sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	
	</display:table>
</fieldset>

<!-- DASHBOARD LEVEL B -->
<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset4"></spring:message>
	</legend>
	<jstl:out value="${totalNumber}"></jstl:out>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset5"></spring:message>
	</legend>
	<jstl:out value="${averageNumber}"></jstl:out>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset6"/>
	</legend>
	<display:table name="pilgrimsMoreAnecdotes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}"  format="{0,date,dd/MM/yyyy}"  sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset25"/>
	</legend>
	<display:table name="keepersMoreBookings" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="innkeeper.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="innkeeper.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="innkeeper.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="innkeeper.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset26"/>
	</legend>
	<display:table name="pilgrimsMoreBooking" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}"  format="{0,date,dd/MM/yyyy HH:mm}"  sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>
	
<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset27"/>
	</legend>
	<display:table name="keepersMoreUnpaid" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="innkeeper.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="innkeeper.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="innkeeper.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="innkeeper.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
	</display:table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset28"/>
	</legend>
	<display:table name="pilgrimsMoreUnpaid" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}"  format="{0,date,dd/MM/yyyy}"  sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>	
	
<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset29"/>
	</legend>
	<display:table name="keepersMoreMoneyEarned" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="innkeeper.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="innkeeper.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="innkeeper.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="innkeeper.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
	</display:table>
</fieldset>	

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset210"/>
	</legend>
	<display:table name="pilgrimsMoreMoneySpent" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}"  format="{0,date,dd/MM/yyyy}" sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
</fieldset>



<!-- DASHBOARD LEVEL A -->

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset7"></spring:message>
	</legend>
	<table class="displaytag">
		<thead>
		<tr>
			<th><spring:message code="pilgrim.name"/></th>
			<th><spring:message code="rateOfComplaints"></spring:message></th>
		</tr>
		</thead>
		
		<tbody>
		<jstl:forEach items="${rate}" var="rating">
			<tr>
				<jstl:forEach items="${rating}" var="aux">
					<td><jstl:out value="${aux}"/></td>
				</jstl:forEach>
			</tr>
		</jstl:forEach>
		</tbody>
	</table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset8"/>
	</legend>
	<display:table name="pligrimsMoreComplaints" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="pilgrim.name" var="name"/>
		<display:column property="name" title="${name}" sortable="false" />
		
		<spring:message code="pilgrim.surname" var="surname"/>
		<display:column property="surname" title="${surname}" sortable="false" />
		
		<spring:message code="pilgrim.emailAddress" var="emailAddress"/>
		<display:column property="emailAddress" title="${emailAddress}" sortable="false" />
		
		<spring:message code="pilgrim.contactPhone" var="contactPhone"/>
		<display:column property="contactPhone" title="${contactPhone}" sortable="false" />
		
		<spring:message code="pilgrim.birthDate" var="birthDate"/>
		<display:column property="birthDate" title="${birthDate}" format="{0,date,dd/MM/yyyy}"  sortable="false" />
		
		<spring:message code="pilgrim.nationality" var="nationality"/>
		<display:column property="nationality" title="${nationality}" sortable="false" />
	</display:table>
	
<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset211"/>
	</legend>
	<jstl:out value="${totalLandmarks}"></jstl:out>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset212"/>
	</legend>
	<table class="displaytag">
		<thead>
		<tr>
			<th><spring:message code="stage.name"/></th>
			<th><spring:message code="average"></spring:message></th>
		</tr>
		</thead>
		
		<tbody>
		<jstl:forEach items="${avgLandmarksPerRoute}" var="Lodge">
			<tr>
				<jstl:forEach items="${Lodge}" var="aux">
					<td><jstl:out value="${aux}"/></td>
				</jstl:forEach>
			</tr>
		</jstl:forEach>
		</tbody>
	</table>
</fieldset>

<fieldset>
	<legend align="left">
		<spring:message code="dashboard.fieldset213"/>
	</legend>
	<display:table name="complaintsByStatus" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
		<spring:message code="complaint.title" var="title"/>
		<display:column property="title" title="${title}" sortable="false" />
		
		<spring:message code="complaint.creationMoment" var="creationMoment"/>
		<display:column property="creationMoment" format="{0,date,dd/MM/yyyy HH:mm}"  title="${creationMoment}" sortable="false" />
		
		<spring:message code="complaint.description" var="description"/>
		<display:column property="description" title="${description}" sortable="false" />
		
		<spring:message code="complaint.resolution" var="resolution"/>
		<display:column property="resolution" title="${resolution}" sortable="false" />
		
		<spring:message code="complaint.status" var="status"/>
		<display:column property="status" title="${status}" sortable="false" />
	</display:table>
</fieldset>

	
</fieldset> 