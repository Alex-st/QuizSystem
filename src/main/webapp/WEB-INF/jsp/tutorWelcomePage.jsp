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
</head>
<body>
<fmt:message key="hello"/> ${user.name}<br>

<c:if test="${not empty resultMessage}" >
  <fmt:message key="${resultMessage}"/><br>
</c:if>

<c:if test="${not empty questions}" >
  <p><fmt:message key="listOfQuestion"/></p>
  <table>
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


<c:import url="/WEB-INF/jsp/menu.jsp"></c:import>

</body>
</html>
