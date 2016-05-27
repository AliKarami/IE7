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
            return;
        }


        try {
            // connecting to the database
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
            Statement stmt = con.createStatement();
            BufferedReader in = new BufferedReader(new FileReader("/opt/tomcat/apache-tomcat-8.0.33/webapps/bourse/WEB-INF/classes/brs/init.sql"));
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
            System.err.println("Failed to Execute" + " init.sql" +". The error is "+ e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) {
        // load the JDBC Driver
        try {
            //Class.forName("org.hsqldb.JDBCDriver");
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
            System.err.println("ex Query error");
            return null;
        }
    }

    public int executeUpdate(String sql) {
        // load the JDBC Driver
        int res = -2;
        try {
            //Class.forName("org.hsqldb.JDBCDriver");
            // connecting to the database
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
            Statement stmt = con.createStatement();
            res = stmt.executeUpdate(sql);
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                    return res;
                } catch (SQLException ex) {
                    // Don't throw from here or you'll lose any return/exception from above
                    System.err.println("Failed to close connection" + ex);
                }
            }
        } catch (Exception ex) {
            System.err.println("ex Update error.");
            return -2;
        }
        return res;
    }
}
