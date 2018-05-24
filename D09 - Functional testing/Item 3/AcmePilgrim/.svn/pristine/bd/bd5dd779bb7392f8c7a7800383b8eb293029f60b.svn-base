<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="AcmePilgrim" />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<li><a class="fNiv"><spring:message	code="master.page.route" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="route/list.do"><spring:message code="master.page.route.list" /></a></li>
					</ul>
		</li>
		<li><a class="fNiv"><spring:message	code="master.page.anecdote" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="anecdote/list.do"><spring:message code="master.page.anecdote.list" /></a></li>
				</ul>
		</li>
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.pilgrim" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="pilgrim/administrator/list.do"><spring:message code="master.page.pilgrim.list" /></a></li>
				</ul>
			</li>
			<li><a href="complaint/administrator/list.do"><spring:message code="master.page.complaints"/></a><li>
			<li><a href="administrator/dashboard.do"><spring:message code="master.page.dashboard"/></a></li>
			<li><a href="administrator/register.do"><spring:message code="master.page.registerAdmin"/></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('PILGRIM')">
			<li><a class="fNiv"><spring:message	code="master.page.complaints" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="complaint/pilgrim/list.do"><spring:message code="master.page.complaint.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('PILGRIM')">
			<li><a class="fNiv"><spring:message	code="master.page.myanecdotes" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="anecdote/pilgrim/list.do"><spring:message code="master.page.anecdote.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="pilgrim/register.do"><spring:message code="master.page.registerPilgrim" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a href="message/list.do"><spring:message code="master.page.messages"/></a></li>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>	
						<security:authorize access="hasRole('PILGRIM')">
							<li><a href="pilgrim/profile.do"><spring:message code="master.page.profile" /> </a></li>					
						</security:authorize>
						<security:authorize access="hasRole('ADMIN')">
							<li><a href="administrator/profile.do"><spring:message code="master.page.profile" /> </a></li>					
						</security:authorize>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

