/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.dtos;

import java.sql.Date;

public class BookingDTO {

    private int bookingId;
    private String email;
    private Date bookingDate;
    private Date returnDate;
    private String requestMessage;
    private String responseMessage;
    private String censorName;
    private int bookingStatusId;
    private Date insDate;
    private int resourceId;
    private String resourceName;
    private int quantity;
    private boolean isDeleted;

    public BookingDTO() {
    }

    public BookingDTO(int bookingId, String email, Date bookingDate, Date returnDate, String requestMessage, String responseMessage, String censorName, int bookingStatusId, Date insDate, int resourceId, String resourceName, int quantity, boolean isDeleted) {
        this.bookingId = bookingId;
        this.email = email;
        this.bookingDate = bookingDate;
        this.returnDate = returnDate;
        this.requestMessage = requestMessage;
        this.responseMessage = responseMessage;
        this.censorName = censorName;
        this.bookingStatusId = bookingStatusId;
        this.insDate = insDate;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getCensorName() {
        return censorName;
    }

    public void setCensorName(String censorName) {
        this.censorName = censorName;
    }

    public int getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(int bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
}
