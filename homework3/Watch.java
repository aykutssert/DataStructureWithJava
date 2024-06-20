public class Watch extends ElectronicDevice{
    /**
     * This is the constructor for the Watch class.
     * @param category The category of the watch.
     * @param name The name of the watch.
     * @param price The price of the watch.
     * @param quantity The quantity of the watch.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public Watch(String category, String name, double price, int quantity) {
        super(category, name, price, quantity); // calling the constructor of the parent class
    }
    /**
     * This is the default constructor for the Watch class.
     */
    public Watch() {
        super();
    }
}
