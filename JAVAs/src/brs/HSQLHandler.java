package brs;

import java.sql.*;
import java.util.*;
import java.io.*;

public class HSQLHandler {

    public static void init_tables() throws IOException,SQLException{
        // load the JDBC Driver
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Unable to load HSQLDB JDBC driver");
        }
        // connecting to the database
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
        Statement stmt = con.createStatement();

        try {
            BufferedReader in = new BufferedReader(new FileReader("init.sql"));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = in.readLine()) != null) {
                sb.append(str + "\n ");
            }
            in.close();
            stmt.executeUpdate(sb.toString());
        } catch (Exception e) {
            System.err.println("Failed to Execute" + " init.sql" +". The error is"+ e.getMessage());
        } finally {
            con.close();
        }
    }

    public ResultSet executeQuery(String sql) throws IOException,SQLException{
        // load the JDBC Driver
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Unable to load HSQLDB JDBC driver");
        }
        // connecting to the database
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    public int executeUpdate(String sql) throws IOException,SQLException{
        // load the JDBC Driver
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Unable to load HSQLDB JDBC driver");
        }
        // connecting to the database
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
        Statement stmt = con.createStatement();

        return stmt.executeUpdate(sql);
    }
}
