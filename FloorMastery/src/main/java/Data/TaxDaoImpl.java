/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Order;
import Model.Tax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * David Kolesar
 */
public class TaxDaoImpl implements TaxDao {

    private String FILENAME = "Taxes.txt";
    private static final String TOKEN = ","; //remember to change this if necessaray
    private double taxDouble = 0;

    public Tax decode(String state) {

        List<String> stateList = new ArrayList<String>();
        Map<String, Double> taxMap = new HashMap<String, Double>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            sc.nextLine();

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                double taxRate = Double.parseDouble(stringParts[1]);
                String stateName = stringParts[0];
                taxMap.put(stateName, taxRate);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        Tax thisTax = new Tax();
        double taxDouble = taxMap.get(state);

        thisTax.setState(state);
        thisTax.setTaxRate(taxDouble);

        return thisTax;
    }

    public List<String> getStates() {

        List<String> stateList = new ArrayList<String>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            sc.nextLine();

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                String stateName = stringParts[0];
                double taxRate = Double.parseDouble(stringParts[1]);
                stateList.add(stateName);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return stateList;
    }

}
