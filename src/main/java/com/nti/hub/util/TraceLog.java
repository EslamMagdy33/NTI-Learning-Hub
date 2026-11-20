package com.nti.hub.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TraceLog {
    private static final int MAX = 30;
    private static final Deque<RequestTrace> TRACES = new ArrayDeque<>();

    private TraceLog() {}

    public static synchronized void add(RequestTrace t){
        TRACES.addFirst(t);
        if (TRACES.size()>MAX)
            TRACES.removeLast();
    }

    public static synchronized List<RequestTrace> recent() {
        return new ArrayList<>(TRACES);      // newest first
    }
}
