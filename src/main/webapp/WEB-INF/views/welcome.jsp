<%--
  Created by IntelliJ IDEA.
  User: EslamMagdy
  Date: 11/17/2026
  Time: 7:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
  <head>
    <title>Welcome</title>
  </head>
  <body>
  <h1>Welcome, <c:out value="${sessionScope.user.fullName}"/>!</h1>
  <p>You are logged in as <c:out value="${sessionScope.user.email}"/>.</p>
  <form action="logout" method="post"><button type="submit">Log out</button></form>
  </body>
</html>
