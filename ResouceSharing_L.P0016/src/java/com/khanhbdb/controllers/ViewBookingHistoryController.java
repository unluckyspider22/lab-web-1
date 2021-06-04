/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.daos.BookingDAO;
import com.khanhbdb.dtos.AccountDTO;
import com.khanhbdb.dtos.BookingDTO;
import com.khanhbdb.utils.CommonUltil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author donguyen
 */
public class ViewBookingHistoryController extends HttpServlet {

    private final static String SUCCESS_EMP = "employee_view_booking_his.jsp";
    private final static String SUCCESS_LEA = "leader_view_booking_his.jsp";

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
        try {
            String pattern = request.getParameter("txtSearch");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            HttpSession session = request.getSession();
            List<BookingDTO> result = null;
            BookingDAO dao = new BookingDAO();
            AccountDTO dto = (AccountDTO) session.getAttribute("USER");
            if ((fromDate == null || fromDate.equals("")) && ((toDate == null || toDate.equals("")))) {
                result = dao.getBookingHistory(dto.getEmail(), pattern);
            } else {
                Timestamp startDate = CommonUltil.parseStringToDate(fromDate);
                Timestamp endDate = CommonUltil.parseStringToDate(toDate);
                result = dao.getBookingHistoryByReqDate(dto.getEmail(), pattern, startDate, endDate);
            }
            request.setAttribute("LISTHISTORY", result);
        } catch (Exception e) {
            log("ERROR at ViewBookingController: " + e.getMessage());
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
