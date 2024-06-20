public class StockDataManager {
    private AVLTree avlTree;

    /**
     * Constructor for the StockDataManager class
     */
    public StockDataManager() {
        avlTree = new AVLTree();
    }

    /**
     * Add or update a stock in the AVL tree
     * @param symbol The symbol of the stock
     * @param price The price of the stock
     * @param volume The volume of the stock
     * @param marketCap The market capitalization of the stock
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        // Search for the stock with the given symbol in the AVL tree
        Stock existingStock = avlTree.search(symbol);
        // If the stock with the given symbol already exists in the AVL tree, update its details
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
            
        } 
        // If the stock with the given symbol does not exist in the AVL tree, insert a new stock with the given details
        else {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
    }


    /**
     * Remove a stock
     * @param symbol The symbol of the stock to be removed
     */
    public void removeStock(String symbol) {
        avlTree.delete(symbol); // Delete the stock with the given symbol from the AVL Tree
    }
    /**
     * Clear the AVL Tree
     */
    public void clear() {
        avlTree = new AVLTree(); // Resets the AVL Tree
    }

    /**
     * Search for a stock
     * @param symbol The symbol of the stock to search for
     * @return The stock object if found, null otherwise
     */
    public Stock searchStock(String symbol) {
        //System.out.println("Searching for stock: " + symbol);
        return avlTree.search(symbol);
    }

    /**
     * Update the stock with the given symbol in the AVL tree
     * @param symbol The symbol of the stock to be updated
     * @param newName The new name of the stock
     * @param newPrice The new price of the stock
     * @param newVolume The new volume of the stock
     * @param newMarketCap The new market capitalization of the stock
     */
    public void updateStock(String symbol, String newName, double newPrice, long newVolume, long newMarketCap) {
        // Search for the stock with the given symbol in the AVL tree
        Stock stock = avlTree.search(symbol);
        
        // If the stock with the given symbol exists in the AVL tree, delete it and insert a new stock with updated details
        if (stock != null) {
            avlTree.delete(symbol);
            Stock newStock = new Stock(newName, newPrice, newVolume, newMarketCap);
            avlTree.insert(newStock);
        }
    }


}
