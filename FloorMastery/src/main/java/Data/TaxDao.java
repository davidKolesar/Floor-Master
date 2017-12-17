/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Order;
import Model.Tax;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Kolesar
 */
public interface TaxDao {

    public Tax decode(String state);

    public List<String> getStates();

}
