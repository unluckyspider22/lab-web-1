/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.utils.CommonUltil;
import com.khanhbdb.daos.AccountDAO;
import com.khanhbdb.dtos.AccountDTO;
import com.khanhbdb.dtos.AccountErrorObj;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    private final String SUCCESS = "SendVerificationCodeController";
    private final String ERROR = "register.jsp";

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
            String name = request.getParameter("txtName").trim();
            String email = request.getParameter("txtEmail").trim();
            String password = request.getParameter("txtPassword");
            String roleName = "Employee";
            String statusName = "New";
            AccountDAO dao = new AccountDAO();
            AccountErrorObj error = null;
            boolean valid = true;
            if (dao.checkExistEmail(email)) {
                if (error == null) {
                    error = new AccountErrorObj();
                }
                error.setEmailError("Email has been registered!");
                valid = false;
            }
            if (valid) {
                String verifyCode = CommonUltil.generateVerifyCode(8);
                Date insDate = CommonUltil.getCurrentDateSql();
                AccountDTO dto = new AccountDTO(email, password, name, roleName, statusName, verifyCode, insDate);
                boolean result = dao.registerAccount(dto);
                if (result) {
                    HttpSession session = request.getSession();
                    session.setAttribute("REGISTER_USER", dto);
                    AccountDTO accountDto = (AccountDTO) session.getAttribute("REGISTER_USER");
                    url = SUCCESS;
                }
            } else {
                url = ERROR;
                request.setAttribute("REGISTER_ERROR", error);
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
