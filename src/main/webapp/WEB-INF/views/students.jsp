
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:url var="studentsUrl" value="/students"/>
<c:url var="welcomeUrl" value="/welcome"/>
<c:url var="logoutUrl" value="/logout"/>
<c:url var="cssUrl" value="/assets/css/app.css"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students | NTI Learning Hub</title>
    <link rel="stylesheet" href="${cssUrl}">
</head>
<body>

<div class="app-shell">

    <!-- Top Navigation -->
    <header class="topbar">
        <a href="${welcomeUrl}" class="brand">
            <span class="brand-icon">N</span>
            <span>NTI Learning Hub</span>
        </a>

        <nav class="topbar-nav">
            <a href="${welcomeUrl}" class="nav-link">Dashboard</a>
            <a href="${studentsUrl}" class="nav-link active">Students</a>
            <form action="${logoutUrl}" method="post" class="logout-form">
                <button type="submit" class="nav-logout">Log out</button>
            </form>
            <a href="<c:url value='/lifecycle'/>" class="nav-link">Lifecycle</a>.
        </nav>
    </header>

    <main class="page-content">

        <!-- Page Heading -->
        <section class="page-heading">
            <div>
                <span class="eyebrow">MANAGEMENT</span>
                <h1>Students</h1>
                <p>Manage student records and keep track of their learning paths.</p>
            </div>

            <div class="student-count">
                <span class="count-number">
                    <c:out value="${students.size()}"/>
                </span>
                <span class="count-label">Total Students</span>
            </div>
        </section>

        <!-- Feedback Messages -->
        <c:if test="${param.added == '1'}">
            <div class="alert success">
                <span class="alert-icon">&#10003;</span>
                Student added successfully.
            </div>
        </c:if>

        <c:if test="${param.deleted == '1'}">
            <div class="alert success">
                <span class="alert-icon">&#10003;</span>
                Student deleted successfully.
            </div>
        </c:if>
        <c:if test="${param.deleteFailed == '1'}">
            <div class="alert error">
                Student could not be deleted. Please try again.
            </div>
        </c:if>

        <c:if test="${not empty errors}">
            <div class="alert error">
                <strong>Please check the following:</strong>
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li><c:out value="${error}"/></li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <!-- Add Student Form -->
        <section class="content-card form-card">
            <div class="section-heading">
                <div class="section-icon">&#43;</div>
                <div>
                    <h2>Add a student</h2>
                    <p>Enter the student's information below.</p>
                </div>
            </div>

            <form action="${studentsUrl}" method="post" class="student-form">

                <input type="hidden" name="action" value="add">

                <div class="form-grid">

                    <div class="form-group">
                        <label for="fullName">Full name</label>
                        <input
                            type="text"
                            id="fullName"
                            name="fullName"
                            placeholder="e.g. Ahmed Mohamed"
                            value="<c:out value='${fullName}'/>"
                            required>
                    </div>

                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            placeholder="student@example.com"
                            value="<c:out value='${email}'/>"
                            required>
                    </div>

                    <div class="form-group full-width">
                        <label for="track">Learning track</label>
                        <input
                            type="text"
                            id="track"
                            name="track"
                            placeholder="e.g. Java Enterprise Development"
                            value="<c:out value='${track}'/>"
                            required>
                    </div>

                </div>

                <div class="form-actions">
                    <button type="submit" class="primary-btn">
                        Add student <span>&rarr;</span>
                    </button>
                </div>

            </form>
        </section>

        <!-- Students Table -->
        <section class="content-card table-card">

            <div class="section-heading table-heading">
                <div>
                    <h2>All students</h2>
                    <p>A list of all registered students.</p>
                </div>
            </div>

            <c:choose>
                <c:when test="${empty students}">
                    <div class="empty-state">
                        <div class="empty-icon">&#128100;</div>
                        <h3>No students yet</h3>
                        <p>Add your first student using the form above.</p>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="table-wrapper">
                        <table class="students-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Student</th>
                                    <th>Email address</th>
                                    <th>Learning track</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="s" items="${students}">
                                    <tr>
                                        <td class="student-id">
                                            #<c:out value="${s.id}"/>
                                        </td>

                                        <td class="student-name">
                                            <c:out value="${s.fullName}"/>
                                        </td>

                                        <td class="student-email">
                                            <c:out value="${s.email}"/>
                                        </td>

                                        <td>
                                            <span class="track-badge">
                                                <c:out value="${s.track}"/>
                                            </span>
                                        </td>

                                        <td>
                                            <form action="${studentsUrl}"
                                                  method="post"
                                                  class="delete-form"
                                                  onsubmit="return confirm('Are you sure you want to delete this student?');">

                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="${s.id}">

                                                <button type="submit" class="delete-btn">
                                                    Delete
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>

        </section>

        <footer class="page-footer">
            &copy; 2026 NTI Learning Hub. Built for learners.
        </footer>

    </main>
</div>

</body>
</html>
