/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.daos.AccountDAO;
import com.khanhbdb.dtos.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CheckVerifyCodeController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(CheckVerifyCodeController.class.getName());

    private final String SUCCESS_EMPLOYEE = "employee.jsp";
    private final String SUCCESS_LEADER = "leader.jsp";
    private final String SUCCESS_MANAGER = "manager.jsp";
    private final String INVALID = "verify_account.jsp";
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
        String url = INVALID;
        try {
            String userVerificationCode = request.getParameter("txtVerifyCode");
            HttpSession session = request.getSession();
            AccountDTO accountDto = (AccountDTO) session.getAttribute("USER");
            AccountDAO dao = new AccountDAO();
            String verifyCodeDB = dao.getAccountVerifyCode(accountDto.getEmail());
            if (userVerificationCode.equals(verifyCodeDB.trim())) {
                dao.activeAccount(accountDto.getEmail(), "Active");
                if(accountDto.getRoleId() == 1){
                    url = SUCCESS_MANAGER;
                }else if(accountDto.getRoleId() == 2){
                    url = SUCCESS_LEADER;
                }else if(accountDto.getRoleId() == 3){
                    url = SUCCESS_EMPLOYEE;
                }else{
                    url = ERROR;
                    request.setAttribute("ERROR", "Invalid role");
                }
            } else {
                request.setAttribute("VERIFICATION_ERROR", "Incorrect verification code!");
            }
        } catch (Exception e) {
            LOGGER.error("Error at RegisterController: " + e.toString());
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
