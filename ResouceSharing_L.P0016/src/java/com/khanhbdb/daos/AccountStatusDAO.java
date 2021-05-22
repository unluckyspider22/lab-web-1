/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.daos;

import com.khanhbdb.utils.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class AccountStatusDAO implements Serializable {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public int getStatusIdByName(String statusName) throws SQLException, NamingException {
        int result = -1;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT AccountStatusId FROM AccountStatus WHERE Name = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, statusName);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("AccountStatusId");
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }
}
