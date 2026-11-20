
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="registerUrl" value="/register"/>
<c:url var="loginUrl" value="/login"/>
<c:url var="cssUrl" value="/assets/css/auth.css"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account | NTI Learning Hub</title>
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
            <h1>Create your account</h1>
            <p>Join NTI Learning Hub and start your learning journey.</p>
        </div>

        <c:if test="${not empty errors}">
            <div class="alert error">
                <ul style="padding-left: 18px; margin: 0;">
                    <c:forEach var="error" items="${errors}">
                        <li><c:out value="${error}"/></li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form action="${registerUrl}" method="post">

            <div class="form-group">
                <label for="fullName">Full name</label>
                <input
                    type="text"
                    id="fullName"
                    name="fullName"
                    placeholder="Enter your full name"
                    value="<c:out value='${fullName}'/>"
                    autocomplete="name"
                    required>
            </div>

            <div class="form-group">
                <label for="email">Email address</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="name@example.com"
                    value="<c:out value='${email}'/>"
                    autocomplete="email"
                    required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    placeholder="At least 6 characters"
                    autocomplete="new-password"
                    minlength="6"
                    required>
            </div>

            <button type="submit" class="submit-btn">
                Create account
                <span>&rarr;</span>
            </button>

        </form>

        <div class="auth-footer">
            Already have an account?
            <a href="${loginUrl}">Sign in</a>
        </div>

    </div>

    <p class="copyright">
        &copy; 2026 NTI Learning Hub. Built for learners.
    </p>

</div>

</body>
</html>
