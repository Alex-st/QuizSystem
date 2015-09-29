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


<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-left">
        <sec:authorize access="hasRole('TUTOR')">
          <li><a href="${pageContext.request.contextPath}/tutor/"><fmt:message key="tutorTitle"/></a>
          </li>
          <li><a href="${pageContext.request.contextPath}/tutor/newQuestionForm"><fmt:message key="questionTitle"/></a>
          </li>
        </sec:authorize>
        <sec:authorize access="hasRole('STUDENT')">
          <li><a href="${pageContext.request.contextPath}/stud/"><fmt:message key="studentTitle"/></a>
          </li>
        </sec:authorize>
        <li>
          <a href="${pageContext.request.contextPath}/register/"><fmt:message key="modifyPersonalData"/></a>
        </li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li>
          <c:url var="logoutUrl" value="/logout"/>
          <form action="${logoutUrl}" method="post">
              <button type = "submit" value="Log out" class="btn btn-link"><span class="glyphicon glyphicon-log-out"></span>&nbsp;<fmt:message key="exitbutton"/></button>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
          </form>
        </li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container -->
</nav>

