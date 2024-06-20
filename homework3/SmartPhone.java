public class SmartPhone extends ElectronicDevice {
    /**
     * This is the constructor for the SmartPhone class.
     * @param category The category of the smartphone.
     * @param name The name of the smartphone.
     * @param price The price of the smartphone.
     * @param quantity The quantity of the smartphone.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public SmartPhone(String category, String name, double price, int quantity) {
        super( category, name, price, quantity); // calling the constructor of the parent class
    }
    /**
     * This is the default constructor for the SmartPhone class.
     */
    public SmartPhone() {
        super();
    }
}
