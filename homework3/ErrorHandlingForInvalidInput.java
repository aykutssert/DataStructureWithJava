
/**
 * This is the ErrorHandlingForInvalidInput class that handles the exceptions for invalid input.
 */
public class ErrorHandlingForInvalidInput extends Exception {

    /**
     * This is the constructor for the ErrorHandlingForInvalidInput class.
     * @param message The message to be displayed.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public ErrorHandlingForInvalidInput(String message) {
        super(message); // calling the constructor of the parent class
    }

    /**
     * This is the constructor for the ErrorHandlingForInvalidInput class.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public ErrorHandlingForInvalidInput() {
    }

    /**
     * This method checks if the input is empty.
     * @param str The input to be checked.
     * @throws ErrorHandlingForInvalidInput If the input is empty.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public void emptyControl(String str) throws ErrorHandlingForInvalidInput {
        if (str == null || str.isEmpty()) {
            throw new ErrorHandlingForInvalidInput("Input cannot be empty.");
        }
    }

    /**
     * This method checks if the price ends with $.
     * @param price The price to be checked.
     * @throws ErrorHandlingForInvalidInput If the price does not end with $.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public void priceControl(String price) throws ErrorHandlingForInvalidInput {
        if(price.isEmpty()){
            return;
        }
        //last character must be $
        if (!price.endsWith("$")) {
            throw new ErrorHandlingForInvalidInput("Price must end with $");
        }
    }
}
