/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Items;

/**
 *
 * @author Grant
 */
public interface VendingMachineServiceLayer {
    
    public Items buyItem(String code)  throws VendingMachinePersistenceException, InsufficientFundsException, OutOfStockException;
    
    public int[] getChange()  throws VendingMachinePersistenceException;
    
    public List<Items> purchaseable()  throws VendingMachinePersistenceException;
    
    public void addCredit(BigDecimal amount)  throws VendingMachinePersistenceException;
    
    public BigDecimal checkCredit()  throws VendingMachinePersistenceException;
    
    public List<Items> getAllItems() throws VendingMachinePersistenceException;
}
