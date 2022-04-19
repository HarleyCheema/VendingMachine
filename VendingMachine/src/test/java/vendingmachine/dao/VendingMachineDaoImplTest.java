package vendingmachine.dao;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import vendingmachine.dto.Items;

/**
 *
 * @author Grant Gsell
 */
public class VendingMachineDaoImplTest {
    // Declare instance of DAO
    VendingMachineDao testDao;
    
    public VendingMachineDaoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        // Create string for test file
        String testDb = "testVendingMachine.txt";
        
        // Use file write to blank the file
        new FileWriter(testDb);
        
        // Instantiate the testDao
        testDao = new VendingMachineDaoImpl(testDb);        
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void testAddGetItemMethods() throws VendingMachinePersistenceException {
        // Create a new Item object
        Items item = new Items();
        
        // Populate object fields
        String code = "E4";
        item.setName("Milk Duds");
        item.setPrice(new BigDecimal("3.99"));
        item.setStock(5);
        item.setSelectionCode(code);
        
        // Add the student to the DAO
        testDao.addItem(item.getSelectionCode(), item);
        
        // Retrieve the object at the key code
        Items retrievedItem = testDao.getItem(code);
        
        // Compare the two objects via hash codes 
        assertEquals(item.hashCode(), retrievedItem.hashCode());
        
        // Compare the two objects via equals
        assertTrue(item.equals(retrievedItem));        
    }
    
    
    @Test
    public void testRemoveItemMethod() throws VendingMachinePersistenceException {
        // Create three new items objects
        Items item0 = new Items();
        item0.setName("Milk Duds");
        item0.setPrice(new BigDecimal("3.99"));
        item0.setStock(5);
        item0.setSelectionCode("A2");
        
        Items item1 = new Items();
        item1.setName("Mint Gum");
        item1.setPrice(new BigDecimal("0.99"));
        item1.setStock(12);
        item1.setSelectionCode("D3");
        
        Items item2 = new Items();
        item2.setName("Skittles");
        item2.setPrice(new BigDecimal("1.99"));
        item2.setStock(3);
        item2.setSelectionCode("E8");
                
        // Add all three items to the DAO
        testDao.addItem(item0.getSelectionCode(), item0);
        testDao.addItem(item1.getSelectionCode(), item1);
        testDao.addItem(item2.getSelectionCode(), item2);
        
        // Remove the first item
        Items removedItem = testDao.removeItem(item0.getSelectionCode(), item0);
        
        // Assert returnedItem is equal to item0
        assertEquals(item0, removedItem, "Milk Duds should be removed");
        
        // Generate a list of all remaining item objects
        List<Items> itemsList = testDao.getAllItems();
        
        // Test to ensure the list is not null
        assertNotNull(itemsList, "Items list should not be null");
        
        // Test to ensure the list size is correct, at two
        assertEquals(itemsList.size(), 2, "List started with three items, "
                + "one was removed, two should remain");
        
        // Assert false that the list does not contains the first item
        assertFalse(itemsList.contains(item0), "The first item (item0) was "
                + "removed, thus the items list should not contain item0");
        
        // Assert true that the list contains the other two items
        assertTrue(itemsList.contains(item1), "Item 1 was added, and untouched"
                + " thus should stil be in items list");        
        assertTrue(itemsList.contains(item2), "Item 2 was added, and untouched"
                + " the items list should contain item2");
        
        // Remove second item (item1)
        removedItem = testDao.removeItem(item1.getSelectionCode(), item1);
        
        // Update the itemsList
        itemsList = testDao.getAllItems();
        
        // Assert equals that the removed item is the second item (item1)
        assertEquals(removedItem, item1, "Returned item should be item1");
                
        // Assert true that the list does not contain the second item
        assertTrue(!itemsList.contains(item1), "Item1 was removed from the list"
                + " thus items list should not contain item1");
        
        // Remove the third and last item
        removedItem = testDao.removeItem(item2.getSelectionCode(), item2);
        
        // Update the itemsList
        itemsList = testDao.getAllItems();
        
        // Assert equals that the removed item is the third item (item2)
        assertEquals(removedItem, item2, "Returned item should be item2");
        
        // Assert true that the list does not contain the last item
        assertTrue(!itemsList.contains(item2), "Item2 was removed from the list"
                + " thus items list should not contain item2");
        
        // Assert true that the list is empty
        assertTrue(itemsList.isEmpty(), "Items List should be empty");
        
        // Try to obtain first student via selection code, assert null 
        removedItem = testDao.getItem(item0.getSelectionCode());
        assertNull(removedItem, "Item0 should not exist thus null");
        
        // Try to obtain second student via selection code, assert null
        removedItem = testDao.getItem(item1.getSelectionCode());
        assertNull(removedItem, "Item1 should not exist thus null");
        
        // Try to obtain third student via selectoin code, assert null
        removedItem = testDao.getItem(item2.getSelectionCode());
        assertNull(removedItem, "Item2 should not exist thus null");
    }
    
    
    @Test
    public void testGetAllItemsMethod() throws VendingMachinePersistenceException {
        // Create three new items objects
        Items item0 = new Items();
        item0.setName("Milk Duds");
        item0.setPrice(new BigDecimal("3.99"));
        item0.setStock(5);
        item0.setSelectionCode("A2");
        
        Items item1 = new Items();
        item1.setName("Mint Gum");
        item1.setPrice(new BigDecimal("0.99"));
        item1.setStock(12);
        item1.setSelectionCode("D3");
        
        Items item2 = new Items();
        item2.setName("Skittles");
        item2.setPrice(new BigDecimal("1.99"));
        item2.setStock(3);
        item2.setSelectionCode("E8");
        
        // Add all items to the DAO
        testDao.addItem(item0.getSelectionCode(), item0);
        testDao.addItem(item1.getSelectionCode(), item1);
        testDao.addItem(item2.getSelectionCode(), item2);
        
        // Obtain a list of all items from the DAO
        List<Items> itemsList = testDao.getAllItems();
        
        // Assert that the list is not null
        assertNotNull(itemsList.isEmpty(), "Item list should not be null");
        
        // Assert that the list has a size of three
        assertEquals(itemsList.size(), 3, "There should be three items in the"
                + " list");
        
        // Assert true that the list contains the first item
        assertTrue(itemsList.contains(item0), "Item0 should be in the list");
        
        // Assert true that the list contains the second item
        assertTrue(itemsList.contains(item1), "Item1 should be in the list");
        
        // Assert true that the list contain the third item
        assertTrue(itemsList.contains(item2), "Item2 should be in the list");
    }
    
    
    @Test
    public void testIncrementDecrementItemStockMethod() throws VendingMachinePersistenceException {
        // Create a new Item object
        Items item = new Items();
        
        // Populate items object fields, set stock as two
        String code = "E4";
        item.setName("Milk Duds");
        item.setPrice(new BigDecimal("3.99"));
        item.setStock(2);
        item.setSelectionCode(code);
                
        // Add items object to DAO
        Items returnedItem = testDao.addItem(item.getSelectionCode(), item);
        
        // Assert null due to no collisions in the hashmap
        assertEquals(returnedItem, null, "Returned item should be null since "
                + "there were no collisions, due to empty HashMap");
        
        // Increment item stock by one, return to new item object
        returnedItem = testDao.incrementItemStock(code);
        
        // Assert returned items object stock is eqaul to three
        assertEquals(returnedItem.getStock(), 3, "Item started with a stock of"
                + " two, adding one should yeild a stock of three");
        
        // Decrement items stock by two, return to new item object
        returnedItem = testDao.decrementItemStock(code);
        returnedItem = testDao.decrementItemStock(code);
        
        // Assert returned item stock is equal to 1
        assertEquals(returnedItem.getStock(), 1, "Item had a stock of 3 post "
                + "incrment, decrementing twice should yeild a stock of 1");
        
        // Decrement items stock by one, return to new item object
        returnedItem = testDao.decrementItemStock(code);
        
        // Assert returned item object stock is equal to 0
        assertEquals(0, returnedItem.getStock(), "Item had a stock of 1 post "
                + "double decrement, decrementing one more should yeild zero");
    }
}
