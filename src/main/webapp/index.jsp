<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="text" />

<html>
<head>
    <title><fmt:message key="title"/></title>
</head>>
<body>
<h2><fmt:message key="title"/></h2>

<form method="post" action="authorization.jsp">

    <select name="locale">
        <option value="en" name="opt">English</option>
        <option value="ru" name="opt">Russian</option>
    </select><br>

    <p>
            <label for="blogin"><fmt:message key="loginButton"/></label>
            <input id="blogin" type='text' name='login' /><br>
    </p>
    <p>
            <label for="password"><fmt:message key="passButton"/></label>
            <input id="password" type='password' name='password' /><br>
    </p>
    <p>
            <button type='submit' name='send' value='signup'>
            <fmt:message key="authButton"/>
            </button>
    </p>

    <p>
        <button type="submit" name="bregister" value="register" >
        <fmt:message key="registrationButton"/>
        </button>
    </p>
    <br>

</form>

</body>
</html>
