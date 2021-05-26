/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanhbdb.dtos;

/**
 *
 * @author donguyen
 */
public class ResourceDTO {

    private int resourceId;
    private String resourceName;
    private int categoryId;
    private String categoryName;
    private String color;
    private int quantity;
    private int availableQuantity;

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public ResourceDTO() {
    }

    public ResourceDTO(int resourceId, String resourceName, String categoryName, String color, int availableQuantity, int quantity) {
        this.resourceName = resourceName;
        this.categoryName = categoryName;
        this.color = color;
        this.availableQuantity = availableQuantity;
        this.resourceId = resourceId;
        this.quantity = quantity;
    }

    public ResourceDTO(int resourceId, String resourceName, String categoryName, String color, int availableQuantity) {
        this.resourceName = resourceName;
        this.categoryName = categoryName;
        this.color = color;
        this.availableQuantity = availableQuantity;
        this.resourceId = resourceId;

    }

    public ResourceDTO(int resourceId, String resourceName, int categoryId, String categoryName, String color, int quantity, int availableQuantity) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.color = color;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
    }

}
