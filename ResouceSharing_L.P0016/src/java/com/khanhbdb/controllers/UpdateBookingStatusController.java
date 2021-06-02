/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.daos.BookingDAO;
import com.khanhbdb.daos.ResourceDAO;
import com.khanhbdb.dtos.AccountDTO;
import com.khanhbdb.dtos.BookingDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author donguyen
 */
public class UpdateBookingStatusController extends HttpServlet {

    private final static String SUCCESS = "manager_view_booking.jsp";

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
        try {
            String rdParam = request.getParameter("rdConfirm");
            String resMess = request.getParameter("responseMessage");
            HttpSession session = request.getSession();
            AccountDTO account = (AccountDTO) session.getAttribute("USER");         
            BookingDTO booking = (BookingDTO) session.getAttribute("BOOKINGDETAIL");
            int bookingStatusId = Integer.parseInt(rdParam);
            String censorName = account.getName();
            int bookingId = booking.getBookingId();
            int availableQuantity = booking.getAvailableQuantity();
            int bookingQuantity =  booking.getQuantity();
            if(bookingQuantity > availableQuantity){
            request.setAttribute("ERROR", "OUT OF STOCK. Available quantity is " + availableQuantity);
            }
            BookingDAO bookingDao = new BookingDAO();
            ResourceDAO resourceDao = new ResourceDAO();
            boolean result = bookingDao.updateBookingStatus(bookingId, resMess, bookingStatusId, censorName);
            if (result) {
                int resourceId = booking.getResourceId();
                int finalQuantity = availableQuantity - bookingQuantity;
                resourceDao.updateResourceQuantity(resourceId, finalQuantity);
                request.setAttribute("RESULTUPDATESUCC", "UPDATE SUCCESSFULLY !");
            } else {
                request.setAttribute("RESULTUPDATEFAI", "UPDATE FAIL !");

            }
        } catch (Exception e) {
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
