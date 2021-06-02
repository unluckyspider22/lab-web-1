/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.daos;

import com.khanhbdb.dtos.BookingStatusDTO;
import com.khanhbdb.utils.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author donguyen
 */
public class BookingStatusDAO implements Serializable {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<BookingStatusDTO> getBookingStatus() throws NamingException, SQLException {
        List<BookingStatusDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingStatusId,Name FROM BookingStatus";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                result = new ArrayList<BookingStatusDTO>();
                while (rs.next()) {
                    int bookingStatusId = rs.getInt("BookingStatusId");
                    String name = rs.getString("Name");
                    BookingStatusDTO dto = new BookingStatusDTO(bookingStatusId, name);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }
}
