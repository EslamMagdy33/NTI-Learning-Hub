
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="loginUrl" value="/login"/>
<c:url var="registerUrl" value="/register"/>
<c:url var="cssUrl" value="/assets/css/auth.css"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | NTI Learning Hub</title>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body>

<div class="auth-container">

    <div class="auth-card">

        <div class="brand">
            <div class="brand-icon">N</div>
            <span>NTI Learning Hub</span>
        </div>

        <div class="auth-heading">
            <h1>Welcome back</h1>
            <p>Sign in to continue your learning journey.</p>
        </div>

        <c:if test="${param.registered == '1'}">
            <div class="alert success">
                Your account was created successfully. You can log in now.
            </div>
        </c:if>

        <c:if test="${param.loggedOut == '1'}">
            <div class="alert success">
                You have been logged out successfully.
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert error">
                <c:out value="${error}"/>
            </div>
        </c:if>

        <form action="${loginUrl}" method="post">

            <div class="form-group">
                <label for="email">Email address</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="name@example.com"
                    value="<c:out value='${param.email}'/>"
                    required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    placeholder="Enter your password"
                    required>
            </div>

            <button type="submit" class="submit-btn">
                Sign in
                <span>&rarr;</span>
            </button>

        </form>

        <div class="auth-footer">
            Don't have an account?
            <a href="${registerUrl}">Create an account</a>
        </div>

    </div>

    <p class="copyright">
        &copy; 2026 NTI Learning Hub. Built for learners.
    </p>

</div>

</body>
</html>
