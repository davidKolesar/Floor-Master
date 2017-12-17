/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Data.AuditDao;
import Model.Audit;
import Model.Order;
import java.time.LocalDate;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
    public class AuditAspect {

    AuditDao aDao;
    

    public AuditAspect(AuditDao aDao) {
        
        this.aDao = aDao;

        
    }

    public Audit addRemoveAudit(JoinPoint jp) {
        LocalDate today;
        today = LocalDate.now();

        Audit audit = new Audit();

        Order o = (Order) jp.getArgs()[0];
        
        String operation = jp.getSignature().getName();

        audit.setOrderId(o.getID());

        audit.setOperation(operation);
        
        audit.setDate(today.toString());
        
        Audit addedAudit = aDao.add(audit);
        
        return addedAudit;

    }

        public Audit addUpdateAudit(JoinPoint jp) {
        LocalDate today;
        today = LocalDate.now();

        Audit audit = new Audit();

        Order o = (Order) jp.getArgs()[3];
        
        String operation = jp.getSignature().getName();

        audit.setOrderId(o.getID());

        audit.setOperation(operation);
        
        audit.setDate(today.toString());
        
        Audit addedAudit = aDao.add(audit);
        
        return addedAudit;

    }
    
}
