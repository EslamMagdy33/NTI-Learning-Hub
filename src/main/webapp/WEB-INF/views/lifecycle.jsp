<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:url var="studentsUrl" value="/students"/>
<c:url var="welcomeUrl" value="/welcome"/>
<c:url var="cssUrl" value="/assets/css/app.css"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Lifecycle | NTI Learning Hub</title>
    <link rel="stylesheet" href="${cssUrl}">
    <style>
        .trace-head { display:flex; gap:12px; align-items:center; flex-wrap:wrap; margin-bottom:14px; }
        .trace-head .time { color:#94a3b8; font-size:12px; }
        .trace-head .path { color:#172d4d; font-weight:700; }
        .chip { padding:4px 9px; border-radius:20px; font-size:11px; font-weight:700; background:#eaf4f8; color:#176b87; }
        .chip.bad { background:#fef2f2; color:#b91c1c; }
        .chip.redirect { background:#fffbeb; color:#b45309; }
        .steps { margin-left:22px; color:#334155; font-size:13px; line-height:1.9; }
    </style>
</head>
<body>
<div class="app-shell">
    <header class="topbar">
        <a href="${welcomeUrl}" class="brand"><span class="brand-icon">N</span><span>NTI Learning Hub</span></a>
        <nav class="topbar-nav">
            <a href="${welcomeUrl}" class="nav-link">Dashboard</a>
            <a href="${studentsUrl}" class="nav-link">Students</a>
            <a href="#" class="nav-link active">Lifecycle</a>
        </nav>
    </header>

    <main class="page-content">
        <section class="page-heading">
            <div>
                <span class="eyebrow">UNDER THE HOOD</span>
                <h1>Request lifecycle</h1>
                <p>The last requests this app handled, and every step each one went through. Open other pages, then refresh this one.</p>
            </div>
        </section>

        <c:if test="${empty traces}">
            <div class="content-card"><p>No requests recorded yet. Visit /students or /welcome, then come back.</p></div>
        </c:if>

        <c:forEach var="t" items="${traces}">
            <section class="content-card">
                <div class="trace-head">
                    <span class="time"><c:out value="${t.time}"/></span>
                    <span class="chip"><c:out value="${t.method}"/></span>
                    <span class="path"><c:out value="${t.path}"/></span>
                    <span class="chip ${t.status >= 400 ? 'bad' : (t.status >= 300 ? 'redirect' : '')}">
                        <c:out value="${t.status}"/>
                    </span>
                    <span class="time"><c:out value="${t.millis}"/> ms</span>
                </div>
                <ol class="steps">
                    <c:forEach var="s" items="${t.steps}"><li><c:out value="${s}"/></li></c:forEach>
                </ol>
            </section>
        </c:forEach>
    </main>
</div>
</body>
</html>