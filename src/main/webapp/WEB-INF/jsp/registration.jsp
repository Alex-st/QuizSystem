<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 9/17/15
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="text" />

<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title><fmt:message key="registrationPage"/></title>
</head>
<body>
<div id="wrapper">

  <%--something to delete--%>
  <center>
      <fmt:message key="${requestScope.resultMessage}"/>
    test<br>

    <c:out value="${locale}" />

    <c:out value="${resultMessage}" />
    <c:out value="${requestScope.resultMessage}" />
    test<br>
    ${resultMessage}<br>
    ${user.userId}<br>

    <fmt:message key="${resultMessage}"/>
    <c:if test="${not empty requestScope.resultMessage}">
      <fmt:message key="${requestScope.resultMessage}"/>
    </c:if>
  </center>
  <%----------------------------------------------%>

  <div id="register">
    <form action="form" method="post">
    <fieldset>
      <legend><fmt:message key="inputYourData"/></legend>
      <sec:authorize access="isAnonymous()">
      <p>
        <select name="select">
          <option value="student" name="opt"><fmt:message key="student"/></option>
          <option value="tutor" name="opt"><fmt:message key="tutor"/></option>
        </select>
      </p>
      </sec:authorize>

      <p>
        <label for="bname" class="uname" data-icon="*"><fmt:message key="name"/></label>
        <input id="bname" type='text' name='name' value='${user.name}'/>
      </p>
      <p>
        <fmt:message key="surname"/>
        <input type='text' name='surname' value='${user.surname}'/>
      </p>

      <p>
        <label for="em" class="uname" data-icon="*"><fmt:message key="email"/></label>
        <input id="em" type="text" name='email' value='${user.email}'/>
      </p>
      <p>
        <label for="logintext" class="youpasswd" data-icon="u"><fmt:message key="loginButton"/></label>
        <input id="logintext" type='text' name='login' value='${user.login}'/>
      </p>
      <p>
        <label for="password" class="youpasswd" data-icon="p"><fmt:message key="passButton"/></label>
        <input id="password" type="password" name='password' value=''/>
      </p>

      <button type = "reset" name="Reset" value="reset"><fmt:message key="reset"/></button>
      <button type = "submit" name="send" value="register">
        <fmt:message key="toregister"/>
      </button>

      <input type="hidden"
             name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
    </fieldset>
    </form>
  </div>

<sec:authorize access="isAuthenticated()">
    <c:import url="/WEB-INF/jsp/menu.jsp"></c:import>
</sec:authorize>
</div>
</body>
</html>
