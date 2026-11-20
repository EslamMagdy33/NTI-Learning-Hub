package com.nti.hub.web.servlet;

import com.nti.hub.util.TraceLog;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("traces", TraceLog.recent());
        req.getRequestDispatcher("/WEB-INF/views/lifecycle.jsp").forward(req, resp);
    }
}
