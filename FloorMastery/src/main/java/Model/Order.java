/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author apprentice
 */
public class Order {

    private int ID;
    private String name;
    private String state;
    private double sqft;
    private Tax taxRate;
    private Products newProduct;
    private double total;
    private double subtotal;
    private LocalDate dateDate;

    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setProduct(Products newProduct) {
        this.newProduct = newProduct;
    }

    public Products getProduct() {
        return newProduct;
    }

    public void setTax(Tax taxRate) {
        this.taxRate = taxRate;
    }

    public Tax getTax() {
        return taxRate;
    }

    public void setID(int nextID) {
        this.ID = nextID;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public double getSqft() {
        return sqft;
    }

    public void setsqft(double sqft) {
        this.sqft = sqft;
    }

      public void setDateDate(LocalDate dateDate) {
        this.dateDate = dateDate;
    }

    public LocalDate getDateDate() {
        return dateDate;
    }
    
    
}
