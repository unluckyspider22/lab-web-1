/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.daos.ResourceDAO;
import com.khanhbdb.dtos.AccountDTO;
import com.khanhbdb.dtos.ResourceDTO;
import com.khanhbdb.utils.GlobalVar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author donguyen
 */
public class PagingController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private final String SUCCESS_EMP = "employee.jsp";
    private final String SUCCESS_MAG = "manager.jsp";
    private final String SUCCESS_LEA = "leader.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        HttpSession session = request.getSession();
        AccountDTO accountDto = (AccountDTO) session.getAttribute("USER");
        int roleId = accountDto.getRoleId();
        int cateId = 0;
        if (roleId == GlobalVar.EMPLOYEE_ROLE) {
            url = SUCCESS_EMP;
        } else if (roleId == GlobalVar.LEADER_ROLE) {
            url = SUCCESS_LEA;
        } else {
            url = SUCCESS_MAG;
        }
        try {
            String cateParam = request.getParameter("CBCATEGORY");
            if (cateParam.equals("All")) {
                cateId = 0;
            } else {
                cateId = Integer.parseInt(cateParam);
            }
            //get search keyword
            String pattern = (String) session.getAttribute("PATTERN");
            //get page num
            String pageReqId = request.getParameter("page");
            if (pageReqId == null) {
                pageReqId = "1";
            }
            int start = Integer.parseInt(pageReqId);
            // start is start index of list result, if page is 1, subtract 1 to get start from 0.
            start -= 1;
            //total is the number of result
            int total = 20;
            start *= total;
            //get list base on keyword pattern
            ResourceDAO resourceDao = new ResourceDAO();
            List<ResourceDTO> listResource;
            if (cateId == 0) {
                listResource = resourceDao.searchResourceAvailable(pattern, start, total, roleId);
            } else {
                listResource = resourceDao.searchResourceAvailableByCateId(pattern, start, total, roleId, cateId);
            }
            // get the total number of list base on keyword pattern
            int numberOfResult = 0;
            if (cateId == 0) {
                numberOfResult = resourceDao.getNumberOfAvailableResource(pattern, roleId);
            } else {
                numberOfResult = resourceDao.getNumberOfAvailableResourceByCateId(pattern, roleId, cateId);
            }
            if (numberOfResult == 0) {
                request.setAttribute("SEARCH_MESSAGE", "No result!");
            } else {
                int numberOfPage = (numberOfResult - 1) / total + 1;
                List<Integer> listPage = null;
                listPage = new ArrayList<Integer>();
                for (int i = 1; i <= numberOfPage; i++) {
                    listPage.add(i);
                }
                request.setAttribute("LIST_RESOURCE", listResource);
                request.setAttribute("NB_PAGE", listPage);
            }
        } catch (Exception e) {
            LOGGER.error("Error at PagingController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
