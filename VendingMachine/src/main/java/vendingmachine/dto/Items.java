package vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Grant Gsell
 */
public class Items {
    // Item fields
    private String name;
    private BigDecimal price;
    private int stock;
    private String selectionCode;

    // Class getters
    public String getName() {
        return name;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public int getStock() {
        return stock;
    }

    public String getSelectionCode(){
        return selectionCode;
    }
    
    // Class setters
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void setSelectionCode(String code){
        this.selectionCode = code;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    // HashCode, Equals, To String overrides
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.price);
        hash = 67 * hash + this.stock;
        hash = 67 * hash + Objects.hashCode(this.selectionCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Items other = (Items) obj;
        if (this.stock != other.stock) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.selectionCode, other.selectionCode)) {
            return false;
        }
        return Objects.equals(this.price, other.price);
    }

    @Override
    public String toString() {
        return "Items{" + "name=" + name + ", price=" + price + ", stock=" + stock + ", selectionCode=" + selectionCode + '}';
    }
    
}
