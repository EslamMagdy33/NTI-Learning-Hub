package com.nti.hub.model;

public class Student {
    private final int id;
    private final String fullName;
    private final String email;
    private final String track;

    public Student(int id, String fullName, String email, String track) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.track = track;
    }
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTrack() {
        return track;
    }

}
