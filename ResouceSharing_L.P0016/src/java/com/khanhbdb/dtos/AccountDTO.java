/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.dtos;

import java.io.Serializable;
import java.sql.Date;


public class AccountDTO implements Serializable {

    private String email;
    private String password;
    private String name;
    private String roleName;
    private String verifyCode;
    private int statusId;
    private int roleId;
    private String status;
    private Date insDate;

    public AccountDTO(String email, String password, String name, String roleName, String status, String verifyCode, Date insDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleName = roleName;
        this.status = status;
        this.verifyCode = verifyCode;
        this.insDate = insDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public AccountDTO(String email, String password, String name, String roleName, String verifyCode, int statusId, int roleId, String status, Date insDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleName = roleName;
        this.verifyCode = verifyCode;
        this.statusId = statusId;
        this.roleId = roleId;
        this.status = status;
        this.insDate = insDate;
    }

    public String getEmail() {
        return email;
    }

    public AccountDTO() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }
}
