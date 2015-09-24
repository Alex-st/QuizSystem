<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 9/22/15
  Time: 6:07 PM
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
    <title><fmt:message key="examPage"/></title>
</head>
<body>
${testmark}<br>
<form name="questionForm" action="examprocess" method="post">
  <p><b><center>${question}</center></b></p>
  <center>
    <c:forEach var="ans" items="${answers}">
      <p class="keeplogin">
        <c:if test="${isMulti == false}" >
          <input type="radio" name="answer" value="${ans.value}" id="${ans.value}"/>
        </c:if>
        <c:if test="${isMulti == true}" >
          <input type="checkbox" name="answer" value="${ans.value}" id="${ans.value}"/>
          <%--<input type="hidden"   name="isMulti" value="true"/>--%>
        </c:if>
      <label for="${ans.value}">${ans.key}</label></p>
    </c:forEach>

    <button type="submit" name="send" value="exam"><fmt:message key="answerQuestion"/></button>
    <button type="submit" name="send" value="next"><fmt:message key="laterQuestion"/></button>
  </center>

  <input type="hidden"
         name="${_csrf.parameterName}"
         value="${_csrf.token}"/>
</form>
</body>
</html>
