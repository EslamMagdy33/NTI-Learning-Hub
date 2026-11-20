package com.nti.hub.util;

import java.util.List;

public class RequestTrace {
    private final String time, method, path;
    private final int status;
    private final long millis;
    private final List<String> steps;

    public RequestTrace(String time, String method, String path, int status, long millis, List<String> steps) {
        this.time = time;
        this.method = method;
        this.path = path;
        this.status = status;
        this.millis = millis;
        this.steps = steps;
    }
    public String getTime() { return time; }
    public String getMethod() { return method; }
    public String getPath() { return path; }
    public int getStatus() { return status; }
    public long getMillis() { return millis; }
    public List<String> getSteps() { return steps; }
}
