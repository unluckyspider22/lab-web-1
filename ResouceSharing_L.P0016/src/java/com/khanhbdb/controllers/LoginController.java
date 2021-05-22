/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.controllers;

import com.khanhbdb.utils.ReCaptchaUtil;
import com.khanhbdb.daos.AccountDAO;
import com.khanhbdb.daos.CategoryDAO;
import com.khanhbdb.dtos.AccountDTO;
import com.khanhbdb.dtos.CategoryDTO;
import com.khanhbdb.utils.GlobalVar;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"}, loadOnStartup = 0)
public class LoginController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    private final String SUCCESS_EMPLOYEE = "employee.jsp";
    private final String SUCCESS_LEADER = "leader.jsp";
    private final String SUCCESS_NEW_USER = "new_user.jsp";
    private final String SUCCESS_MANAGER = "manager.jsp";
    private final String ERROR = "login.jsp";

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String url = ERROR;
        try {
            boolean valid = true;
            String errorMessage = null;
            //check login
            AccountDAO dao = new AccountDAO();
            AccountDTO dto = new AccountDTO();
            dto = dao.checkLogin(email, password);
            if (dto == null) {
                //login failed
                errorMessage = "Invalid email or password";
                request.setAttribute("ERROR", errorMessage);
            } else {
                String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
                System.out.println(gRecaptchaResponse);
                valid = ReCaptchaUtil.verify(gRecaptchaResponse);
                if (!valid) {
                    //input wrong
                    errorMessage = "Captcha invalid";
                    request.setAttribute("ERROR", errorMessage);
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", dto);
                    // check status of account
                    if (dto.getStatusId() == GlobalVar.NEW_USER) {
                        url = SUCCESS_NEW_USER;
                    } else if (dto.getStatusId() == GlobalVar.DISABLE_USER) {
                        errorMessage = "Your account is disable !";
                        request.setAttribute("ERROR", errorMessage);
                    } else {
                        //check role
                        CategoryDAO cateDao = new CategoryDAO();
                        List<CategoryDTO> listCate = cateDao.getCategories(dto.getRoleId());
                        request.setAttribute("LIST_CATE", listCate);
                        if (dto.getRoleId() == GlobalVar.MANAGER_ROLE) {
                            url = SUCCESS_MANAGER;
                        } else if (dto.getRoleId() == GlobalVar.LEADER_ROLE) {
                            url = SUCCESS_LEADER;
                        } else {
                            url = SUCCESS_EMPLOYEE;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at LoginController" + e.toString());

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
