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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<form action="article/searchArticleByKeyword.do" method="post">
			<label> <spring:message code="article.keyword" />
			</label> <input type="text" name="word" /> <input type="submit"
				name="searchArticleByKeyword" value="<spring:message code="article.search"/>" />
		</form>
		<br>
		
<display:table name="articles" id="row" requestURI="${requestURI}" pagesize="5">


<jstl:set var="isDraft" value="${row.isDraft}" />		
	
	
	<jstl:if test="${isDraft eq true}">
		<jstl:set var="style" value="background-color:#ffad9b;" />
	</jstl:if>
	
	<jstl:if test="${isDraft eq false}">
		<jstl:set var="style" value="background-color:transparent;" />
	</jstl:if>

<spring:message code="article.title" var="titleHeader" />
<display:column property="title" title="${titleHeader}" sortable="false" style="${style}"/>
	
<spring:message code="article.body" var="bodyHeader" />
<display:column property="body" title="${bodyHeader}" sortable="false" style="${style}"/>

<spring:message code="article.summary" var="summaryHeader" />
<display:column property="summary" title="${summaryHeader}" sortable="false" style="${style}"/>
<jstl:choose>
<jstl:when test="${row.publicationMoment!= null}">
<spring:message code="article.publicationMoment" var="publicationMomentHeader" />
<display:column property="publicationMoment" title="${publicationMomentHeader}" sortable="false" style="${style}"/>

</jstl:when>
</jstl:choose>
<spring:message code="article.pictures" var ="picturesHeader"/>		
<display:column title="${picturesHeader}" style="${style}">			
			<jstl:choose>
	<jstl:when test="${fn:length(row.pictures)==0}">
			 	<spring:message code="article.noPictures"  />
	</jstl:when>
		</jstl:choose>
	</display:column>



</display:table>

<security:authorize access="isAuthenticated()">
	<jstl:if test="${not empty articles}">
		<span style="background-color:#ffad9b; border-radius: 15px 50px;">&nbsp;&nbsp;<spring:message code="article.isDraft"/>&nbsp;&nbsp;</span>
	</jstl:if>
</security:authorize>
