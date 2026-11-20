package com.nti.hub.dao;

import com.nti.hub.model.Student;
import com.nti.hub.util.Db;
import com.nti.hub.util.Trace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDao implements StudentDao{
    @Override
    public List<Student> findAll() {
        Trace.step("JdbcStudentDao.findAll: SELECT via PreparedStatement on MySQL");
        String sql="SELECT id, full_name, email, track FROM students ORDER BY id DESC";
        List<Student> result=new ArrayList<>();
        try (Connection con = Db.getConnection();
             PreparedStatement ps=con.prepareStatement(sql);
             ResultSet rs=ps.executeQuery()){
            while (rs.next()) {
                result.add(new Student(rs.getInt("id"), rs.getString("full_name"),
                        rs.getString("email"), rs.getString("track")));
            }
            return result;
        }
        catch (SQLException e){
            throw new IllegalStateException("Could not list students", e);
        }
    }

    @Override
    public boolean Save(Student student) {
        Trace.step("JdbcStudentDao.save: INSERT ...");
        String sql="INSERT INTO students (full_name, email, track) VALUES (?,?,?)";
        try (Connection con=Db.getConnection();
            PreparedStatement ps =con.prepareStatement(sql)){
            ps.setString(1,student.getFullName());
            ps.setString(2,student.getEmail());
            ps.setString(3,student.getTrack());

            return ps.executeUpdate()==1;
        }catch (SQLException e){
            if (e.getErrorCode()==1062)
                return false;
            throw new IllegalStateException("Could not save student", e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        Trace.step("JdbcStudentDao.deleteById: DELETE ...");
        String sql= "DELETE FROM students WHERE id = ?";
        try(Connection con =Db.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            throw new IllegalStateException("Could not delete student", e);
        }
    }
}
