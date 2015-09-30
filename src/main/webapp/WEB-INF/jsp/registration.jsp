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
  <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/modern-business.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
  <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.js" />"></script>
  <script src="<c:url value="/resources/js/jqBootstrapValidation.js"/>"></script>

</head>
<body>
<div class="container">
  <div class="col-md-12">
    <br>

    <div id="header" align="center">
      <c:if test="${not empty user}">
        <fmt:message key="hello"/> ${user.name}<br>
      </c:if>

    <%--something additional--%>
      <c:if test="${not empty resultMessage}" >
        <div class="alert alert-info">
          <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
          <fmt:message key="${resultMessage}"/>
        </div>
      </c:if>
    </div>
  <%----------------------------------------------%>

  <div id="register" align="center">
    <br>
    <form action="form" method="post">
    <fieldset>
      <legend><fmt:message key="inputYourData"/></legend>
      <sec:authorize access="isAnonymous()">
      <p><fmt:message key="toregister"/>
        <select name="select">
          <option value="student" name="opt"><fmt:message key="student"/></option>
          <option value="tutor" name="opt"><fmt:message key="tutor"/></option>
        </select>
      </p>
      </sec:authorize>

      <table>
        <tr>
          <td>
            <div class="control-group form-group">
              <label for="bname" class="uname" data-icon="*"><fmt:message key="name"/></label>
            </div>
          </td>
          <td>
            <div class="control-group form-group">
              <input id="bname" type='text' name='name' value='${user.name}'/>
              <c:if test="${not empty errorMessages['nameError']}">
                  <font color="red"><fmt:message key="${errorMessages['nameError']}"/></font>
              </c:if>
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="control-group form-group">
            <fmt:message key="surname"/>
              </div>
          </td>
          <td>
            <div class="control-group form-group">
            <input type='text' name='surname' value='${user.surname}'/>
              </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="control-group form-group">
            <label for="em" class="uname" data-icon="*"><fmt:message key="email"/></label>
              </div>
          </td>
          <td>
            <div class="control-group form-group">
            <input id="em" type="text" name='email' value='${user.email}'/>
            <c:if test="${not empty errorMessages['emailError']}">
              <font color="red"><fmt:message key="${errorMessages['emailError']}"/></font>
            </c:if>
              </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="control-group form-group">
            <label for="logintext" class="youpasswd" data-icon="u"><fmt:message key="loginButton"/></label>
              </div>
          </td>
          <td>
            <div class="control-group form-group">
            <input id="logintext" type='text' name='login' value='${user.login}'/>
            <c:if test="${not empty errorMessages['loginError']}">
              <font color="red"><fmt:message key="${errorMessages['loginError']}"/></font>
            </c:if>
              </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="control-group form-group">
            <label for="password" class="youpasswd" data-icon="p"><fmt:message key="passButton"/></label>
              </div>
          </td>
          <td>
            <div class="control-group form-group">
            <input id="password" type="password" name='password' value=''/>
            <c:if test="${not empty passwordError}">
              <font color="red"><fmt:message key="${passwordError}"/></font>
            </c:if>
              </div>
          </td>
        </tr>
        </table>
      <center>
      <button type = "reset" name="Reset" value="reset" class="btn btn-default"><fmt:message key="reset"/></button>
      <button type = "submit" name="send" value="register" class="btn btn-default">
        <fmt:message key="${registerButton}"/>
      </button>
        </center>

      <input type="hidden"
             name="${_csrf.parameterName}"
             value="${_csrf.token}"/>
    </fieldset>
    </form>

    <sec:authorize access="isAnonymous()">
    <a href="${pageContext.request.contextPath}/index"><fmt:message key="toIndex"/>
      </sec:authorize>
  </div>


<sec:authorize access="isAuthenticated()">
    <c:import url="/WEB-INF/jsp/menu.jsp"></c:import>
</sec:authorize>
</div>
  </div>

</body>
</html>
