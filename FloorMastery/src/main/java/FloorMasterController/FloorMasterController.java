/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterController;

import Data.AuditDao;
import Data.OrderDao;
import Data.ProductDao;
import Data.TaxDao;
import Model.Order;
import Model.Products;
import Model.Tax;
import com.mycompany.consoleio.ConsoleIO;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Kolesar
 */
public class FloorMasterController {

    private ConsoleIO IO;
    private OrderDao orderdao;
    private TaxDao taxdao;
    private ProductDao productdao;
    private AuditDao auditdao;
    private int counter = 0;

    public FloorMasterController(OrderDao orderdao, TaxDao taxdao, ProductDao productdao, ConsoleIO consoleio, AuditDao auditdao) {

        this.orderdao = orderdao;
        this.IO = consoleio;
        this.taxdao = taxdao;
        this.productdao = productdao;

    }

    public void run() {

        while (1 == 1) {

            IO.displayString("Welcome to floormaster.\n ");
            IO.displayString("1. Place an order");
            IO.displayString("2. View all orders within date");
            IO.displayString("3. Search for order by ID");
            IO.displayString("4. edit an order");
            IO.displayString("5. remove order");
            IO.displayString("6. exit");

            int selection = IO.getIntRange("", 6, 1);

            switch (selection) {

                //Make sure these are in the same order as business specifications
                //SearchID and Get all are going to be combined into the same option
                case 1:
                    addOrder();
                    break;
                case 2:
                    getAll();
                    break;
                case 3:
                    searchID();
                    break;
                case 4:
                    editOrder();
                    break;
                case 5:
                    removeOrder();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }

    private void getAll() {

        boolean keepViewing = true;

        while (keepViewing) {

            LocalDate viewDate = IO.getLocalDate("Enter the date the orders were placed");

            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMddyyyy");
            String dateString = viewDate.format(formatDate);

            dateString = "Orders_".concat(dateString).concat(".txt").replace("-", "");

            List<Order> list = orderdao.readAll(dateString);

            if (list.size() == 0) {
                IO.displayString("There are no orders saved from that date.");
            } else {

                IO.displayString("");
                IO.displayString("=================================");
                IO.displayString("");
                for (int i = 0; i < list.size(); i++) {

                    System.out.println(list.get(i).getID() + "   " + list.get(i).getName());
                    IO.displayString("----------------");

                }

                IO.displayString("");
                int number = IO.getInt("Input the order number: ");

                for (Order a : list) {

                    if (number == a.getID()) {
                        printOrder(a);
                    } else {

                        counter++;

                        if (counter == list.size()) {

                            IO.displayString("There are no orders affiliated with that number");

                        }

                    }

                }
            }

            IO.displayString("Input \"1\" to return to Main Menu, or");
            int viewMore = IO.getInt("Input \"2\" to view another order.");
            if (viewMore == 1) {
                keepViewing = false;
            } else {
                IO.displayString("");
            }

        }
    }

    public void printOrder(Order a) {

        calculations(a);
        Products printMe = a.getProduct();
        Tax taxRate = a.getTax();

        IO.displayString("+==========================================+");
        IO.displayString("Order Number " + a.getID() + "  :  " + a.getName());
        IO.displayString("+==========================================+");
        IO.displayString("State of buyer  :  " + a.getState());
        IO.displayString("+==========================================+");
        System.out.println("Square feet of flooring purchased  :  " + printMe.getArea());
        IO.displayString("+==========================================+");
        System.out.println("Flooring Type  :  " + printMe.getProductType());
        IO.displayString("+==========================================+");
        System.out.println("Type cost per square foot  :  " + printMe.getCostPerSqFt());
        IO.displayString("+==========================================+");
        System.out.println("Labor cost per square foot  :  " + printMe.getLaborCostPerSqFt());
        IO.displayString("+==========================================+");
        System.out.println("Total labor costs :  " + printMe.getLaborTotal());
        IO.displayString("+==========================================+");
        System.out.println("Cost of materials  :  " + printMe.getMaterialCosts());
        IO.displayString("+==========================================+");
        System.out.println("Subtotal  :  " + a.getSubtotal());
        IO.displayString("+==========================================+");
        System.out.println("Tax rate  :  " + taxRate.getTaxRate());
        IO.displayString("+==========================================+");
        System.out.println("Total Cost  :  " + a.getTotal());
        IO.displayString("");

    }

    public void addOrder() {

        LocalDate today;
        today = LocalDate.now();
        Order thisOrder = new Order();
        DateTimeFormatter test = DateTimeFormatter.ofPattern("MMddyyyy");
        String x = today.format(test);
        System.out.println(x);

        IO.displayString("");
        Integer when = IO.getInt("To add new order to today, press \"1\"\nTo add order to another day, input \"0\" .");

        if (when == 1) {
            thisOrder.setDateDate(today);

        } else {
            LocalDate newDate = IO.getLocalDate("Input the date for this order : ");

            DateTimeFormatter formatMe = DateTimeFormatter.ofPattern("MMddyyyy");
            String dateString = newDate.format(formatMe);

            thisOrder.setDateDate(newDate);

        }

        String name = IO.getString("Please enter name you would like attached to the order.");

        List<String> stateList = taxdao.getStates();

        for (int i = 0; i < stateList.size(); i++) {
            IO.displayString("(" + (i + 1) + ") " + stateList.get(i));
            IO.displayString("\t\t");

            if ((i + 1) % 4 == 0) {
                IO.displayString("");
            }
        }

        IO.displayString("Input the number that corresponds to the correct state: ");

        String stateString = "x";

        int stateChoice = IO.getIntRange("", 4, 1);

        switch (stateChoice) {

            case 1:
                stateString = "OH";
                break;
            case 2:
                stateString = "PA";
                break;
            case 3:
                stateString = "MI";
                break;
            case 4:
                stateString = "IN";
                break;

        }
        // Get Product Object
        IO.displayString("");
        IO.displayString("Customer's Product: ");

        // Print the list of options for the products
        List<String> listOfProducts = productdao.getProducts();

        for (int i = 0; i < listOfProducts.size(); i++) {
            IO.displayString("(" + (i + 1) + ") " + listOfProducts.get(i));
            IO.displayString("\t\t");

            if ((i + 1) % 4 == 0) {
                IO.displayString("");
            }
        }

        String productString = x;

        IO.displayString("Input the number that corresponds to the correct product: ");

        int type = IO.getIntRange("", 4, 1);

        switch (type) {

            case 1:
                productString = "Carpet";
                break;
            case 2:
                productString = "Laminate";
                break;
            case 3:
                productString = "Tile";
                break;
            case 4:
                productString = "Wood";
                break;

        }

        double area = IO.getDouble("Please enter the area in sqft you would like to purchase");

        Products addProduct = productdao.decode(productString);

        addProduct.setArea(area);
        Tax taxRate = taxdao.decode(stateString);

        thisOrder.setProduct(addProduct);

        thisOrder.setTax(taxRate);

        thisOrder.setName(name);

        thisOrder.setState(stateString);

        int reallyAdd = IO.getInt("Are you ready to add this order?\nFor YES, input \"1\", For NO, input \"2\" ");

        if (reallyAdd == 1) {
            orderdao.add(thisOrder);
            IO.displayString("Your order has been added!");
        } else {
            IO.displayString("The order has been discarded");
        }
    }

    private void searchID() {

        LocalDate newDate = IO.getLocalDate("Enter the date the order was placed : ");

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = newDate.format(formatDate);

        dateString = "Orders_".concat(dateString).concat(".txt").replace("-", "");

        List<Order> list = orderdao.readAll(dateString);

        int thisID = IO.getInt("Please enter the ID number of the order you would like to see:");

        for (Order a : list) {

            if (thisID == a.getID()) {
                printOrder(a);
            }
        }
    }

    private void editOrder() {

        LocalDate editDate = IO.getLocalDate("Enter the date the order was placed : ");

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = editDate.format(formatDate);

        dateString = "Orders_".concat(dateString).concat(".txt").replace("-", "");

        List<Order> list = orderdao.readAll(dateString);

        if (list.size() == 0) {
            IO.displayString("There are no orders saved from that date.");
        } else {

            IO.displayString("");
            IO.displayString("=================================");
            IO.displayString("");
            for (int i = 0; i < list.size(); i++) {

                System.out.println(list.get(i).getID() + "   " + list.get(i).getName());
                IO.displayString("----------------");

            }

            int thisID = IO.getInt("Please enter the ID number of the order you would like to edit:");

            for (Order a : list) {

                if (thisID == a.getID()) { //this logic should be in dao

                    printOrder(a);

                    String nameEdit = IO.getString("Please enter name you would like attached to the order.");

                    List<String> stateList = taxdao.getStates();

                    for (int i = 0; i < stateList.size(); i++) {
                        IO.displayString("(" + (i + 1) + ") " + stateList.get(i));
                        IO.displayString("\t\t");

                        if ((i + 1) % 4 == 0) {
                            IO.displayString("");
                        }
                    }

                    IO.displayString("Input the number that corresponds to the correct state: ");

                    String stateEdit = "x";

                    int stateChoice = IO.getIntRange("", 4, 1);

                    switch (stateChoice) {

                        case 1:
                            stateEdit = "OH";
                            break;
                        case 2:
                            stateEdit = "PA";
                            break;
                        case 3:
                            stateEdit = "MI";
                            break;
                        case 4:
                            stateEdit = "IN";
                            break;
                    }

                    // Get Product Object
                    IO.displayString("");
                    IO.displayString("Customer's Product: ");

                    // Print the list of options for the products
                    List<String> listOfProducts = productdao.getProducts();

                    for (int i = 0; i < listOfProducts.size(); i++) {
                        IO.displayString("(" + (i + 1) + ") " + listOfProducts.get(i));
                        IO.displayString("\t\t");

                        if ((i + 1) % 4 == 0) {
                            IO.displayString("");
                        }
                    }

                    String productType = "x";

                    IO.displayString("Input the number that corresponds to the correct product: ");

                    int type = IO.getIntRange("", 4, 1);

                    switch (type) {

                        case 1:
                            productType = "Carpet";
                            break;
                        case 2:
                            productType = "Laminate";
                            break;
                        case 3:
                            productType = "Tile";
                            break;
                        case 4:
                            productType = "Wood";
                            break;

                    }

                    Double areaEdit = IO.getDouble("Please enter the new area");
                    LocalDate dateEdit = IO.getLocalDate("Please edit the date of this order");

                    DateTimeFormatter editDateString = DateTimeFormatter.ofPattern("MMddyyyy");

                    String newDateString = dateEdit.format(editDateString);

                    Order editOrder = new Order();

                    editOrder.setID(thisID);
                    editOrder.setName(nameEdit);

                    Tax editTax = taxdao.decode(stateEdit);

                    Products editProduct = productdao.decode(productType);

                    editProduct.setProductType(productType);

                    editProduct.setArea(areaEdit);

                    editOrder.setTax(editTax);
                    editOrder.setState(stateEdit);
                    editOrder.setProduct(editProduct);
                    editOrder.setDateDate(dateEdit);

                    int orderID = editOrder.getID();

                    orderdao.update(editDate, dateEdit, orderID, editOrder);

                }

            }
        }

    }

    public Order calculations(Order o) {

        Products productForCalc = o.getProduct();
        Tax taxForCalc = o.getTax();

        Double unroundedMaterialCost = o.getProduct().getCostPerSqFt() * productForCalc.getArea();
        Double materialCost = ((double) Math.round(unroundedMaterialCost * 100)) / 100;
        productForCalc.setMaterialCosts(materialCost);

        Double unroundedLaborCost = o.getProduct().getLaborCostPerSqFt() * productForCalc.getArea();
        Double laborCost = ((double) Math.round(unroundedLaborCost * 100)) / 100;
        productForCalc.setLaborTotal(laborCost);

        Double sub = unroundedMaterialCost + unroundedLaborCost;

        o.setSubtotal(sub);

        Double taxRateDecimal = taxForCalc.getTaxRate() / 100;

        Double unroundedTax = sub * taxRateDecimal;
        double tax = ((double) Math.round(unroundedTax * 100)) / 100; // round the tax to 2 decimals
        taxForCalc.setTaxTotal(tax);

        double unroundedTotal = sub + tax;
        double total = ((double) Math.floor(unroundedTotal * 100)) / 100; // round the total to 2 decimals
        o.setTotal(total);

        return o;

    }

    public void removeOrder() {

        LocalDate newDate = IO.getLocalDate("Enter the date the order was placed : ");

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateString = newDate.format(formatDate);

        dateString = "Orders_".concat(dateString).concat(".txt").replace("-", "");

        List<Order> list = orderdao.readAll(dateString);

        int thisID = IO.getInt("Please enter the ID number of the order you would like to remove:");

        Order deleteOrder = orderdao.read(thisID);

        deleteOrder.setDateDate(newDate);

        if (deleteOrder == null) {
            IO.displayString("There is no order with that ID number.");
        } else {

            printOrder(deleteOrder);

            int delete = IO.getInt("Are you positive you actually want to delete this order?\nFor YES, input \"1\", For NO, input \"2\"");

            if (delete == 1) {
                orderdao.delete(deleteOrder);
                IO.displayString("The order has been deleted.");
            } else {
                IO.displayString("The order has NOT been deleted.");
            }

        }

    }
}
