<%--
  Created by IntelliJ IDEA.
  User: EslamMagdy
  Date: 11/17/2026
  Time: 7:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
  <head>
    <title>Register jsp</title>
  </head>
  <body>
    <h1>Create an account</h1>
    <c:if test="${not empty errors}">
      <ul style="color:red">
        <c:forEach var="e" items="${errors}">
          <li><c:out value="${e}"/></li>
        </c:forEach>
      </ul>
    </c:if>
    <form action="register" method="post" novalidate>
      <label>Full name
        <input name="fullName" value="<c:out value='${fullName}'/>"></label><br>
      <label>Email
        <input name="email" type="email" value="<c:out value='${email}'/>"></label><br>
      <label>Password <input name="password" type="password"></label><br>
      <button type="submit">Register</button>
    </form>
    <a href="login">Already have an account? Log in</a>
  </body>
</html>
