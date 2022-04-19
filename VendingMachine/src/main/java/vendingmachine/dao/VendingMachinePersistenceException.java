package vendingmachine.dao;

/**
 *
 * @author Grant
 */
public class VendingMachinePersistenceException extends Exception {
    
    /**
     * Custom exception for vending machine project.
     * 
     * @param message 
     */
    public VendingMachinePersistenceException(String message) {
        super(message);
    }
    
    /**
     * Custom exception for vending machine project.
     * 
     * @param message
     * @param cause 
     */
    public VendingMachinePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
