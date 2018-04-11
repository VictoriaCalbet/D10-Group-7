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

<display:table name="tabooWords" id="row" requestURI="${requestURI}" pagesize="5">

	<spring:message code="systemconfiguration.taboowords" var="tabooword" />
	<display:column property="tabooWord" title="${tabooword}" />
	
	<spring:message code="systemconfiguration.edit" var="edit" />	
	<display:column title="${edit}">	
		<a href="system-configuration/administrator/edit.do?tabooWord=${row.tabooWord}">
			 <spring:message code="systemconfiguration.edit" />
		</a>
	</display:column>
	
	<spring:message code="systemconfiguration.delete" var="delete" />	
	<display:column title="${delete}">	
		<a href="system-configuration/administrator/delete.do?tabooWord=${row.tabooWord}">
			 <spring:message code="systemconfiguration.delete" />
		</a>
	</display:column>
</display:table>

<a href="system-configuration/administrator/create.do">
<spring:message code="systemconfiguration.create" /></a>