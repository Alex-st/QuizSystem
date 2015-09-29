<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 9/18/15
  Time: 5:49 PM
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
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <title><fmt:message key="questionTitle"/></title>
  <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/css/modern-business.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
  <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
  <script src="<c:url value="/resources/js/jquery.js" />"></script>
  <script src="<c:url value="/resources/js/jqBootstrapValidation.js"/>"></script>


</head>
<body>
<c:import url="/WEB-INF/jsp/menu.jsp"></c:import>
<br>
<div class="container" align="center">
  <div class="col-md-12">

    <fmt:message key="hello"/> ${user.name}<br>
    <c:if test="${not empty resultMessage}" >
      <div class="alert alert-info">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <fmt:message key="${resultMessage}"/>
      </div>
    </c:if>

  <form action='addquestion' method="post">

    <table>
      <tr>
        <td colspan="3" align="center"><h4><fmt:message key="writeQuestion"/></h4></td>
      </tr>
      <tr>
        <td><fmt:message key="subject"/>
          <select name="qtopic">
          <c:forEach var="topic" items="${topics}">
            <option value="${topic.topicName}" name="opt">${topic.topicName}</option>
          </c:forEach>
        </select></td>
        <%--<td></td>--%>
        <td colspan="2">
          <div class="control-group form-group">
            <input type="checkbox" name="isMultiChoice" value="true">
            <fmt:message key="isQuestionMulty"/>
            </div>
        </td>
      </tr>
      <tr>
        <td><fmt:message key="inputQuestion"/></td>
        <td>

          <textarea cols="23" rows="5" name="qtext">${curQuestion.text}</textarea>
          <%--<input type='text' name='qtext' value=''/>--%>
        </td>
        <td></td>
      </tr>
      <tr>
        <td colspan="3" align="center"><h4><fmt:message key="writeAnswer"/></h4></td>
      </tr>
      <tr>
        <td><div class="control-group form-group"><fmt:message key="inputCorrectAnswer"/></div></td>
        <td><input type='text' name='q1' value='${q1.text}'/></td>
        <td><input type="checkbox" name="correctAnswer1" value="true">&nbsp;<fmt:message key="isAnswerCorrect"/></td>
      </tr>
      <tr>
        <td><div class="control-group form-group"><fmt:message key="inputSecondAnswer"/></div></td>
        <td><input type='text' name='q2' value='${q2.text}'/></td>
        <td><input type="checkbox" name="correctAnswer2" value="true">&nbsp;<fmt:message key="isAnswerCorrect"/></td>
      </tr>
      <tr>
        <td><div class="control-group form-group"><fmt:message key="inputThirdAnswer"/></div></td>
        <td><input type='text' name='q3' value='${q3.text}'/></td>
        <td><input type="checkbox" name="correctAnswer3" value="true">&nbsp;<fmt:message key="isAnswerCorrect"/></td>
      </tr>
      <tr>
        <td><div class="control-group form-group"><fmt:message key="inputFourthAnswer"/></div></td>
        <td><input type='text' name='q4' value='${q4.text}'/></td>
        <td><input type="checkbox" name="correctAnswer4" value="true">&nbsp;<fmt:message key="isAnswerCorrect"/></td>
      </tr>
      <tr>
        <td colspan="3" align="center">
          <div class="control-group form-group">
          <button type = "reset" name="Reset" class="btn btn-default" value="reset"><fmt:message key="reset"/></button>
          <button type="submit" name="send" class="btn btn-default" value="newquestion"><fmt:message key="addQuestion"/></button>
          </div>
        </td>
      </tr>
    </table>

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

  </form>


</div>
</div>


</body>
</html>
