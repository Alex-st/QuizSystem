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
</head>
<body>
<div id="wrapper">

  <br>
  ${locale}<br>
  <fmt:message key="hello"/> ${user.name}<br>
  <br>

  <form action='addquestion' method="post">

    <table>
      <tr>
        <td colspan="3" align="center"><fmt:message key="writeQuestion"/></td>
      </tr>
      <tr>
        <td><fmt:message key="subject"/>
          <select name="qtopic">
          <c:forEach var="topic" items="${topics}">
            <option value="${topic.topicName}" name="opt">${topic.topicName}</option>
          </c:forEach>
        </select></td>
        <%--<td></td>--%>
        <td colspan="2"><input type="checkbox" name="isMultiChoice" value="multi"><fmt:message key="isQuestionMulty"/></td>
      </tr>
      <tr>
        <td><fmt:message key="inputQuestion"/></td>
        <td><input type='text' name='qtext' value=''/></td>
        <td></td>
      </tr>
      <tr>
        <td colspan="3" align="center"><fmt:message key="writeAnswer"/></td>
      </tr>
      <tr>
        <td><fmt:message key="inputCorrectAnswer"/></td>
        <td><input type='text' name='correctanswer' value=''/></td>
        <td><input type="checkbox" name="correctAnswer" value="a1"><fmt:message key="isAnswerCorrect"/></td>
      </tr>
      <tr>
        <td><fmt:message key="inputSecondAnswer"/></td>
        <td><input type='text' name='q2' value=''/></td>
        <td><input type="checkbox" name="correctAnswer" value="a2"><fmt:message key="isAnswerCorrect"/></td>
      </tr>
      <tr>
        <td><fmt:message key="inputThirdAnswer"/></td>
        <td><input type='text' name='q3' value=''/></td>
        <td><input type="checkbox" name="correctAnswer" value="a3"><fmt:message key="isAnswerCorrect"/></td>
      </tr>
      <tr>
        <td><fmt:message key="inputFourthAnswer"/></td>
        <td><input type='text' name='q4' value=''/></td>
        <td><input type="checkbox" name="correctAnswer" value="a4"><fmt:message key="isAnswerCorrect"/></td>
      </tr>
      <tr>
        <td colspan="3" align="center"><button type = "reset" name="Reset" value="reset"><fmt:message key="reset"/></button>
        <button type="submit" name="send" value="newquestion"><fmt:message key="addQuestion"/></button></td>
      </tr>
    </table>



    <%--<select name="qtopic">--%>
      <%--<c:forEach var="topic" items="${topics}">--%>
        <%--<option value="${topic.topicName}" name="opt">${topic.topicName}</option>--%>
      <%--</c:forEach>--%>
    <%--</select><br>--%>

    <%--<fmt:message key="inputQuestion"/>--%>
    <%--<input type='text' name='qtext' value=''/><br>--%>
    <%--<fmt:message key="inputCorrectAnswer"/>--%>
    <%--<input type='text' name='correctanswer' value=''/><br>--%>
    <%--<fmt:message key="inputSecondAnswer"/>--%>
    <%--<input type="text" name='q2' value=''/><br>--%>
    <%--<fmt:message key="inputThirdAnswer"/>--%>
    <%--<input type='text' name='q3' value=''/><br>--%>
    <%--<fmt:message key="inputFourthAnswer"/>--%>
    <%--<input type='text' name='q4' value=''/><br>--%>
    <%--<fmt:message key="inputFifthAnswer"/>--%>
    <%--<input type='text' name='q5' value=''/><br>--%>

    <%--<button type = "reset" name="Reset" value="reset"><fmt:message key="reset"/></button>--%>
    <%--<button type="submit" name="send" value="newquestion"><fmt:message key="addQuestion"/></button>--%>
  </form>


  <c:import url="/WEB-INF/jsp/menu.jsp"></c:import>

</div>

</body>
</html>
