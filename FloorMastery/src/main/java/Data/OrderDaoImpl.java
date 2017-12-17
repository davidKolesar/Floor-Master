/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import com.mycompany.consoleio.ConsoleIO;
import Model.Order;
import Model.Products;
import Model.Tax;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.OptionalInt;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author David Kolesar
 */
public class OrderDaoImpl implements OrderDao {

    private ConsoleIO IO;
    public LocalDate date = LocalDate.now();
    private DateTimeFormatter correctFormat = DateTimeFormatter.ofPattern("MMddyyyy");
    private String ignoreSlash = ",";
    private static final String TOKEN = ",";
    public List<Order> ordersList = null;
    public List<Order> editList = null;
    private Integer nextID = 1;
    private String FILENAME = "Orders_".concat(date.toString()).concat(".txt");

    public OrderDaoImpl() {

        String dateString = date.format(correctFormat);
        String FILENAME = "Orders_".concat(dateString.toString()).concat(".txt");

        FILENAME = FILENAME.replace("-", "");

        ordersList = readAll(FILENAME);

        for (Order o : ordersList) {

            if (o.getID() == nextID) {
                nextID = o.getID() + 1;
            }
        }
    }

    @Override
    public Order add(Order thisOrder) {

        LocalDate originalDateDate = thisOrder.getDateDate();

        ordersList = decode(originalDateDate);

        OptionalInt ID = ordersList.stream().mapToInt(o -> o.getID()).max(); //map to int only takes ints (gets the max ID)
        if (ID.isPresent()) {

            nextID = ID.getAsInt();

        } else {

            nextID = 0;

        }

        nextID++;

        thisOrder.setID(nextID);

        ordersList.add(thisOrder);

        encode(originalDateDate, ordersList);

        return thisOrder;

    }

    public Order read(int ID) {

        nextID = 1;

        for (Order o : ordersList) {

            if (o.getID() == nextID) {
                return o;
            }
        }
        return null;

    }

    @Override
    public void update(LocalDate oldDate, LocalDate originalDateDate, int originalID, Order thisOrder) {

        ordersList = decode(originalDateDate);

        if (ordersList.size() == 0) {

            this.add(thisOrder);

        }

        ordersList = decode(oldDate);
        
        for (int i = 0; i < ordersList.size(); i++) {

            Order o = ordersList.get(i);

            if (o.getID() == (originalID)) {
                if (o.getDateDate() == thisOrder.getDateDate()) {

                    ordersList.set(i, thisOrder);
                    encode(originalDateDate, ordersList);

                } else {
                    this.delete(o);
                    this.add(thisOrder);

                }
                break;
            }

        }
    }

    @Override
    public void delete(Order thisOrder) {

        LocalDate orderDate = thisOrder.getDateDate();

        ordersList = decode(orderDate);

        for (Order o : ordersList) {

            if (o.getID() == thisOrder.getID()) {
                orderDate = o.getDateDate();
                ordersList.remove(o);
                break;
            }
        }

        encode(orderDate, ordersList);
    }

