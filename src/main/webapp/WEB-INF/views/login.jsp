<%--
  Created by IntelliJ IDEA.
  User: EslamMagdy
  Date: 11/17/2026
  Time: 7:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
    <h1>Log in</h1>
    <c:if test="${param.registered == '1'}">
      <p style="color:green">Your account was created. You can log in now.</p>
    </c:if>
    <c:if test="${param.loggedOut == '1'}">
      <p>You are logged out.</p>
    </c:if>
    <c:if test="${not empty error}">
      <p style="color:red"><c:out value="${error}"/></p>
    </c:if>

    <form action="login" method="post">
      <label>Email
        <input type="email" name="email" value="<c:out value='${param.email}'/>"></label><br>
      <label>Password <input type="password" name="password"></label><br>
      <button type="submit">Log in</button>
    </form>
    <a href="register">Create an account</a>
  </body>
</html>
