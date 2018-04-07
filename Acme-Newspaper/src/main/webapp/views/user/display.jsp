<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<b><spring:message code="user.name" />: </b>
	<jstl:out value="${user.name}" /><br>
	
	<b><spring:message code="user.surname" />: </b>
	<jstl:out value="${user.surname}" /><br>
	
	<b><spring:message code="user.postalAddresses" />: </b>
	<jstl:out value="${user.postalAddresses}" /><br>
	
	<b><spring:message code="user.phoneNumbers" />: </b>
	<jstl:out value="${user.phoneNumbers}" /><br>
	
	<b><spring:message code="user.emailAddresses" />: </b>
	<jstl:out value="${user.emailAddresses}" /><br>
</div>