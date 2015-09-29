<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="text" />

<html>
<head>
    <title><fmt:message key="title"/></title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/modern-business.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/jqBootstrapValidation.js"/>"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
        <h2><fmt:message key="title"/></h2>

        <c:if test="${not empty resultMessage}" >
            <fmt:message key="${resultMessage}"/><br>
        </c:if>

    <form name="loginForm" method="post" action="login" role="form">

        <fieldset class="scheduler-border">
            <legend><fmt:message key="greatingIndexMessage"/></legend>
    <div class="control-group form-group">
            <select name="locale">
            <option value="en" name="opt">English</option>
            <option value="ru" name="opt">Russian</option>
            </select>
    </div>

    <div class="control-group form-group">
            <label for="blogin"><fmt:message key="loginButton"/></label><br>
            <input id="blogin" type='text' name='username' required data-validation-required-message="Please enter your login" aria-describedby="sizing-addon1"/><br>
    </div>
    <div class="control-group form-group">
            <label for="password"><fmt:message key="passButton"/></label><br>
            <input id="password" type='password' name='password' required data-validation-required-message="Please enter your password"/><br>
    </div>
        <div id="success"></div>
            <button type='submit' name='submit' value='submit' class="btn btn-default">
            <fmt:message key="authButton"/>
            </button>

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
        </fieldset>

</form>

            <div class="control-group form-group">
                <form action="register/" method="get">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                    <input type="hidden"
                           name="locale"
                           value="ru"/>
                    <button type = "submit" name="register" value="register" class="btn btn-link"><fmt:message key="pleaseRegister"/></button>
                </form>
            </div>

        </div>
        </div>
</div>


<%--<p>--%>
    <%--<a href="register/"> <fmt:message key="registrationButton"/> </a>--%>
<%--</p>--%>

</body>
</html>
