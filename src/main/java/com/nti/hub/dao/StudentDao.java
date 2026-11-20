package com.nti.hub.dao;

import com.nti.hub.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    boolean Save(Student student);
    boolean deleteById(int id);
}
