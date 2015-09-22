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
</head>
<body>
<fmt:message key="hello"/> ${user.name}<br>
${resultMessage}<br>

<c:if test="${not empty studresults}" >
  <table>
    <tr>
      <th><fmt:message key="subject"/></th>
      <th><fmt:message key="mark"/></th>
    </tr>
    <c:forEach var="entry" items="${studresults}">
      <tr>
        <td>${entry.topic.topicName}</td>
        <td>${entry.mark}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<br><br>
<p><fmt:message key="passNewTest"/></p>

<form method="get" action="examinit" >
  <c:forEach var="topic" items="${topics}">
    <button type="submit" name="test" value="${topic.topicId}" >${topic.topicName}
    </button><br>
  </c:forEach>

  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
</form>


<c:import url="/WEB-INF/jsp/menu.jsp"></c:import>
</body>
</html>
