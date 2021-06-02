/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.daos.BookingDAO;
import com.khanhbdb.dtos.AccountDTO;
import com.khanhbdb.dtos.ResourceDTO;
import com.khanhbdb.utils.CommonUltil;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class BookingController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(BookingController.class.getName());
    private final static String SUCCESS = "employee_resource_detail.jsp";

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
        String url = SUCCESS;
        boolean valid = true;
        try {
            String quantityParam = request.getParameter("txtQuantity");
            String bookingDateParam = request.getParameter("bookingDate");
            String returnDateParam = request.getParameter("returnDate");
            String requestMessage = request.getParameter("requestMessage");
            HttpSession session = request.getSession();
            session.setAttribute("QUANTITY", quantityParam);
            session.setAttribute("BOOKINGDATE", bookingDateParam);
            session.setAttribute("RETURNDATE", returnDateParam);
            session.setAttribute("REQUESTMESSAGE", requestMessage);
            ResourceDTO resourceDto = (ResourceDTO) session.getAttribute("RESOURCE_DETAIL");
            AccountDTO accountDto = (AccountDTO) session.getAttribute("USER");
            String email = accountDto.getEmail();
            int resourceId = resourceDto.getResourceId();
            int quantity = Integer.parseInt(quantityParam);
            Timestamp bookingDate = CommonUltil.parseStringToDate(bookingDateParam);
            Timestamp returnDate = CommonUltil.parseStringToDate(returnDateParam);
            if (quantity > resourceDto.getAvailableQuantity()) {
                valid = false;
                request.setAttribute("FAIL_WARNING", "Booking FAILED !!!");
                request.setAttribute("MESSAGE", "The quantity must be less than available quantity of resource !");
            }
            if (returnDate.compareTo(bookingDate) <= 0) {
                valid = false;
                request.setAttribute("FAIL_WARNING", "Booking FAILED !!!");
                request.setAttribute("MESSAGE", "The return time must be after the booking time !");
            }

            if (valid) {
                BookingDAO bookingDao = new BookingDAO();
                boolean result
                        = bookingDao.createBooking(resourceId, quantity, bookingDate, returnDate, email, requestMessage);
                if (result) {
                    request.setAttribute("SUCCESS_MESS", "Booking Successfully !!!");
                } else {
                    request.setAttribute("FAIL_MESS", "Booking FAILED !!!");
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at BookingController: " + e.toString());
            e.printStackTrace();
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
