package vendingmachine.dao;

/**
 *
 * @author Grant
 */
public interface VendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
    
}
