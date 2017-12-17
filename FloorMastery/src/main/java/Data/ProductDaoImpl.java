/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Products;
import Model.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Kolesar
 */
public class ProductDaoImpl implements ProductDao {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    private String FILENAME = "Products.txt";
    private static final String TOKEN = ","; //remember to change this if necessaray
    private double costPerFoot;
    private double laborCostPerFoot;
    private Products thisProduct = new Products();

    public Products decode(String type) {

        Map<String, List<Double>> costMap = new HashMap<String, List<Double>>();
        List<Double> costs = new ArrayList<Double>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            sc.nextLine();

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                String floorType = stringParts[0];
                costPerFoot = Double.parseDouble(stringParts[1]);
                laborCostPerFoot = Double.parseDouble(stringParts[2]);
                
                
                costs.add(costPerFoot);                                         //populate list
                costs.add(laborCostPerFoot);
                
                
                costMap.put(floorType, costs);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        List<Double> requiredCostList = costMap.get(type);

        for (double c : requiredCostList) {
            thisProduct.setCostPerSqFt(costPerFoot);
            thisProduct.setLaborCostPerSqFt(laborCostPerFoot);
        }

        thisProduct.setProductType(type);

        return thisProduct;
    }
    
    
        public List<String> getProducts() {

            List<String> types = new ArrayList<String>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            sc.nextLine();

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                String floorType = stringParts[0];
                costPerFoot = Double.parseDouble(stringParts[1]);
                laborCostPerFoot = Double.parseDouble(stringParts[2]);
                
                
                types.add(floorType);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        

        return types;
    }
    

}
