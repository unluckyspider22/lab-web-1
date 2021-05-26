/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.daos;

import com.khanhbdb.utils.CommonUltil;
import com.khanhbdb.utils.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class BookingDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean createBooking(int resourceId, int quantity, Date bookingDate, Date resturnDate, String email, String requestMessage) throws NamingException, SQLException {
        boolean result = false;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Bookings(ResourceId,Quantity,BookingDate,ReturnDate,Email,RequestMessage,BookingStatusId,InsDate) VALUES(?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, resourceId);
                ps.setInt(2, quantity);
                ps.setDate(3, bookingDate);
                ps.setDate(4, resturnDate);
                ps.setString(5, email);
                ps.setString(6, requestMessage);
                ps.setInt(7, 1);
                ps.setDate(8, CommonUltil.getCurrentDateSql());

                result = (ps.executeUpdate() > 0);
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

}
