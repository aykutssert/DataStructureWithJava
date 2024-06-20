public class HeadPhone extends ElectronicDevice{
    /**
     * This is the constructor for the HeadPhone class.
     * @param category The category of the headphone.
     * @param name The name of the headphone.
     * @param price The price of the headphone.
     * @param quantity The quantity of the headphone.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public HeadPhone(String category, String name, double price, int quantity) {
        super( category, name, price, quantity); // calling the constructor of the parent class
    }
    /**
     * This is the default constructor for the HeadPhone class.
     */
    public HeadPhone() {
        super();
    }
}
