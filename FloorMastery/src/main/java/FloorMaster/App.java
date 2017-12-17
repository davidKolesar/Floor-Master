/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMaster;

import FloorMasterController.FloorMasterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mycompany.consoleio.ConsoleIO;

/**
 *
 * @author apprentice
 */
public class App {
    
    
    
    
    public static void main(String[] args) {
        
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //Class Path XML Application Context reads in the XML passed from parameter (applicationContext.xml)
       
        FloorMasterController sc = (FloorMasterController) ctx.getBean("MasterController");
        //"DVDController" parameter is the name of the Bean and casts it into the controller class
        
        sc.run();
        
    }
    
}
