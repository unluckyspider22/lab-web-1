/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.dtos;

import java.io.Serializable;

public class AccountErrorObj implements Serializable {

    private String emailError;
    private String nameError;
    private String passwordError;
    private String roleError;
    private String statusError;

    public AccountErrorObj() {
    }

    public AccountErrorObj(String emailError, String nameError, String passwordError, String roleError, String statusError) {
        this.emailError = emailError;
        this.nameError = nameError;
        this.passwordError = passwordError;
        this.roleError = roleError;
        this.statusError = statusError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }
}
