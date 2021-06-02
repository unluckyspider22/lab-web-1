/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.dtos;

import java.io.Serializable;

public class BookingStatusDTO implements Serializable {

    private int bookingStatusId;
    private String name;

    public BookingStatusDTO() {
    }

    public int getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(int bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookingStatusDTO(int bookingStatusId, String name) {
        this.bookingStatusId = bookingStatusId;
        this.name = name;
    }

}
