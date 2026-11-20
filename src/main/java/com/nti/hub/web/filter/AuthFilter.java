package com.nti.hub.web.filter;

import com.nti.hub.util.Trace;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/welcome","/students", "/lifecycle"})
public class AuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp,
                            FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            Trace.step("AuthFilter: no session -> redirect to /login");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Trace.step("AuthFilter: session OK -> continue to the servlet");
        chain.doFilter(req, resp);
    }
}