    private void newFile() {

        LocalDate newFile = LocalDate.now();
        String dateString = date.format(correctFormat);
        String currentDateString = "Orders_".concat(dateString.toString()).concat(".txt");

        File currentDateFileName = new File(currentDateString);

        if (!currentDateFileName.exists()) {

            try {
                currentDateFileName.createNewFile();

            } catch (IOException ex) {
                Logger.getLogger(OrderDaoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private String convertDateToString(LocalDate date) {

        //viewDate is the LocalDate Object
        //dateString is the string
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = date.format(formatDate);

        dateString = "Orders_".concat(dateString).concat(".txt").replace("-", "");

        return dateString;
    }

    private void encode(LocalDate orderDate, List<Order> newOrderList) {

        String dateString = convertDateToString(orderDate);

        String FILENAME = dateString;

        newFile();

        PrintWriter out = null;

        try {

            out = new PrintWriter(new FileWriter(FILENAME));

            for (Order o : newOrderList) {
                out.print(o.getID());
                out.print(TOKEN);

                String name = commaCheck(o.getName());
                out.print(name);
                out.print(TOKEN);

                out.print(o.getState());
                out.print(TOKEN);

                Tax taxDouble = o.getTax();             //instantiate taxDTO within orderDTO

                out.print(taxDouble.getTaxRate());      //pull tax double out of taxDTO in orderDTO
                out.print(TOKEN);

                Products thisProduct = o.getProduct();

                out.print(thisProduct.getProductType());
                out.print(TOKEN);

                out.print(thisProduct.getArea());
                out.print(TOKEN);

                out.print(thisProduct.getCostPerSqFt());
                out.print(TOKEN);

                out.print(thisProduct.getLaborCostPerSqFt());
                out.print(TOKEN);

                out.print(thisProduct.getMaterialCosts());
                out.print(TOKEN);

                out.print(thisProduct.getLaborTotal());
                out.print(TOKEN);

                out.print(taxDouble.getTaxTotal());
                out.print(TOKEN);

                out.print(o.getTotal());
                out.println("");

            }

            out.flush();

        } catch (IOException ex) {

        } finally {
            out.close();
        }

    }

    private List<Order> decode(LocalDate orderDate) {

        String dateString = convertDateToString(orderDate);

        String FILENAME = dateString;

        File file = new File(dateString);
        if (!file.exists()) {
            return new ArrayList<Order>();

        }

        this.FILENAME = dateString;

        List<Order> tempOrderList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                // String[] stringParts = currentLine.split(TOKEN);
                String[] stringParts = currentLine.split("(?<!\\\\),"); //"?<!" is negative lookbehind if , does not follow \, you recognize it as a delimiter. It is Regex. 

                Tax thisTax = new Tax();
                Products thisProduct = new Products();
                Order thisOrder = new Order();

                Double taxes = Double.parseDouble(stringParts[3]);
                int orderId = Integer.parseInt(stringParts[0]);
                double area = Double.parseDouble(stringParts[5]);
                double costPerSquareFeet = Double.parseDouble(stringParts[6]);
                double laborCosts = Double.parseDouble(stringParts[7]);
                double materialCosts = Double.parseDouble(stringParts[8]);
                double laborTotal = Double.parseDouble(stringParts[9]);
                double taxTotal = Double.parseDouble(stringParts[10]);
                double grandTotal = Double.parseDouble(stringParts[11]);

                thisOrder.setID(orderId);

                thisOrder.setDateDate(orderDate);
                thisOrder.setName(stringParts[1].replaceAll("\\\\,", ","));                            //thisOrder.setName(stringParts[1].replaceAll("~!~", ","));
                thisOrder.setState(stringParts[2]);
                thisTax.setTaxRate(taxes); //decoder reads the taxRate as a double and sets the new double to TaxDTO
                thisOrder.setTax(thisTax); //TaxDTO is passed into instantiation of Order (thisOrder) with new taxRate 
                thisProduct.setProductType(stringParts[4]);
                thisProduct.setArea(area);
                thisProduct.setCostPerSqFt(costPerSquareFeet);
                thisProduct.setLaborCostPerSqFt(laborCosts);
                thisProduct.setMaterialCosts(materialCosts);
                thisProduct.setLaborTotal(laborTotal);
                thisTax.setTaxTotal(taxTotal);
                thisOrder.setTotal(grandTotal);

                thisOrder.setProduct(thisProduct);

                tempOrderList.add(thisOrder);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return tempOrderList;
    }

    @Override
    public List readAll(String date) {

        String FILENAME = date;

        this.FILENAME = date;

        List<Order> tempOrderList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                // String[] stringParts = currentLine.split(TOKEN);
                String[] stringParts = currentLine.split("(?<!\\\\),"); //"?<!" is negative lookbehind if , does not follow \, you recognize it as a delimiter. It is Regex. 

                Tax thisTax = new Tax();
                Products thisProduct = new Products();
                Order thisOrder = new Order();

                Double taxes = Double.parseDouble(stringParts[3]);
                int orderId = Integer.parseInt(stringParts[0]);
                double area = Double.parseDouble(stringParts[5]);
                double costPerSquareFeet = Double.parseDouble(stringParts[6]);
                double laborCosts = Double.parseDouble(stringParts[7]);
                double materialCosts = Double.parseDouble(stringParts[8]);
                double laborTotal = Double.parseDouble(stringParts[9]);
                double taxTotal = Double.parseDouble(stringParts[10]);
                double grandTotal = Double.parseDouble(stringParts[11]);

                thisOrder.setID(orderId);

                thisOrder.setName(stringParts[1].replaceAll("\\\\,", ","));                            //thisOrder.setName(stringParts[1].replaceAll("~!~", ","));
                thisOrder.setState(stringParts[2]);
                thisTax.setTaxRate(taxes); //decoder reads the taxRate as a double and sets the new double to TaxDTO
                thisOrder.setTax(thisTax); //TaxDTO is passed into instantiation of Order (thisOrder) with new taxRate 
                thisProduct.setProductType(stringParts[4]);
                thisProduct.setArea(area);
                thisProduct.setCostPerSqFt(costPerSquareFeet);
                thisProduct.setLaborCostPerSqFt(laborCosts);
                thisProduct.setMaterialCosts(materialCosts);
                thisProduct.setLaborTotal(laborTotal);
                thisTax.setTaxTotal(taxTotal);
                thisOrder.setTotal(grandTotal);

                thisOrder.setProduct(thisProduct);

                tempOrderList.add(thisOrder);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return tempOrderList;
    }

    public String commaCheck(String userInput) {

        String printInput;
        if (userInput.contains(",")) {
            userInput = userInput.replaceAll(",", "\\\\,");
        }

        printInput = userInput;

        return printInput;

    }

}
