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
public class Products {

    private String productType;
    private double CostPerSqFt;
    private double LaborCostPerSqFt;
    private double area;
    private double materialCosts;
    private double laborTotal;

    public void setLaborTotal(double laborTotal) {
        this.laborTotal = laborTotal;
    }

    public double getLaborTotal() {
        return laborTotal;
    }

    public void setMaterialCosts(double materialCosts) {
        this.materialCosts = materialCosts;
    }

    public double getMaterialCosts() {
        return materialCosts;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setLaborCostPerSqFt(double LaborCostPerSqFt) {
        this.LaborCostPerSqFt = LaborCostPerSqFt;
    }

    public double getLaborCostPerSqFt() {
        return LaborCostPerSqFt;
    }

    public void setCostPerSqFt(double CostPerSqFt) {
        this.CostPerSqFt = CostPerSqFt;
    }

    public double getCostPerSqFt() {
        return CostPerSqFt;
    }

}
