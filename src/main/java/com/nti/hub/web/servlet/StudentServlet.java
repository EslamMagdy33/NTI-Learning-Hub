package com.nti.hub.web.servlet;

import com.nti.hub.config.Storage;
import com.nti.hub.model.Student;
import com.nti.hub.util.Trace;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/students.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showList(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action=req.getParameter("action");
        if ("delete".equals(action)) {
            delete(req, resp);
        } else if ("add".equals(action)) {
            add(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String track = req.getParameter("track");

        List<String> errors = new ArrayList<>();
        if (fullName == null || fullName.isBlank()) errors.add("Please write the student's name.");
        if (email == null || !email.contains("@")) errors.add("Please write a valid email.");
        if (track == null || track.isBlank()) errors.add("Please write the track.");

        if (errors.isEmpty()){
            Student st=new Student(0,fullName.trim(),email.trim().toLowerCase(),track.trim());
            if (Storage.getStudentDao().Save(st)){
                resp.sendRedirect(req.getContextPath()+"/students?added=1");
                return;
            }
            errors.add("This email is already used by another student.");
        }
        req.setAttribute("errors", errors);
        req.setAttribute("fullName", fullName);
        req.setAttribute("email", email);
        req.setAttribute("track", track);
        showList(req, resp);
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id=Integer.parseInt(req.getParameter("id"));
            if (id <= 0 || !Storage.getStudentDao().deleteById(id)) {
                resp.sendRedirect(req.getContextPath() + "/students?deleteFailed=1");
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/students?deleted=1");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/students?deleteFailed=1");
        }
    }
    private void showList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        Trace.step("StudentServlet: asking StudentDao for all students");

        req.setAttribute("students",Storage.getStudentDao().findAll());

        Trace.step("StudentServlet: forward to students.jsp");
        req.getRequestDispatcher(VIEW).forward(req, resp);
    }
}
