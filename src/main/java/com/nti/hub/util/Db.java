package com.nti.hub.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Day 3 - the only class that opens connections to MySQL. Settings: db.properties. */
public final class Db {

    private static final Properties SETTINGS = load();

    private Db() {
    }

    /** A new connection - close it when you are done (try-with-resources does it for you). */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                SETTINGS.getProperty("db.url"),
                SETTINGS.getProperty("db.user"),
                SETTINGS.getProperty("db.password"));
    }

    /** Reads src/main/resources/db.properties once, and loads the MySQL driver. */
    private static Properties load() {
        Properties settings = new Properties();
        try (InputStream in = Db.class.getResourceAsStream("/db.properties")) {
            if (in == null) {
                throw new IllegalStateException("db.properties is missing");
            }
            settings.load(in);
            Class.forName("com.mysql.cj.jdbc.Driver");   // in Tomcat: no "No suitable driver"
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Cannot read db.properties or the driver", e);
        }
        return settings;
    }
}
