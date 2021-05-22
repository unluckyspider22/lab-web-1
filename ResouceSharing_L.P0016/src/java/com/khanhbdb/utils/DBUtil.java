/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil implements Serializable {

    public static Connection getConnection() throws NamingException, SQLException {
//        Connection conn = null;
//        Context context = new InitialContext();
//        Context end = (Context) context.lookup("java:comp/env");
//        DataSource ds = (DataSource) end.lookup("DBConn");
//        conn = ds.getConnection();
//        return conn;
        Connection conn = null;
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName = ResourceSharing", "sa", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {

        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
