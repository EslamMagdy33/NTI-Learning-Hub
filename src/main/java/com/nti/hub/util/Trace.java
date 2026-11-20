package com.nti.hub.util;

import java.util.ArrayList;
import java.util.List;

// Trace : collecting the steps of the current request
public final class Trace {
    // Each Request has its own steps so ThreadLocal
    private static final ThreadLocal<List<String>> CURRENT=new ThreadLocal<>();

    private Trace() {
    }
    public static void begin(){CURRENT.set(new ArrayList<>());}
    public static void step(String text){
        List<String> steps=CURRENT.get();
        if (steps != null)
            steps.add(text);
    }

    public static List<String> end(){
        List<String> steps = CURRENT.get();
        CURRENT.remove();               // Tomcat reuses threads, so always clean up
        return steps == null ? List.of() : steps;
    }
}
