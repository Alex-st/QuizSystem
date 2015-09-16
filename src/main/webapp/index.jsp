<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="text" />

<html>
<head>
    <title><fmt:message key="title"/></title>
</head>
<body>
<h2><fmt:message key="title"/></h2>

<form name="loginForm" method="post" action="login">

    <select name="locale">
        <option value="en" name="opt">English</option>
        <option value="ru" name="opt">Russian</option>
    </select><br>

    <p>
            <label for="blogin"><fmt:message key="loginButton"/></label>
            <input id="blogin" type='text' name='username' /><br>
    </p>
    <p>
            <label for="password"><fmt:message key="passButton"/></label>
            <input id="password" type='password' name='password' /><br>
    </p>
    <p>
            <button type='submit' name='submit' value='submit'>
            <fmt:message key="authButton"/>
            </button>
    </p>

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

    <p>
        <button type="submit" name="bregister" value="register" >
        <fmt:message key="registrationButton"/>
        </button>
    </p>
    <br>

</form>

</body>
</html>
