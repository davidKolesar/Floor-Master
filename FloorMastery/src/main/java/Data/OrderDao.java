/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Order;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.List;

/**
 *
 * @author David Kolesar
 */
public interface OrderDao {
    Order add(Order thisOrder);                                             //CREATE
    Order read(int id);                                                     //READ
    void update(LocalDate editDate, LocalDate originalDateDate, int ID, Order thisOrder);       //UPDATE
    void delete(Order thisOrder);                                           //DELETE
    List readAll(String date);
}


