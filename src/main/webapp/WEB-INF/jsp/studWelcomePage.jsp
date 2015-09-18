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
<fmt:setBundle basename="resources.text" />

<html>
<head>
    <title><fmt:message key="studentTitle"/></title>
</head>
<body>
hello student;<br>
${locale}

</body>
</html>
