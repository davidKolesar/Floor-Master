/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author David Kolesar
 */
public class Tax {

    private double taxTotal;
    private double taxRate;
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
   public void setTaxTotal(double taxTotal) {
        this.taxTotal = taxTotal;
    }

    public double getTaxTotal() {
        return taxTotal;
    }
    
}
