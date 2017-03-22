package pl.marceen.learningjavaee.mockito.entity;

/**
 * @author Marcin Zaremba
 */
public class Event {
    private String timestamp;
    private String customerName;
    private String type;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
