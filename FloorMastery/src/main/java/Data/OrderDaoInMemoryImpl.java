/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author David Kolesar
 */
public class OrderDaoInMemoryImpl implements OrderDao {

    public LocalDate date = LocalDate.now();
    public List<Order> ordersList = null;
    private Integer nextID = 1;
    private Order myDVD = new Order();

    public OrderDaoInMemoryImpl() {

        for (Order o : ordersList) {

            if (o.getID() == nextID) {
                nextID = o.getID() + 1;
            }
        }
    }

    @Override
    public Order add(Order thisOrder) {

        thisOrder.setID(nextID);

        ordersList.add(thisOrder);

        nextID++;

        return thisOrder;

    }

    public Order read(int ID) {

        for (Order o : ordersList) {

            if (o.getID() == nextID) {
                return o;
            }
        }
        return null;

    }

    @Override
    public void update(LocalDate editDate, LocalDate originalDateDate, int originalID, Order thisOrder) {

        for (Order o : ordersList) {

            if (o.getID() == thisOrder.getID()) {
                if (o.getDateDate() == thisOrder.getDateDate()) {
                    delete(o);
                    add(thisOrder);

                } else {
                    ordersList.remove(o);
                    ordersList.add(thisOrder);

                }

            }
        }
    }

    @Override
    public void delete(Order thisOrder) {

        for (Order o : ordersList) {

            if (o.getID() == thisOrder.getID()) {

                ordersList.remove(o);
                break;
            }
        }

    }

    public List<Order> list() {

        return ordersList;

        //DVDs is a variable assigned to the value of list.
    }

    public List readAll(String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
