public class Laptop extends ElectronicDevice {
    /**
     * This is the constructor for the Laptop class.
     * @param category The category of the laptop.
     * @param name The name of the laptop.
     * @param price The price of the laptop.
     * @param quantity The quantity of the laptop.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public Laptop(String category, String name, double price, int quantity) {
        super( category, name, price, quantity); // calling the constructor of the parent class
    }
    /**
     * This is the default constructor for the Laptop    class.
     */
    public Laptop() {
        super();
    }
}
