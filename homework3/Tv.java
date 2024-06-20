public class Tv extends ElectronicDevice{
    /**
     * This is the constructor for the Tv class.
     * @param category The category of the tv.
     * @param name The name of the tv.
     * @param price The price of the tv.
     * @param quantity The quantity of the tv.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public Tv(String category, String name, double price, int quantity) {
        super( category, name, price, quantity); // calling the constructor of the parent class.
    }
    /**
     * This is the default constructor for the Tv class.
     */
    public Tv() {
        super();
    }
}
