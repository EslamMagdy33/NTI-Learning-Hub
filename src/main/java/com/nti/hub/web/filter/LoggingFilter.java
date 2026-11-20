package com.nti.hub.web.filter;

import com.nti.hub.util.RequestTrace;
import com.nti.hub.util.Trace;
import com.nti.hub.util.TraceLog;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;

public class LoggingFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String path=req.getRequestURI().substring(req.getContextPath().length());

        // skip static files and the lifecycle page itself
        if (path.startsWith("/assets/") || path.equals("/lifecycle")) {
            chain.doFilter(req, res);
            return;
        }
        Trace.begin();
        Trace.step("Browser Sent "+ req.getMethod()+" "+path+" to Tomcat");
        Trace.step("LoggingFilter: request entered the filter chain");
        long start = System.nanoTime();
        try {
            chain.doFilter(req, res);
        } finally {
            long millis = (System.nanoTime() - start) / 1_000_000;
            int status = res.getStatus();
            Trace.step("Response " + status + " sent back to the browser");
            RequestTrace trace = new RequestTrace(
                    LocalTime.now().withNano(0).toString(),
                    req.getMethod(), path, status, millis, Trace.end());
            TraceLog.add(trace);
            System.out.println("[REQUEST] " + trace.getMethod() + " " + path
                    + " -> " + status + " (" + millis + " ms)");
        }
    }
}
