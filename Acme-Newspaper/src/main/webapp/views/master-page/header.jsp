<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a href=""><img src="images/logo.png"
		alt="Acme-Newspaper Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<security:authorize access="permitAll">
			<li>
				<a class="fNiv" href="user/list.do"> <spring:message code="master.page.users"/></a>
			</li>
			<li>
				<a class="fNiv" href="customer/list.do"> <spring:message code="master.page.customers"/></a>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv"><spring:message
						code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="user/create.do">
						<spring:message code="master.page.user" /></a></li>
					<li><a href="customer/create.do">
						<spring:message code="master.page.customer" /></a></li>
				</ul>
			</li>
		</security:authorize>
	
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/dashboard.do">
						<spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="administrator/administrator/list.do">
						<spring:message code="master.page.administrators" /></a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message
						code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li>
						<a href="subscription/customer/list.do">
							<spring:message code="master.page.subscription.mySubscriptions"/>
						</a>
					</li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('USER')">
						<li><a href="user/user/edit.do"><spring:message
									code="user.profile" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('CUSTOMER')">
						<li><a href="customer/customer/edit.do"><spring:message
									code="user.profile" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="administrator/administrator/edit.do"><spring:message
									code="user.profile" /></a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

