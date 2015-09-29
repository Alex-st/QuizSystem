<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 9/18/15
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="text" />


<html>
<head>
  <title><fmt:message key="tutorTitle"/></title>
  <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/modern-business.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
  <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.js" />"></script>
</head>
<body>
<c:import url="/WEB-INF/jsp/menu.jsp"></c:import>
<br>
<div class="container" align="center">
  <div class="col-md-12">

    <fmt:message key="hello"/> ${user.name}<br>
    <c:if test="${not empty resultMessage}" >
      <div class="alert alert-info">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <fmt:message key="${resultMessage}"/>
      </div>
    </c:if>

    <c:if test="${not empty questions}" >
      <h4><fmt:message key="listOfQuestion"/></h4>
      <table class="table">
        <tr>
          <th><fmt:message key="subject"/></th>
          <th><fmt:message key="question"/></th>
        </tr>
        <c:forEach var="entry" items="${questions}">
          <tr>
            <td>${entry.topic.topicName}</td>
            <td>${entry.text}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
  </div>
</div>
</body>
</html>
