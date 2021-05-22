package com.khanhbdb.daos;

import com.khanhbdb.dtos.CategoryDTO;
import com.khanhbdb.utils.DBUtil;
import com.khanhbdb.utils.GlobalVar;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class CategoryDAO implements Serializable {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<CategoryDTO> getCategories(int roleId) throws SQLException, NamingException, NoSuchAlgorithmException {
        List<CategoryDTO> result = null;
        String sql;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                if (roleId == GlobalVar.EMPLOYEE_ROLE) {
                    sql = "SELECT CategoryId, CategoryName FROM Categories WHERE IsForEmployee = 1";
                } else if (roleId == GlobalVar.LEADER_ROLE) {
                    sql = "SELECT CategoryId, CategoryName FROM Categories WHERE IsForLeader = 1";
                } else {
                    sql = "SELECT CategoryId, CategoryName FROM Categories WHERE IsForManager = 1";
                }
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                result = new ArrayList();
                while (rs.next()) {
                    int categoryId = rs.getInt("CategoryId");
                    String categoryName = rs.getString("CategoryName");
                    CategoryDTO cateDto = new CategoryDTO(categoryId, categoryName);
                    result.add(cateDto);
                }
            }
        } finally {
            DBUtil.closeConnection(conn, ps, rs);
        }
        return result;
    }
}
