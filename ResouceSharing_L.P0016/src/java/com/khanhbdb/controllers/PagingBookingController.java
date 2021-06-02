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
import com.khanhbdb.utils.GlobalVar;
import java.io.IOException;
import java.sql.Timestamp;
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
public class PagingBookingController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private final String SUCCESS_EMP = "employee_view_booking.jsp";
    private final String SUCCESS_MAG = "manager_view_booking.jsp";
    private final String SUCCESS_LEA = "leader_view_booking.jsp";

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
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        HttpSession session = request.getSession();
        AccountDTO accountDto = (AccountDTO) session.getAttribute("USER");
        int roleId = accountDto.getRoleId();
        int bookingStatusId = 0;
        if (roleId == GlobalVar.EMPLOYEE_ROLE) {
            url = SUCCESS_EMP;
        } else if (roleId == GlobalVar.LEADER_ROLE) {
            url = SUCCESS_LEA;
        } else {
            url = SUCCESS_MAG;
        }
        try {
            String bookingStatusParam = request.getParameter("CBSTATUS");
            if (bookingStatusParam.equals("All")) {
                bookingStatusId = 0;
            } else {
                bookingStatusId = Integer.parseInt(bookingStatusParam);
            }
            String pattern = (String) session.getAttribute("PATTERN");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String pageReqId = request.getParameter("page");
            if (pageReqId == null) {
                pageReqId = "1";
            }
            int start = Integer.parseInt(pageReqId);
            start -= 1;
            int total = 20;
            start *= total;
            BookingDAO bookingDao = new BookingDAO();
            List<BookingDTO> listBooking = new ArrayList<BookingDTO>();
            int numberOfResult = 0;
            if ((fromDate == null || fromDate.equals("")) && ((toDate == null || toDate.equals("")))) {
                if (bookingStatusId == 0) {
                    listBooking = bookingDao.searchBookingByResourceName(pattern, start, total);
                    numberOfResult = bookingDao.getNumberOfBookingByResourceName(pattern);
                } else {
                    listBooking = bookingDao.searchBookingByBookingStatus(pattern, start, total, bookingStatusId);
                    numberOfResult = bookingDao.getNumberOfBookingByBookingStatus(pattern, bookingStatusId);
                }
            } else {
                Timestamp startDate = CommonUltil.parseStringToDate(fromDate);
                Timestamp endDate = CommonUltil.parseStringToDate(toDate);
                if (bookingStatusId == 0) {
                    listBooking = bookingDao.searchBookingByBookingDate(pattern, start, total, startDate, endDate);
                    numberOfResult = bookingDao.getNumberOfBookingByBookingDate(pattern, startDate, endDate);
                } else {
                    listBooking = bookingDao.searchBookingByBookingDateWithStatus(pattern, start, total, startDate, endDate, bookingStatusId);
                    numberOfResult = bookingDao.getNumberOfBookingByBookingDateWithStatus(pattern, startDate, endDate, bookingStatusId);
                }
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
                request.setAttribute("LIST_BOOKING", listBooking);
                request.setAttribute("NB_PAGE", listPage);
            }
        } catch (Exception e) {
            LOGGER.error("Error at PagingBooking+Controller: " + e.toString());
            log("ERROR at PagingBookingController: " + e.getMessage());
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
