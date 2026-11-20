package com.nti.hub.web.servlet;

import com.nti.hub.config.Storage;
import com.nti.hub.model.User;
import com.nti.hub.util.PasswordHasher;
import com.nti.hub.util.Trace;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Trace.step("LoginServlet: UserDao.findByEmail + BCrypt check");
        User user = (email == null) ? null : Storage.getUserDao().findByEmail(email.trim().toLowerCase());

        if (user == null || password == null || !PasswordHasher.match(password,user.getPasswordHash())) {
            req.setAttribute("error", "Wrong email or password.");
            req.getRequestDispatcher(VIEW).forward(req, resp);
            return;
        }
        HttpSession session= req.getSession();
        req.changeSessionId();
        session.setAttribute("user", user);
        Trace.step("LoginServlet: login OK -> session created -> redirect /welcome");
        resp.sendRedirect(req.getContextPath() + "/welcome");
    }
}