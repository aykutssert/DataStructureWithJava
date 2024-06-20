
import java.util.Scanner;


public class Main{
    /**
     * This is the main method that runs the program.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Inventory inventory = new Inventory(); // create a new inventory object
        Scanner scanner = new Scanner(System.in); // create a new scanner object to read user input
        int option; // the option selected by the user

        do {
            // display the menu
            System.out.println("Welcome to the Electronics Inventory Management System!\n" +
                    "Please select an option:\n" +
                    "1. Add a new device\n" +
                    "2. Remove a device\n" +
                    "3. Update device details\n" +
                    "4. List all devices\n" +
                    "5. Find the cheapest device\n" +
                    "6. Sort devices by price\n" +
                    "7. Calculate total inventory value\n" +
                    "8. Restock a device\n" +
                    "9. Export inventory report\n" +
                    "0. Exit");
            option = scanner.nextInt(); // read the option selected by the user
            scanner.nextLine(); // consume the newline character
            switch (option) {
                case 1: // add a new device

                    try{
                    ErrorHandlingForInvalidInput errorHandlingForInvalidInput = new ErrorHandlingForInvalidInput(); // create a new error handling object

                    System.out.print("Enter the category of the device: "); 
                    String category = scanner.nextLine();
                    errorHandlingForInvalidInput.emptyControl(category); // check if the category is empty
                    // check if the category is valid
                    if(!category.equals("Watch") && !category.equals("SmartPhone") && !category.equals("Laptop") && !category.equals("Tv") && !category.equals("HeadPhones")){
                        System.out.println("Invalid input! Please enter Watch, SmartPhone, Laptop, Tv or HeadPhones");
                        break;
                    }

                    System.out.print("Enter the name of the device: ");
                    String name = scanner.nextLine();
                    errorHandlingForInvalidInput.emptyControl(name);


                    System.out.print("Enter the price of the device: ");
                    String price = scanner.nextLine();
                    errorHandlingForInvalidInput.emptyControl(price);
                    errorHandlingForInvalidInput.priceControl(price); // check if the price is valid
                    String priceWithoutDollar = price.substring(0, price.length() - 1); // remove the dollar sign
                    double priceNew = Double.parseDouble(priceWithoutDollar); // convert the price to double

                    System.out.print("Enter the quantity of the device: ");
                    String quantity = scanner.nextLine();
                    int quantityNew = Integer.parseInt(quantity); // convert the quantity to integer
                    
                    // add the device to the inventory
                    if(category.equals("Watch")){
                        inventory.addDevice(new Watch(category, name, priceNew, quantityNew));
                    }
                    else if(category.equals("SmartPhone")){
                        inventory.addDevice(new SmartPhone(category, name, priceNew, quantityNew));
                    }
                    else if(category.equals("Laptop")){
                        inventory.addDevice(new Laptop(category, name, priceNew, quantityNew));
                    }
                    else if(category.equals("Tv")){
                        inventory.addDevice(new Tv(category, name, priceNew, quantityNew));
                    } 
                    else if(category.equals("HeadPhones")){
                        inventory.addDevice(new HeadPhone(category, name, priceNew, quantityNew));
                    }
                }
                    catch (NumberFormatException e) {
                        // if we can't convert the price to double, it means the user entered an invalid price
                        System.out.println("Invalid input! Please enter a valid numeric number");
                    } catch (ErrorHandlingForInvalidInput e) {
                        // if the user enters an empty string, we catch the exception and print the error message
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                try{
                    ErrorHandlingForInvalidInput errorHandlingForInvalidInput = new ErrorHandlingForInvalidInput();
                    System.out.println("Enter the name of the device you want to remove: ");
                    String deviceName = scanner.nextLine();
                    errorHandlingForInvalidInput.emptyControl(deviceName); // check if the device name is empty
                    inventory.removeDevice(deviceName); // remove the device from the inventory
                } catch (ErrorHandlingForInvalidInput e) {
                    // if the user enters an empty string, we catch the exception and print the error message
                    System.out.println(e.getMessage());
                }
                    break;
                case 3:
                    // update device details
                try{

                    ErrorHandlingForInvalidInput errorHandlingForInvalidInput = new ErrorHandlingForInvalidInput(); // create a new error handling object
                    System.out.println("Enter the name of the device to update: ");
                    String deviceName = scanner.nextLine();
                    errorHandlingForInvalidInput.emptyControl(deviceName);

                    
                    System.out.println("Enter new price (leave blank to keep current price): ");
                    String newPrice = scanner.nextLine();
                    double newPriceDouble;
                    if(newPrice.isEmpty()){ // if the user leaves the price blank, we set the new price to 0
                        newPrice = "0";
                        newPriceDouble = Double.parseDouble(newPrice);
                    }
                    else{
                        errorHandlingForInvalidInput.priceControl(newPrice); // check if the price is valid
                        String priceWithoutDollar = newPrice.substring(0, newPrice.length() - 1); // remove the dollar sign
                        newPriceDouble = Double.parseDouble(priceWithoutDollar);
                    }
                   

                    System.out.println("Enter new quantity (leave blank to keep current quantity): ");
                    String newQuantity = scanner.nextLine();
                    if(newQuantity.isEmpty()){ // if the user leaves the quantity blank, we set the new quantity to 0
                        newQuantity = "0";
                    }
                    int newQuantityInt = Integer.parseInt(newQuantity); // convert the new quantity to integer
                    
                    inventory.updateDevice(deviceName,newPriceDouble, newQuantityInt); // update the device details
                } catch (NumberFormatException e) {
                    // if we can't convert the price to double, it means the user entered an invalid price
                    System.out.println("Invalid input! Please enter a valid numeric number");
                } catch (ErrorHandlingForInvalidInput e) {
                    // if the user enters an empty string, we catch the exception and print the error message
                    System.out.println(e.getMessage());
                }
                    break;
                case 4:
                    // display all devices
                    inventory.displayDevices();
                    break;
                case 5:
                    // find the cheapest device
                    Device cheapestDevice = inventory.findCheapestDevice();
                    if(cheapestDevice != null) // if there is a cheapest device
                    System.out.println("Cheapest device: " + cheapestDevice.getName() + ", Price: " + cheapestDevice.getPrice());
                    break;
                case 6:
                    // sort devices by price
                    inventory.sortDevicesByPrice();
                    break;
                case 7:
                    // calculate total inventory value
                    double totalValue = inventory.calculateTotalValue();
                    System.out.println("Total inventory value: $" + totalValue);
                    break;
                case 8:
                // Restock a device
                    try{
                        
                        ErrorHandlingForInvalidInput errorHandlingForInvalidInput = new ErrorHandlingForInvalidInput();
                        System.out.println("Enter the name of the device to restock: ");
                        String deviceName = scanner.nextLine();
                        errorHandlingForInvalidInput.emptyControl(deviceName);
                        System.out.println("Do you want to add or remove stock? (Add/Remove): ");
                        String addOrRemove = scanner.nextLine();
                        errorHandlingForInvalidInput.emptyControl(addOrRemove);

                        // check if the user entered a valid option
                        if(!addOrRemove.equals("Add") && !addOrRemove.equals("Remove")){
                            System.out.println("Invalid input! Please enter Add or Remove");
                            break;
                        }
                        System.out.println("Enter the quantity to "+addOrRemove+": ");
                        String quantity = scanner.nextLine();
                        int quantityInt = Integer.parseInt(quantity); // convert the quantity to integer
                        if(quantityInt < 0){
                            System.out.println("Invalid input! Please enter a positive number");
                            break;
                        }
                        // restock the device
                        if(addOrRemove.equals("Add")){
                            inventory.restockDevice(deviceName, quantityInt, addOrRemove); // add the quantity
                        }else{
                            inventory.restockDevice(deviceName, -quantityInt, addOrRemove); // remove the quantity
                        }
                        
                    }

                    catch (NumberFormatException e) {
                        // Eğer fiyatı double'a dönüştüremiyorsak, kullanıcı geçersiz bir fiyat girmiş demektir
                        System.out.println("Invalid input! Please enter a valid numeric number");
                    } catch (ErrorHandlingForInvalidInput e) {
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                case 9:
                    // export inventory report
                    inventory.exportInventoryReport();
                    break;
                case 0:
                    // exit the program
                    System.out.println("Exiting...");
                    break;
                default:
                    // invalid option
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0); // repeat until the user chooses to exit

        // close the scanner
        scanner.close();
    }


}
