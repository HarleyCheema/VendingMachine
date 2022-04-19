/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vendingmachine.dto.Items;

/**
 *
 * @author harle
 */
@Component
public class VendingMachineView {
    // UserIO declaration
    private UserIO io;
    
    // Constructor
    public VendingMachineView(){
        
    }
    
    /**
     * Constructor for Dependency Injection
     * 
     * @param io 
     */
    @Autowired
    public VendingMachineView(UserIO io){
        this.io = io;
    }

    // Display, take, and return an integer for a selection from the menu
    public int getMenuSelection() {
        io.print("Main Menu");
        io.print("1. Add funds");
        io.print("2. Buy an item");
        io.print("3. See what you can afford");
        io.print("4. Get change");
        io.print("5. View funds");
        io.print("6. EXIT");
        return io.readInt("Select one of the choices above:", 1, 6);
    }

    public void displayGetChangeBanner() {
        io.print("=== Getting Change ===");
    }

    public void displayAddFundsBanner() {
        io.print("=== Add Funds ===");
    }

    public void displayCurrentFundsBanner() {
        io.print("=== Current Funds ===");
    }

    public String getFundsAdditionAmount() {
        return io.readString("Please enter payment amount.");
    }

    public void displayFundsAddedBanner() {
        io.print("=== Your funds have been added! ===");
    }

    public void displayBuyItemBanner() {
        io.print("=== Buy an Item ===");
    }

    public String getItemCode() {
        return io.readString("Please enter the code for the item to be purchased.");
    }

    public void displayAffordableItemsBanner() {
        io.print("=== Currently Affordable Items ===");
    }

    public void displayExitBanner() {
        io.print("=== Good Bye! ===");
    }

    public void displayUnknownCommandBanner() {
        io.print("=== Unknown Command ===");
    }

    public void displayEnjoyBanner() {
        io.print("=== Enjoy! ===");
    }

    public void displayInventoryBanner() {
        io.print("=== Inventory ===");
    }

    public void displayChangeSuccessBanner() {
        io.print("=== Thank you! ===");
    }

    public void displayChange(int[] change) {
        System.out.println("Your change is...\n " + "Quarters: " + change[0] + "\nDimes: " + change[1] + "\nNickles: " + change[2] + "\nPennies: " + change[3]);
    }

    public void displayChangeFunds(BigDecimal currentCredit) {
        System.out.println("Total change: " + currentCredit);
    }

    public void displayViewFunds(BigDecimal currentCredit) {
        System.out.println("Your current balance is: " + currentCredit);
    }

    public void displayItemList(List<Items> allItems) {
        Collections.sort(allItems, Comparator.comparing((item) -> item.getSelectionCode()));
        for (Items item : allItems) {
            String itemInfo = String.format("#%s : %s - x%s - $%s",
                    item.getSelectionCode(),
                    item.getName(),
                    item.getStock(),
                    item.getPrice());
            io.print(itemInfo);
        }
        io.readString("Please hit enter to continue.");
    }
}
