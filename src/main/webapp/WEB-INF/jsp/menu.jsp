<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 8/23/15
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="text" />


<ul>
  <li><a href="registration"><fmt:message key="modifyPersonalData"/></a>
  </li>
  <li><a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="exitbutton"/></a>
  </li>
  <sec:authorize access="hasRole('TUTOR')">
    <li><a href="${pageContext.request.contextPath}/tutor/"><fmt:message key="tutorTitle"/></a>
    </li>
    <li><a href="${pageContext.request.contextPath}/tutor/newQuestionForm"><fmt:message key="questionTitle"/></a>
    </li>
  </sec:authorize>
  <sec:authorize access="hasRole('STUDENT')">
    <li><a href="${pageContext.request.contextPath}/stud/passexam">Pass new Exam</a>
    </li>
    <li><a href="${pageContext.request.contextPath}/stud/">Student home page</a>
    </li>
  </sec:authorize>
</ul>
