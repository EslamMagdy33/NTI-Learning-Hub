package com.nti.hub.dao;

import com.nti.hub.model.User;
import com.nti.hub.util.Db;

import java.sql.*;

public class JdbcUserDao implements UserDao{
    private static final int Duplicate_Entry=1062;  // MySQL error code for UNIQUE violation

    @Override
    public boolean save(User user) {
        String sql="INSERT INTO users (full_name,email,password_hash) VALUES (?,?,?)";
        try (Connection con= Db.getConnection(); PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,user.getFullName());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPasswordHash());
            return ps.executeUpdate()==1;
        }catch (SQLIntegrityConstraintViolationException e){
            return false;
        }
        catch (SQLException e){
            if (e.getErrorCode()==Duplicate_Entry)
                return false;
            throw new IllegalStateException("Could not save user",e);
        }
    }

    @Override
    public User findByEmail(String email) {
        if (email == null)
            return null;
        String sql="SELECT full_name,email,password_hash FROM users WHERE email=?";
        try (Connection con = Db.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,email);
            try (ResultSet rs= ps.executeQuery()){
                if (!rs.next()) return null;
                return new User(
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password_hash"));
            }
        }catch (SQLException e){
            throw new IllegalStateException("Could not read user",e);
        }
    }

    @Override
    public int count() {
        String sql="SELECT COUNT(*) FROM users";
        try (Connection con = Db.getConnection();
             PreparedStatement ps=con.prepareStatement(sql);ResultSet rs= ps.executeQuery()) {
            return rs.next()?rs.getInt(1):0;
        }catch (SQLException e){
            throw new IllegalStateException("Could not count users", e);
        }
    }

    @Override
    public boolean deleteByEmail(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException("Could not delete user", e);
        }
    }
}
