/**
 * This is the ElectronicDevice class that implements the Device interface.
 */
public class ElectronicDevice implements Device {
    private String category; // category of the device 
    private String name; // name of the device
    private double price;   // price of the device
    private int quantity; // quantity of the device

    /**
     * This is the constructor for the ElectronicDevice class.
     * @param category The category of the device.
     * @param name The name of the device.
     * @param price The price of the device.
     * @param quantity The quantity of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public ElectronicDevice(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    /**
     * This is the default constructor for the ElectronicDevice class.
     */
    public ElectronicDevice() {
        this.category = "";
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

    
    /**
     * This method returns the category of the device.
     * @return The category of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * This method returns the name of the device.
     * @return The name of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This method returns the price of the device.
     * @return The price of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * This method returns the quantity of the device.
     * @return The quantity of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method sets the name of the device.
     * @param name The name of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the price of the device.
     * @param price The price of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method sets the quantity of the device.
     * @param quantity The quantity of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
