<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 9/14/15
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="text" />

<html>
<head>
    <title><fmt:message key="studentTitle"/></title>
  <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/modern-business.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
  <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.js" />"></script>
  <script src="<c:url value="/resources/js/jqBootstrapValidation.js"/>"></script>

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
        <fmt:message key="${resultMessage}"/>${resultMark}
      </div>
    </c:if>

    <c:if test="${not empty studresults}" >
      <h4><fmt:message key="yourResult"/></h4>
      <table class="table table-condensed">
        <tr>
          <th><fmt:message key="date"/></th>
          <th><fmt:message key="subject"/></th>
          <th><fmt:message key="mark"/></th>
        </tr>
        <c:forEach var="entry" items="${studresults}">
          <tr>
            <td>${entry.date}</td>
            <td>${entry.topic.topicName}</td>
            <td>${entry.mark}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>

<br><br>
<h4><fmt:message key="passNewTest"/></h4>

    <form method="get" action="examinit" >
      <ul class="nav nav-pills nav-stacked">

      <c:forEach var="topic" items="${topics}">
        <li>
          <button type="submit" class="btn btn-default" name="test" value="${topic.topicId}" >${topic.topicName}
          </button>
        </li>
      </c:forEach>

      </ul>
      <input type="hidden"
             name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
    </form>
    </div>
  </div>
</body>
</html>
