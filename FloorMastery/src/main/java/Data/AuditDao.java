/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Model.Audit;
import Model.Order;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author apprentice
 */
public interface AuditDao {

 
    Audit add(Audit audit);

    List<Audit> decode();

    void encode();

}
