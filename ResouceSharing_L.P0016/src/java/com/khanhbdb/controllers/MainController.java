/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.dtos.AccountDTO;
import com.khanhbdb.utils.GlobalVar;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class MainController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(MainController.class.getName());
    private final String USER_LOGIN = "login.jsp";
    private final String LOGIN = "LoginController";
    private final String LOGOUT = "LogoutController";
    private final String REGISTER = "RegisterController";
    private final String CHECK_VERIFY_CODE = "CheckVerifyCodeController";
    private final String SEARCH = "SearchController";
    private final String SEARCH_BOOKING = "SearchBookingController";
    private final String EMPLOYEE_PAGE = "employee.jsp";
    private final String LEADER_PAGE = "manager.jsp";
    private final String MANAGER_PAGE = "leader.jsp";
    private final String RESOURCE_DETAIL = "ResourceDetailController";
    private final String BOOKING = "BookingController";
    private final String VIEW_BOOKING = "ViewBookingController";
    private final String UPDATE_BOOKING_STATUS = "UpdateBookingStatusController";
    private final String SEARCH_BOOKING_HIS = "ViewBookingHistoryController";

    private final String ERROR = "error.jsp";

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
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equals("User_Login")) {
                url = USER_LOGIN;
            } else if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("Register")) {
                url = REGISTER;
            } else if (action.equals("CheckVerifyCode")) {
                url = CHECK_VERIFY_CODE;
            } else if (action.equals("Search")) {
                url = SEARCH;
            } else if (action.equals("ResourceDetail")) {
                url = RESOURCE_DETAIL;
            } else if (action.equals("Book")) {
                url = BOOKING;
            } else if (action.equals("ViewBooking")) {
                url = VIEW_BOOKING;
            } else if (action.equals("SearchBooking")) {
                url = SEARCH_BOOKING;
            } else if (action.equals("Confirm Update")) {
                url = UPDATE_BOOKING_STATUS;
            } else if (action.equals("SeachBookingHis")) {
                url = SEARCH_BOOKING_HIS;
            }
            HttpSession session = request.getSession();
            if (session.getAttribute("USER") == null && action.equals("Search")) {
                url = USER_LOGIN;
            }
            if (session.getAttribute("USER") != null && action.equals("Login")) {
                AccountDTO accountDto = (AccountDTO) session.getAttribute("USER");
                if (accountDto.getRoleId() == GlobalVar.EMPLOYEE_ROLE) {
                    url = EMPLOYEE_PAGE;
                } else if (accountDto.getRoleId() == GlobalVar.LEADER_ROLE) {
                    url = LEADER_PAGE;
                } else {
                    url = MANAGER_PAGE;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at MainController: " + e.toString());
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
