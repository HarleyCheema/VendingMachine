package vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author Grant
 */
@Component
public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao {
    // Create audit file 
    public static final String AUDIT_FILE = "audit.txt";
    
    
    /**
     * Takes in a string entry, adds a timestamp and writes the timestamp, entry
     * to an audit log file.
     * 
     * @param entry the string to be logged.
     * @throws VendingMachinePersistenceException 
     */
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException{
        // Create a Print Write object
        PrintWriter out;
        
        // Try to open the file, opens without overwriting
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }catch(IOException e){
            throw new VendingMachinePersistenceException("Could not persist audit information", e);
        }
        
        // Create timestamp variable
        LocalDateTime timeStamp = LocalDateTime.now();
        
        // Write the data to the file
        out.println(timeStamp.toString() + " : " + entry);
        
        // Ensure the entire buffer was written
        out.flush();
        
        // Close the print writer to prevent memory leaks
        out.close();
    }
}
