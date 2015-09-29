<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 9/28/15
  Time: 5:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="text" />

<html>
<head>
    <title><fmt:message key="errorPageTitle"/></title>
  <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="col-md-12">
    <h1><fmt:message key="errorPageHeader"/></h1>
    <c:if test="${url != null}"> URL: ${url} </c:if> </br>
    ${errorMessage}<br>
    <a href="${pageContext.request.contextPath}/index"><fmt:message key="errorButtonToMain"/></a>
  </div>
</div>
</body>
</html>
