/**
 * This is the interface for the Device class.
 */
public interface Device {
    

    /**
     * This method returns the category of the device.
     * @return The category of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    String getCategory();

    /**
     * This method returns the name of the device.
     * @return The name of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    String getName();

    /**
     * This method returns the price of the device.
     * @return The price of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    double getPrice();

    /**
     * This method returns the quantity of the device.
     * @return The quantity of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    int getQuantity();

    /**
     * This method sets the category of the device.
     * @param category The category of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    void setName(String name);

    /**
     * This method sets the name of the device.
     * @param name The name of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    void setPrice(double price);

    /**
     * This method sets the price of the device.
     * @param price The price of the device.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    void setQuantity(int quantity);
}

