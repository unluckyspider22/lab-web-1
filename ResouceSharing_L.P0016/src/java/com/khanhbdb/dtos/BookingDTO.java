/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.dtos;

import java.sql.Timestamp;

public class BookingDTO {

    private int bookingId;
    private String email;
    private Timestamp bookingTimestamp;
    private Timestamp returnTimestamp;
    private String requestMessage;
    private String responseMessage;
    private String censorName;
    private int bookingStatusId;
    private String bookingStatusName;
    private Timestamp insTimestamp;
    private int resourceId;
    private String resourceName;
    private int quantity;
    private boolean isDeleted;
    private int availableQuantity;

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getBookingStatusName() {
        return bookingStatusName;
    }

    public void setBookingStatusName(String bookingStatusName) {
        this.bookingStatusName = bookingStatusName;
    }

    public BookingDTO() {
    }

    public BookingDTO(int bookingId, String email, Timestamp bookingTimestamp, Timestamp returnTimestamp, String requestMessage, String responseMessage, String censorName, int bookingStatusId, String bookingStatusName, Timestamp insTimestamp, int resourceId, String resourceName, int quantity, boolean isDeleted) {
        this.bookingId = bookingId;
        this.email = email;
        this.bookingTimestamp = bookingTimestamp;
        this.returnTimestamp = returnTimestamp;
        this.requestMessage = requestMessage;
        this.responseMessage = responseMessage;
        this.censorName = censorName;
        this.bookingStatusId = bookingStatusId;
        this.bookingStatusName = bookingStatusName;
        this.insTimestamp = insTimestamp;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
    }

    public BookingDTO(int bookingId, String email, Timestamp bookingTimestamp, Timestamp returnTimestamp, String requestMessage, String censorName, int bookingStatusId, String bookingStatusName, Timestamp insTimestamp, int resourceId, String resourceName, int quantity) {
        this.bookingId = bookingId;
        this.email = email;
        this.bookingTimestamp = bookingTimestamp;
        this.returnTimestamp = returnTimestamp;
        this.requestMessage = requestMessage;
        this.responseMessage = responseMessage;
        this.censorName = censorName;
        this.bookingStatusId = bookingStatusId;
        this.bookingStatusName = bookingStatusName;
        this.insTimestamp = insTimestamp;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
    }

    public BookingDTO(int bookingId, String email, Timestamp bookingTimestamp, Timestamp returnTimestamp, String requestMessage, String responseMessage, String censorName, int bookingStatusId, String bookingStatusName, Timestamp insTimestamp, int resourceId, String resourceName, int quantity) {
        this.bookingId = bookingId;
        this.email = email;
        this.bookingTimestamp = bookingTimestamp;
        this.returnTimestamp = returnTimestamp;
        this.requestMessage = requestMessage;
        this.responseMessage = responseMessage;
        this.censorName = censorName;
        this.bookingStatusId = bookingStatusId;
        this.bookingStatusName = bookingStatusName;
        this.insTimestamp = insTimestamp;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.quantity = quantity;
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

    public Timestamp getBookingTimestamp() {
        return bookingTimestamp;
    }

    public void setBookingTimestamp(Timestamp bookingTimestamp) {
        this.bookingTimestamp = bookingTimestamp;
    }

    public Timestamp getReturnTimestamp() {
        return returnTimestamp;
    }

    public void setReturnTimestamp(Timestamp returnTimestamp) {
        this.returnTimestamp = returnTimestamp;
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

    public Timestamp getInsTimestamp() {
        return insTimestamp;
    }

    public void setInsTimestamp(Timestamp insTimestamp) {
        this.insTimestamp = insTimestamp;
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
