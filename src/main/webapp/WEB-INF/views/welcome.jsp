
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="logoutUrl" value="/logout"/>
<c:url var="cssUrl" value="/assets/css/auth.css"/>
<c:url var="studentsUrl" value="/students"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome | NTI Learning Hub</title>
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
            <h1>Welcome aboard!</h1>
            <p>Your learning journey starts here.</p>
        </div>

        <div class="welcome-profile">
            <div class="welcome-avatar">
                <c:out value="${sessionScope.user.fullName.substring(0, 1).toUpperCase()}"/>
            </div>

            <h2>
                Hello, <c:out value="${sessionScope.user.fullName}"/>!
            </h2>

            <p>
                We're glad to have you with us.
                Keep learning, keep growing, and make every day count.
            </p>

            <div class="user-email">
                <c:out value="${sessionScope.user.email}"/>
            </div>
        </div>

        <div class="welcome-actions">
            <a href="${studentsUrl}" class="submit-btn">
                Manage students
                <span>&rarr;</span>
            </a>
            <form action="${logoutUrl}" method="post">
                <button type="submit" class="submit-btn logout-btn">Log out <span>&rarr;</span></button>
            </form>

        </div>

    </div>

    <p class="copyright">
        &copy; 2026 NTI Learning Hub. Built for learners.
    </p>

</div>

</body>
</html>
