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
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">

  <script language="JavaScript">
    var needToConfirm = true;

    <%--window.onbeforeunload = confirmExit;--%>
    <%--function confirmExit()--%>
    <%--{--%>
      <%--if (needToConfirm)--%>
        <%--return "<fmt:message key="leavePageMessage"/>";--%>
    <%--}--%>
  </script>

</head>
<body>
<%--${testmark}<br>--%>
<br>
<br>
<div class="container">
  <div class="col-md-12">

    <h2>${testtopic.topicName}&nbsp;<fmt:message key="examPage"/></h2>

    <div id="testform" >

      <form name="questionForm" action="examprocess" method="post">
        <p><h3>${question}</h3></p>

          <c:forEach var="ans" items="${answers}">
            <div class="control-group form-group">
                  <c:if test="${isMulti == false}" >
                    <div class="radio">
                      <label><input type="radio" name="answer" value="${ans.value}" id="${ans.value}"/>&nbsp;&nbsp;${ans.key}</label>
                    </div>
                  </c:if>
                  <c:if test="${isMulti == true}" >
                    <div class="checkbox">
                    <label><input type="checkbox" name="answer" value="${ans.value}" id="${ans.value}"/>&nbsp;&nbsp;${ans.key}</label>
                    <%--<input type="hidden"   name="isMulti" value="true"/>--%>
                    </div>
                  </c:if>

                  <%--<label for="${ans.value}">${ans.key}</label>--%>
            </div>
          </c:forEach>

        <div class="control-group form-group">
          <button type="submit" name="send" value="exam" onclick="needToConfirm = false;" class="btn btn-default"><fmt:message key="answerQuestion"/></button>
          <button type="submit" name="send" value="next" onclick="needToConfirm = false;" class="btn btn-default"><fmt:message key="laterQuestion"/></button>
        </div>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
      </form>
      </div>
    </div>
  </div>
</body>
</html>
