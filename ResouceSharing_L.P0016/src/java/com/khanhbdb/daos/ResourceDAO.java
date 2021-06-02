/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.daos;

import com.khanhbdb.dtos.ResourceDTO;
import com.khanhbdb.utils.DBUtil;
import com.khanhbdb.utils.GlobalVar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ResourceDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ResourceDTO getResourceById(int resourceId) throws NamingException, SQLException {
        ResourceDTO result = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT r.ResourceId,r.ResourceName,c.CategoryName,r.Color,r.AvailableQuantity,r.Quantity "
                    + "FROM Resources as r, Categories as c "
                    + "WHERE r.CategoryId = c.CategoryId AND r.IsAvailable = 1 AND  ResourceId = ?";
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, resourceId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String resourceName = rs.getString("ResourceName");
                    String categoryName = rs.getString("CategoryName");
                    String color = rs.getString("Color");
                    int availableQuantity = rs.getInt("AvailableQuantity");
                    int quantity = rs.getInt("Quantity");
                    result = new ResourceDTO(resourceId, resourceName, categoryName, color, availableQuantity, quantity);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);

        }
        return result;
    }

    public boolean updateResourceQuantity(int resourceId,int availableQuantity) throws NamingException, SQLException {
        boolean result = false;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE Resources SET AvailableQuantity = ? WHERE ResourceId = ?";
                ps = conn.prepareStatement(sql);     
                ps.setInt(1, availableQuantity);
                ps.setInt(2, resourceId);
                result = (ps.executeUpdate() > 0);
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public List<ResourceDTO> searchResourceAvailable(String pattern, int start, int total, int roleId) throws SQLException, NamingException {
        List<ResourceDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "";
            String selectString = "SELECT r.ResourceId,r.ResourceName, c.CategoryName, r.Color,r.AvailableQuantity "
                    + "FROM Resources as r, Categories as c "
                    + "WHERE r.CategoryId = c.CategoryId AND r.IsAvailable = 1 AND r.ResourceName LIKE ? AND ";
            String pagingString = " ORDER BY r.ResourceName ASC "
                    + "OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
            String roleString = "";
            if (conn != null) {
                if (roleId == GlobalVar.EMPLOYEE_ROLE) {
                    roleString = "c.IsForEmployee = 1";
                } else if (roleId == GlobalVar.LEADER_ROLE) {
                    roleString = "c.IsForLeader = 1";
                } else {
                    roleString = "c.IsForManager = 1";
                }
                sql = selectString + roleString + pagingString;
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + pattern + "%");
                ps.setInt(2, start);
                ps.setInt(3, total);
                System.out.println("SQL String: " + sql);
                rs = ps.executeQuery();
                result = new ArrayList<ResourceDTO>();
                while (rs.next()) {
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    String categoryName = rs.getString("CategoryName");
                    String color = rs.getString("Color");
                    int availableQuantity = rs.getInt("AvailableQuantity");
                    ResourceDTO dto = new ResourceDTO(resourceId, resourceName, categoryName, color, availableQuantity);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public List<ResourceDTO> searchResourceAvailableByCateId(String pattern, int start, int total, int roleId, int cateId) throws SQLException, NamingException {
        List<ResourceDTO> result = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "";
            String selectString = "SELECT r.ResourceId,r.ResourceName, c.CategoryName, r.Color,r.AvailableQuantity "
                    + "FROM Resources as r, Categories as c "
                    + "WHERE r.CategoryId = c.CategoryId AND r.IsAvailable = 1 AND r.CategoryId = ? AND r.ResourceName LIKE ? AND ";
            String pagingString = " ORDER BY r.ResourceName ASC "
                    + "OFFSET ? ROWS FETCH FIRST ? ROWS ONLY";
            String roleString = "";
            if (conn != null) {
                if (roleId == GlobalVar.EMPLOYEE_ROLE) {
                    roleString = "c.IsForEmployee = 1";
                } else if (roleId == GlobalVar.LEADER_ROLE) {
                    roleString = "c.IsForLeader = 1";
                } else {
                    roleString = "c.IsForManager = 1";
                }
                sql = selectString + roleString + pagingString;
                ps = conn.prepareStatement(sql);
                ps.setInt(1, cateId);
                ps.setString(2, "%" + pattern + "%");
                ps.setInt(3, start);
                ps.setInt(4, total);
                System.out.println("SQL String: " + sql);
                rs = ps.executeQuery();
                result = new ArrayList<ResourceDTO>();
                while (rs.next()) {
                    int resourceId = rs.getInt("ResourceId");
                    String resourceName = rs.getString("ResourceName");
                    String categoryName = rs.getString("CategoryName");
                    String color = rs.getString("Color");
                    int availableQuantity = rs.getInt("AvailableQuantity");
                    ResourceDTO dto = new ResourceDTO(resourceId, resourceName, categoryName, color, availableQuantity);
                    result.add(dto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }

    public int getNumberOfAvailableResourceByCateId(String pattern, int roleId, int cateId) throws SQLException, NamingException {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String selectString = "SELECT COUNT(r.ResourceId) as Result FROM Resources as r, Categories as c WHERE r.CategoryId = c.CategoryId AND r.CategoryId = ? AND "
                    + "r.IsAvailable = 1 AND r.ResourceName LIKE ? AND ";
            String sql = "";
            String roleString = "";
            if (conn != null) {
                if (roleId == GlobalVar.EMPLOYEE_ROLE) {
                    roleString = "c.IsForEmployee = 1";
                } else if (roleId == GlobalVar.LEADER_ROLE) {
                    roleString = "c.IsForLeader = 1";
                } else {
                    roleString = "c.IsForManager = 1";
                }
                sql = selectString + roleString;
                System.out.println("SQL String: " + sql);
                ps = conn.prepareStatement(sql);
                ps.setInt(1, cateId);
                ps.setString(2, "%" + pattern + "%");
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

    public int getNumberOfAvailableResource(String pattern, int roleId) throws SQLException, NamingException {
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String selectString = "SELECT COUNT(r.ResourceId) as Result FROM Resources as r, Categories as c WHERE r.CategoryId = c.CategoryId AND "
                    + "r.IsAvailable = 1 AND r.ResourceName LIKE ? AND ";
            String sql = "";
            String roleString = "";
            if (conn != null) {
                if (roleId == GlobalVar.EMPLOYEE_ROLE) {
                    roleString = "c.IsForEmployee = 1";
                } else if (roleId == GlobalVar.LEADER_ROLE) {
                    roleString = "c.IsForLeader = 1";
                } else {
                    roleString = "c.IsForManager = 1";
                }
                sql = selectString + roleString;
                System.out.println("SQL String: " + sql);
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
}
