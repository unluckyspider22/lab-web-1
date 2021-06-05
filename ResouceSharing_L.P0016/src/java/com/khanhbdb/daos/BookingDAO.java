/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.daos;

import com.khanhbdb.dtos.BookingDTO;
import com.khanhbdb.utils.CommonUltil;
import com.khanhbdb.utils.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class BookingDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean createBooking(int resourceId, int quantity, Timestamp bookingDate, Timestamp resturnDate, String email, String requestMessage) throws NamingException, SQLException {
        boolean result = false;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Bookings(ResourceId,Quantity,BookingDate,ReturnDate,Email,RequestMessage,BookingStatusId,InsDate) VALUES(?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, resourceId);
                ps.setInt(2, quantity);
                ps.setTimestamp(3, bookingDate);
                ps.setTimestamp(4, resturnDate);
                ps.setString(5, email);
                ps.setString(6, requestMessage);
                ps.setInt(7, 1);
                ps.setTimestamp(8, CommonUltil.getCurrentTimestampSql());

                result = (ps.executeUpdate() > 0);
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public boolean updateBookingStatus(int bookingId, String respMess, int bookingStatusId, String censorName) throws NamingException, SQLException {
        boolean result = false;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE Bookings SET ResponseMessage = ? , BookingStatusId = ? ,CensorName = ? WHERE BookingId = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, respMess);
                ps.setInt(2, bookingStatusId);
                ps.setString(3, censorName);
                ps.setInt(4, bookingId);
                result = (ps.executeUpdate() > 0);
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public BookingDTO getBookingById(int bookingId) throws NamingException, SQLException {
        BookingDTO result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingId,Email,BookingDate,ReturnDate,RequestMessage,ResponseMessage,bk.BookingStatusId,bk.Name,InsDate,b.Quantity,r.ResourceId,r.ResourceName,r.AvailableQuantity "
                        + "FROM Bookings as b, Resources as r,BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId AND b.BookingId = ? AND b.IsDeleted = 0";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, bookingId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("Email");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    Timestamp returnDate = rs.getTimestamp("ReturnDate");
                    String requestMessage = rs.getString("RequestMessage");
                    String responseMessage = rs.getString("ResponseMessage");
                    int bookingStatusId = rs.getInt("BookingStatusId");
                    String bookingStatusName = rs.getString("Name");
                    Timestamp insDate = rs.getTimestamp("InsDate");
                    int quantity = rs.getInt("Quantity");
                    int resourceId = rs.getInt("ResourceId");
                    int availableQuantity = rs.getInt("AvailableQuantity");
                    String resourceName = rs.getString("ResourceName");
                    result = new BookingDTO(bookingId, email, bookingDate, insDate, requestMessage, responseMessage, resourceName, bookingId, bookingStatusName, insDate, resourceId, resourceName, quantity);
                    result.setBookingStatusId(bookingStatusId);
                    result.setAvailableQuantity(availableQuantity);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public List<BookingDTO> getNewBookings() throws NamingException, SQLException {
        List<BookingDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingId,Email,BookingDate,ReturnDate,RequestMessage,bk.BookingStatusId,bk.Name,InsDate,b.Quantity,r.ResourceId,r.ResourceName "
                        + "FROM Bookings as b, Resources as r,BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId AND b.BookingStatusId =1 AND b.IsDeleted = 0 ORDER BY InsDate ASC";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                result = new ArrayList<BookingDTO>();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    String email = rs.getString("Email");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    Timestamp returnDate = rs.getTimestamp("ReturnDate");
                    String requestMessage = rs.getString("RequestMessage");
                    String bookingStatusName = rs.getString("Name");
                    Timestamp insDate = rs.getTimestamp("InsDate");
                    int quantity = rs.getInt("Quantity");
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    BookingDTO dto = new BookingDTO(bookingId, email, bookingDate, insDate, requestMessage, requestMessage, resourceName, bookingId, bookingStatusName, insDate, resourceId, resourceName, quantity);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public boolean deleteBooking(int bookingId) throws SQLException, NamingException {
        boolean result = false;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE Bookings SET IsDeleted = 1 WHERE BookingId = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, bookingId);
                result = (ps.executeUpdate() > 0);
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;

    }

    public List<BookingDTO> getBookingHistory(String email, String pattern) throws NamingException, SQLException {
        List<BookingDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingId,Email,BookingDate,ReturnDate,RequestMessage,ResponseMessage,CensorName,bk.BookingStatusId,bk.Name,InsDate,b.Quantity,r.ResourceId,r.ResourceName "
                        + "FROM Bookings as b, Resources as r,BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId AND Email = ? AND ResourceName LIKE ?  AND b.IsDeleted = 0 ORDER BY BookingDate DESC";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, "%" + pattern + "%");
                rs = ps.executeQuery();
                result = new ArrayList<BookingDTO>();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    Timestamp returnDate = rs.getTimestamp("ReturnDate");
                    String requestMessage = rs.getString("RequestMessage");
                    String bookingStatusName = rs.getString("Name");
                    Timestamp insDate = rs.getTimestamp("InsDate");
                    int quantity = rs.getInt("Quantity");
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    String responseMessage = rs.getString("ResponseMessage");
                    String censor = rs.getString("CensorName");
                    BookingDTO dto = new BookingDTO(bookingId, email, bookingDate, returnDate, requestMessage, resourceName, bookingId, bookingStatusName, insDate, resourceId, resourceName, quantity);
                    dto.setResponseMessage(responseMessage);
                    dto.setCensorName(censor);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public List<BookingDTO> getBookingHistoryByReqDate(String email, String pattern, Timestamp fromDate, Timestamp toDate) throws NamingException, SQLException {
        List<BookingDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingId,Email,BookingDate,ReturnDate,RequestMessage,ResponseMessage,CensorName,bk.BookingStatusId,bk.Name,InsDate,b.Quantity,r.ResourceId,r.ResourceName "
                        + "FROM Bookings as b, Resources as r,BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId AND Email = ? AND ResourceName LIKE ? AND InsDate >= ? AND InsDate <= ?  AND b.IsDeleted = 0 ORDER BY BookingDate DESC";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, "%" + pattern + "%");
                ps.setTimestamp(3, fromDate);
                ps.setTimestamp(5, toDate);
                rs = ps.executeQuery();
                result = new ArrayList<BookingDTO>();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    Timestamp returnDate = rs.getTimestamp("ReturnDate");
                    String requestMessage = rs.getString("RequestMessage");
                    String bookingStatusName = rs.getString("Name");
                    Timestamp insDate = rs.getTimestamp("InsDate");
                    int quantity = rs.getInt("Quantity");
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    String responseMessage = rs.getString("ResponseMessage");
                    String censor = rs.getString("CensorName");
                    BookingDTO dto = new BookingDTO(bookingId, email, bookingDate, returnDate, requestMessage, resourceName, bookingId, bookingStatusName, insDate, resourceId, resourceName, quantity);
                    dto.setResponseMessage(responseMessage);
                    dto.setCensorName(censor);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public List<BookingDTO> searchBookingByBookingStatus(String pattern, int start, int total, int bookingStatusId) throws NamingException, SQLException {
        List<BookingDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingId,Email,BookingDate,ReturnDate,RequestMessage,bk.BookingStatusId,bk.Name,InsDate,b.Quantity,r.ResourceId,r.ResourceName "
                        + "FROM Bookings as b, Resources as r,BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId"
                        + " AND b.BookingStatusId = ? AND r.ResourceName LIKE ? AND b.IsDeleted = 0 ORDER BY InsDate ASC "
                        + "OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, bookingStatusId);
                ps.setString(2, "%" + pattern + "%");
                ps.setInt(3, start);
                ps.setInt(4, total);
                rs = ps.executeQuery();
                result = new ArrayList<BookingDTO>();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    String email = rs.getString("Email");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    Timestamp returnDate = rs.getTimestamp("ReturnDate");
                    String requestMessage = rs.getString("RequestMessage");
                    String bookingStatusName = rs.getString("Name");
                    Timestamp insDate = rs.getTimestamp("InsDate");
                    int quantity = rs.getInt("Quantity");
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    BookingDTO dto = new BookingDTO(bookingId, email, bookingDate, returnDate, requestMessage, resourceName, bookingId, bookingStatusName, insDate, resourceId, resourceName, quantity);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public List<BookingDTO> searchBookingByResourceName(String pattern, int start, int total) throws NamingException, SQLException {
        List<BookingDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingId,Email,BookingDate,ReturnDate,RequestMessage,bk.BookingStatusId,bk.Name,InsDate,b.Quantity,r.ResourceId,r.ResourceName "
                        + "FROM Bookings as b, Resources as r,BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId"
                        + " AND r.ResourceName LIKE ? AND b.IsDeleted = 0 ORDER BY InsDate ASC "
                        + "OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + pattern + "%");
                ps.setInt(2, start);
                ps.setInt(3, total);
                rs = ps.executeQuery();
                result = new ArrayList<BookingDTO>();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    String email = rs.getString("Email");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    Timestamp returnDate = rs.getTimestamp("ReturnDate");
                    String requestMessage = rs.getString("RequestMessage");
                    String bookingStatusName = rs.getString("Name");
                    Timestamp insDate = rs.getTimestamp("InsDate");
                    int quantity = rs.getInt("Quantity");
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    BookingDTO dto = new BookingDTO(bookingId, email, bookingDate, returnDate, requestMessage, resourceName, bookingId, bookingStatusName, insDate, resourceId, resourceName, quantity);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public List<BookingDTO> searchBookingByBookingDate(String pattern, int start, int total, Timestamp startDate, Timestamp endDate) throws NamingException, SQLException {
        List<BookingDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingId,Email,BookingDate,ReturnDate,RequestMessage,bk.BookingStatusId,bk.Name,InsDate,b.Quantity,r.ResourceId,r.ResourceName "
                        + "FROM Bookings as b, Resources as r,BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId"
                        + " AND r.ResourceName LIKE ? AND b.IsDeleted = 0 AND BookingDate >= ? AND BookingDate <= ? ORDER BY InsDate ASC "
                        + "OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + pattern + "%");
                ps.setTimestamp(2, startDate);
                ps.setTimestamp(3, endDate);
                ps.setInt(4, start);
                ps.setInt(5, total);
                rs = ps.executeQuery();
                result = new ArrayList<BookingDTO>();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    String email = rs.getString("Email");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    Timestamp returnDate = rs.getTimestamp("ReturnDate");
                    String requestMessage = rs.getString("RequestMessage");
                    String bookingStatusName = rs.getString("Name");
                    Timestamp insDate = rs.getTimestamp("InsDate");
                    int quantity = rs.getInt("Quantity");
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    BookingDTO dto = new BookingDTO(bookingId, email, bookingDate, returnDate, requestMessage, resourceName, bookingId, bookingStatusName, insDate, resourceId, resourceName, quantity);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public List<BookingDTO> searchBookingByBookingDateWithStatus(String pattern, int start, int total, Timestamp startDate, Timestamp endDate, int bookingStatusId) throws NamingException, SQLException {
        List<BookingDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT BookingId,Email,BookingDate,ReturnDate,RequestMessage,bk.BookingStatusId,bk.Name,InsDate,b.Quantity,r.ResourceId,r.ResourceName "
                        + "FROM Bookings as b, Resources as r,BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId"
                        + " AND r.ResourceName LIKE ? AND b.IsDeleted = 0 AND BookingDate >= ? AND BookingDate <= ? AND b.BookingStatusId = ? ORDER BY InsDate ASC "
                        + "OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + pattern + "%");
                ps.setTimestamp(2, startDate);
                ps.setTimestamp(3, endDate);
                ps.setInt(4, bookingStatusId);
                ps.setInt(5, start);
                ps.setInt(6, total);
                rs = ps.executeQuery();
                result = new ArrayList<BookingDTO>();
                while (rs.next()) {
                    int bookingId = rs.getInt("BookingId");
                    String email = rs.getString("Email");
                    Timestamp bookingDate = rs.getTimestamp("BookingDate");
                    Timestamp returnDate = rs.getTimestamp("ReturnDate");
                    String requestMessage = rs.getString("RequestMessage");
                    String bookingStatusName = rs.getString("Name");
                    Timestamp insDate = rs.getTimestamp("InsDate");
                    int quantity = rs.getInt("Quantity");
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    BookingDTO dto = new BookingDTO(bookingId, email, bookingDate, returnDate, requestMessage, resourceName, bookingId, bookingStatusName, insDate, resourceId, resourceName, quantity);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public int getNumberOfBookingByBookingDate(String pattern, Timestamp startDate, Timestamp endDate) throws NamingException, SQLException {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(b.BookingId) as Result "
                        + "FROM Bookings as b, Resources as r "
                        + "WHERE b.ResourceId = r.ResourceId "
                        + " AND r.ResourceName LIKE ? AND b.IsDeleted = 0 AND BookingDate >= ? AND BookingDate <= ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + pattern + "%");
                ps.setTimestamp(2, startDate);
                ps.setTimestamp(3, endDate);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Result");
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public int getNumberOfBookingByBookingDateWithStatus(String pattern, Timestamp startDate, Timestamp endDate, int bookingStatusId) throws NamingException, SQLException {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(b.BookingId) as Result "
                        + "FROM Bookings as b, Resources as r, BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId "
                        + " AND r.ResourceName LIKE ? AND b.IsDeleted = 0 AND BookingDate >= ? AND BookingDate <= ? AND b.BookingStatusId = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + pattern + "%");
                ps.setTimestamp(2, startDate);
                ps.setTimestamp(3, endDate);
                ps.setInt(4, bookingStatusId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Result");
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public int getNumberOfBookingByResourceName(String pattern) throws NamingException, SQLException {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(b.BookingId) as Result FROM Bookings as b, Resources as r, BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId"
                        + " AND r.ResourceName LIKE ? AND b.IsDeleted = 0";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + pattern + "%");
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Result");
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public int getNumberOfBookingByBookingStatus(String pattern, int bookingStatusId) throws NamingException, SQLException {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(b.BookingId) as Result FROM Bookings as b, Resources as r, BookingStatus as bk "
                        + "WHERE b.ResourceId = r.ResourceId AND b.BookingStatusId = bk.BookingStatusId"
                        + " AND r.ResourceName LIKE ? AND bk.BookingStatusId = ? AND b.IsDeleted = 0";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + pattern + "%");
                ps.setInt(2, bookingStatusId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Result");
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public int getTotalQuantityBooked(BookingDTO dto) throws SQLException, NamingException {
        int result = -1;
        Date dateBook = new Date(dto.getBookingTimestamp().getTime());
        Date dateReturn = new Date(dto.getReturnTimestamp().getTime());
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "Select SUM(dbo.Bookings.Quantity) as TotalQuantity "
                        + "From Bookings "
                        + "Where dbo.Bookings.ResourceId = ? and dbo.Bookings.BookingStatusId = 2 and CAST(dbo.Bookings.BookingDate as date) <=  CAST(? as date) and  CAST(? as date) <= CAST(dbo.Bookings.ReturnDate as date)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, dto.getResourceId());
                ps.setDate(2, dateBook);
                ps.setDate(3, dateReturn);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("TotalQuantity");
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }
}
