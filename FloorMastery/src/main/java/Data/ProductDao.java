/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Products;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ProductDao {

public Products decode(String type);

 public List<String> getProducts();

}
