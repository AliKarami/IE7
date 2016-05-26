package brs;

import java.sql.*;
import java.util.*;
import java.io.*;

public class HSQLHandler {

    public static void init_tables() {
        // load the JDBC Driver
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Unable to load HSQLDB JDBC driver");
        }


        try {
            // connecting to the database
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
            Statement stmt = con.createStatement();
            BufferedReader in = new BufferedReader(new FileReader("init.sql"));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = in.readLine()) != null) {
                sb.append(str + "\n ");
            }
            in.close();
            stmt.executeUpdate(sb.toString());
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                } catch (SQLException ex) {
                        // Don't throw from here or you'll lose any return/exception from above
                        System.err.println("Failed to close connection" + ex);
                    }
                }
        } catch (Exception e) {
            System.err.println("Failed to Execute" + " init.sql" +". The error is"+ e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) {
        // load the JDBC Driver
        try {
            Class.forName("org.hsqldb.JDBCDriver");
            // connecting to the database
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    // Don't throw from here or you'll lose any return/exception from above
                    System.err.println("Failed to close connection" + ex);
                }
            }
            return rs;
        } catch (Exception ex) {
            System.err.println("Unable to load HSQLDB JDBC driver");
            return null;
        }
    }

    public int executeUpdate(String sql) {
        // load the JDBC Driver
        try {
            Class.forName("org.hsqldb.JDBCDriver");
            // connecting to the database
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
            Statement stmt = con.createStatement();
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    // Don't throw from here or you'll lose any return/exception from above
                    System.err.println("Failed to close connection" + ex);
                }
            }
            return stmt.executeUpdate(sql);
        } catch (Exception ex) {
            System.err.println("Unable to load HSQLDB JDBC driver");
            return -2;
        }

    }
}
