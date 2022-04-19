package vendingmachine;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vendingmachine.controller.VendingMachineController;
import vendingmachine.dao.VendingMachineAuditDao;
import vendingmachine.dao.VendingMachineAuditDaoImpl;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoImpl;
import vendingmachine.service.VendingMachineServiceLayer;
import vendingmachine.service.VendingMachineServiceLayerImpl;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImpl;
import vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Grant Gsell
 */
public class VendingMachine {

    public static void main(String[] args) {
        
        /**
         * Dependency Injection with Spring Framework
         */
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("vendingmachine");
        appContext.refresh();
        
        VendingMachineController appController = appContext.getBean("vendingMachineController", VendingMachineController.class);
        appController.run();
        
       
        /* Old version of dependency injection
        // Instantiate the UserIO implementation
        UserIO appIO = new UserIOConsoleImpl();
        
        // Instantiate the view and wire in the UserIO implementation
        VendingMachineView appView = new VendingMachineView(appIO);
        
        // Instantaite the DAO
        VendingMachineDao appDao = new VendingMachineDaoImpl();
        
        // Instantiate the Audit DAO
        VendingMachineAuditDao appAuditDao = new VendingMachineAuditDaoImpl();
        
        // Instantiate the Service Layer and wire in the DAO and Audit DAO
        VendingMachineServiceLayer appService = new VendingMachineServiceLayerImpl(appDao, appAuditDao);
                
        // Instantiate the Controler and wire int the service layer and the view
        VendingMachineController appController = new VendingMachineController(appService, appView);
        
        // Run the applicaiton
        appController.run();
        */
    }
}
