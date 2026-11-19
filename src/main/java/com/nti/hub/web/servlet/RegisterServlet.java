package com.nti.hub.web.servlet;


import com.nti.hub.config.Storage;
import com.nti.hub.model.User;
import com.nti.hub.util.PasswordHasher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/register.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        List<String> errors = new ArrayList<>();
        if (fullName == null || fullName.isBlank()) {
            errors.add("Please write your name.");
        }

        if (email == null || !email.contains("@")) {
            errors.add("Please write a valid email.");
        }

        if (password == null || password.length() < 6) {
            errors.add("The password needs at least 6 characters.");
        }
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("fullName", fullName);
            req.setAttribute("email", email);
            req.getRequestDispatcher(VIEW).forward(req, resp);
            return;
        }

        String passwordHash= PasswordHasher.hash(password);
        User user=new User(fullName.trim(),email.trim().toLowerCase(),passwordHash);
        boolean saved= Storage.getUserDao().save(user);

        if (saved){
            resp.sendRedirect(req.getContextPath()+"/login?registered=1");
            return;
        }
        errors.add("This email is already registered.");

        req.setAttribute("errors", errors);
        req.setAttribute("fullName", fullName);
        req.setAttribute("email", email);

        req.getRequestDispatcher(VIEW).forward(req, resp);
    }
}
