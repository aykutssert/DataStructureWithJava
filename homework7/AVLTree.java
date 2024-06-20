
/**
 * AVL Tree implementation in Java
 */
public class AVLTree {
   
    /**
     * Node class for the AVL tree
     */
    private class Node {
        Stock stock; // Stock object
        Node left, right; // Left and right children of the node
        int height;// Height of the node

        /**
         * Constructor for the Node class
         * @param stock Stock object
         * 
         */
        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root; // Root of the AVL tree

    
    /**
     * Insertion public method
     * @param stock Stock object to insert
     * 
     */
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    /**
     * Insert a stock into the AVL tree
     * @param node The current node being considered
     * @param stock The stock to insert
     * @return Node The root node of the subtree after insertion
     */
    private Node insert(Node node, Stock stock) {
        // If the current node is null, create a new node with the given stock
        if (node == null) {
            return new Node(stock);
        }
        
        // If the symbol of the given stock is less than the symbol of the current node's stock, recursively insert into the left subtree
        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0) {
            node.left = insert(node.left, stock);
        } 
        // If the symbol of the given stock is greater than the symbol of the current node's stock, recursively insert into the right subtree
        else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0) {
            node.right = insert(node.right, stock);
        } 
        // If the symbol of the given stock is equal to the symbol of the current node's stock, duplicate symbols are not allowed
        else {
            return node;
        }

        // Update the height of the current node
        node.height = 1 + Math.max(height(node.left), height(node.right));
        
        // Balance the tree after insertion
        return balance(node);
    }


    /**
     * Delete public method
     * @param symbol Symbol of the stock to delete
     */
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    /**
     * Delete private method
     * @param node Node to delete
     * @param symbol Symbol of the stock to delete
     * @return Node Node to delete
     */
    private Node delete(Node node, String symbol) {
        if (node == null) {
            return node;
        }
        if (symbol.compareTo(node.stock.getSymbol()) < 0) { // Search in the left subtree if the symbol is smaller 
            node.left = delete(node.left, symbol);
        } else if (symbol.compareTo(node.stock.getSymbol()) > 0) { // Search in the right subtree if the symbol is greater
            node.right = delete(node.right, symbol);
        }
        else { // If the symbol is found
            if ((node.left == null) || (node.right == null)) { // If the node has only one child
                Node temp = null;
                if (node.left != null) {
                    temp = node.left; // If the left child is not null
                } else { 
                    temp = node.right; // If the right child is not null
                }

                if (temp == null) { // If the node has no children
                    node = null;
                } else {        // If the node has one child
                    node = temp;
                }
            } 
            else {   // If the node has two children 
                Node temp = minValueNode(node.right); // Find the inorder successor of the node
                node.stock = temp.stock; // Copy the inorder successor's data to this node
                node.right = delete(node.right, temp.stock.getSymbol()); // Delete the inorder successor
            }
        }
        if (node == null) { 
            return node;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right)); // Update the height of the current node
        return balance(node); // Balance the node
    }

    /**
     * Find the node with the minimum value
     * @param node Node to start the search
     * @return Node Node with the minimum value
     */
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Search for a stock in the AVL tree public method
     * @param symbol Symbol of the stock to search
     * @return Stock Stock object
     */
    public Stock search(String symbol) {
        Node result = search(root, symbol);
        return (result != null) ? result.stock : null;
    }

    /**
     * Search for a stock in the AVL tree private method
     * @param node  Node to start the search
     * @param symbol Symbol of the stock to search
     * @return Node Node with the stock
     */
    private Node search(Node node, String symbol) {
        if (node == null || node.stock.getSymbol().equals(symbol)) { // If the node is null or the symbol is found
            return node;
        }

        if (symbol.compareTo(node.stock.getSymbol()) < 0) { // Search in the left subtree if the symbol is smaller
            return search(node.left, symbol); // Recursive call for the left subtree
        } else { 
            return search(node.right, symbol); // Search in the right subtree if the symbol is greater
        }
    }

    /**
     * Get the height of a node
     * @param node Node to get the height
     * @return int Height of the node
     */
    private int height(Node node) {
        return node == null ? 0 : node.height; // If the node is null, return 0 otherwise return the height of the node
    }

    /**
     * Get the balance of a node
     * @param node Node to get the balance
     * @return int Balance of the node
     */
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right); // If the node is null, return 0 otherwise return the difference between the left and right subtree heights
    }

    
    /**
     * Right rotation
     * @param y Node to rotate
     * @return Node Node rotated
     */
    private Node rightRotate(Node y) { // Right rotation
        Node yChild = y.left;  // x is the left child of y
        Node xChild = yChild.right; // T2 is the right child of x

        // Perform rotation
        yChild.right = y; // Make y the right child of x
        y.left = xChild; // Set T2 as the left child of y

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        yChild.height = Math.max(height(yChild.left), height(yChild.right)) + 1;

        return yChild; // Return the new root
    }
    /**
     * Left rotation
     * @param x Node to rotate
     * @return Node Node rotated
     */
    private Node leftRotate(Node x) {
        Node xChild = x.right;  // y is the right child of x
        Node yChild = xChild.left; // T2 is the left child of y

        // Perform rotation
        xChild.left = x; // Make x the left child of y
        x.right = yChild; // Set T2 as the right child of x

         // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        xChild.height = Math.max(height(xChild.left), height(xChild.right)) + 1;

        return xChild; // Return the new root
    }
    
    /**
     * Balance the AVL tree
     * @param node Node to balance
     * @return Node Node balanced
     */
    private Node balance(Node node) {
        // Calculate the balance factor of the node
        int balance = getBalance(node);

        // If the balance factor is greater than 1 and the left subtree's balance is non-negative, perform a right rotation
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // If the balance factor is greater than 1 and the left subtree's balance is negative, perform a left rotation on the left child and then a right rotation on the node
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // If the balance factor is less than -1 and the right subtree's balance is non-positive, perform a left rotation
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // If the balance factor is less than -1 and the right subtree's balance is positive, perform a right rotation on the right child and then a left rotation on the node
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Return the balanced node
    }

   

    /**
     * In-order traversal of the AVL tree public method
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * In-order traversal of the AVL tree private method
     * @param node Node to start the traversal
     */
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.stock);
            inOrderTraversal(node.right);
        }
    }

    /**
     * Pre-order traversal of the AVL tree public method
     */
    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    /**
     * Pre-order traversal of the AVL tree private method
     * @param node Node to start the traversal
     */
    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.stock);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    /**
     * Post-order traversal of the AVL tree public method
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }
    
    
    
    /**
     * Post-order traversal of the AVL tree private method
     * @param node Node to start the traversal
     */
    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.stock);
        }
    }

}

