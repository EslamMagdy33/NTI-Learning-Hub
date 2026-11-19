package com.nti.hub.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Day 3 - run it BEFORE Tomcat: right-click > Run 'DbCheck.main()'.
 * [OK]    = Java reaches MySQL, so a problem in the app is in your code.
 * [ERROR] = the problem is MySQL itself, schema.sql or db.properties.
 */
public class DbCheck {

    public static void main(String[] args) {
        try (Connection con = Db.getConnection()) {
            String version = con.getMetaData().getDatabaseProductVersion();
            System.out.println("[OK] Connected to MySQL " + version);

            String sql = "SELECT email, full_name FROM users";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                int rows = 0;
                while (rs.next()) {                        // one row at a time
                    rows++;
                    String email = rs.getString("email");
                    System.out.println("     " + email + "  " + rs.getString("full_name"));
                }
                System.out.println("[OK] Table users: " + rows + " row(s)");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println("  Is MySQL running? Did you run db/schema.sql?");
            System.out.println("  Is your MySQL password in db.properties?");
        }
    }
}
