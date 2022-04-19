/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachine.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vendingmachine.service.InsufficientFundsException;
import vendingmachine.service.OutOfStockException;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoImpl;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Items;
import vendingmachine.service.VendingMachineServiceLayer;
import vendingmachine.service.VendingMachineServiceLayerImpl;
import vendingmachine.ui.VendingMachineView;

/**
 *
 * @author harlee
 */
@Component
public class VendingMachineController {

    // Model and VIew class objects
    private VendingMachineView view;
    private VendingMachineDao dao;
    private VendingMachineServiceLayer service;

    // Constructors
    public VendingMachineController() throws VendingMachinePersistenceException{
        view = new VendingMachineView();
        dao = new VendingMachineDaoImpl();
        service = new VendingMachineServiceLayerImpl();
    }
    
    // Constructor for Dependency Injection
    @Autowired
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    
    
    // Function that controls program flow
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {

                listInventory(); //Begins with Inventor Banner and list of inventory every time

                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        addFunds();
                        break;
                    case 2:
                        buyItem();
                        break;
                    case 3:
                        browseAffordableItems();
                        break;
                    case 4:
                        getChange();
                        break;
                    case 5:
                        viewFunds();
                        break;
                    case 6:
                        getChange();
                        keepGoing = false;
                        exitMessage();
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (VendingMachinePersistenceException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientFundsException in) {
            System.out.println("Sorry, you have insufficient funds for this item.");
            run();
        } catch (OutOfStockException st) {
            System.out.println("Sorry, this item is currently out of stock.");
            run();
        }
    }

    private void listInventory() throws VendingMachinePersistenceException {
        view.displayInventoryBanner();
        List<Items> allItems = service.getAllItems();
        view.displayItemList(allItems);
    }

    private void addFunds() throws VendingMachinePersistenceException {
        view.displayAddFundsBanner();
        String fundsAdditionAmount = view.getFundsAdditionAmount();
        BigDecimal fundsBigDecimal = new BigDecimal(fundsAdditionAmount).setScale(2, RoundingMode.HALF_UP);
        service.addCredit(fundsBigDecimal);
        view.displayFundsAddedBanner();
    }

    private void buyItem() throws VendingMachinePersistenceException, InsufficientFundsException, OutOfStockException {
        view.displayBuyItemBanner();
        String itemCode = view.getItemCode();
        service.buyItem(itemCode);
        view.displayEnjoyBanner();
    }

    private void browseAffordableItems() throws VendingMachinePersistenceException {
        view.displayAffordableItemsBanner();
        view.displayItemList(service.purchaseable());        
    }

    private void getChange() throws VendingMachinePersistenceException {
        view.displayGetChangeBanner();
        BigDecimal currentCredit = service.checkCredit();
        int[] change = service.getChange();
        view.displayChange(change);
        view.displayChangeFunds(currentCredit);
        view.displayChangeSuccessBanner();
    }

    private void viewFunds() throws VendingMachinePersistenceException {
        view.displayCurrentFundsBanner();
        BigDecimal currentCredit = service.checkCredit();
        view.displayViewFunds(currentCredit);
    }

    // Let's user know they've exited
    private void exitMessage() {
        view.displayExitBanner();
    }

    // Prompts user for a known command
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    // Requests view to display the menu
    private int getMenuSelection() {
        return view.getMenuSelection();
    }

}
