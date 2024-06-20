import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is the Inventory class that manages the devices.
 */
public class Inventory {

    private List<ArrayList<Device>> deviceLists;  // List of devices in the inventory
     

    /**
     * This is the constructor for the Inventory class.
     * time complexity: O(1) : constant time complexity because it has a fixed number of operations.
     */
    public Inventory() {
        deviceLists = new LinkedList<>(); // Initialize the deviceLists list

    }

    /**
     * This method adds a device to the inventory.
     * @param device The device to be added.
     * worst case time complexity: O(n^m) : because it has nested loops. n is category count and m is device count.
     * 
     */
    public void addDevice(Device device) {
        boolean added = false; // If the device is added to the list, it is set to true
       
        for (List<Device> list : deviceLists) {
            //check same name device in the list if true then dont add
            if (list.get(0).getCategory().equals(device.getCategory())) {
                //check same name device in the list if true then dont add
                for (Device device1 : list) {
                    if (device1.getName().equals(device.getName())) {
                        System.out.println("This device is already in the inventory.");
                        return;
                    }
                }
                list.add(device);
                added = true;
                break;
            }
        }
        // If no list exists for the category, create a new list and add the device
        if (!added) {
            ArrayList<Device> newList = new ArrayList<>();
            newList.add(device);
            deviceLists.add(newList);
            
        }
    }

    /**
     * This method removes a device from the inventory.
     * @param name The name of the device to be removed.
     * worst case time complexity: O(n^m) : because it has nested loops. n is category count and m is device count.
     */
    public void removeDevice(String name) {
        boolean flag = false; // If the device is removed from the list, it is set to true
        // remove device from the inventory
        for (List<Device> list : deviceLists) {
            Device deviceToRemove = null;
            for (Device device : list) {
                // Check if the device name matches with the name to be removed
                if (device.getName().equals(name)) {
                    flag = true;
                    deviceToRemove = device;
                    break;
                }
            }
            if (deviceToRemove != null) {
                list.remove(deviceToRemove);
                System.out.println("Device removed successfully.");
                // Check if the list is empty after removing the device
                if (list.isEmpty()) {
                    deviceLists.remove(list); // Remove the list from deviceLists if it's empty
                }
                return;
            }
        }
        if (!flag) {
            System.out.println("Device not found.");
        }
    }
    

    /**
     * This method updates the details of a device.
     * @param name The name of the device to be updated.
     * @param newPrice The new price of the device.
     * @param newQuantity The new quantity of the device.
     * worst case time complexity: O(n^m) : because it has nested loops. n is category count and m is device count.
     */
    public void updateDevice(String name,double newPrice, int newQuantity) {
        // Cihaz detaylarını güncelle
        for (List<Device> list : deviceLists) {
            for (Device device : list) {

                // Check if the device name matches with the name to be updated
                if (device.getName().equals(name)) {
                    if(newPrice == 0){
                        newPrice = device.getPrice();
                    }
                    if(newQuantity == 0){
                        newQuantity = device.getQuantity();
                    }
                    device.setPrice(newPrice); // Update the price
                    device.setQuantity(newQuantity); // Update the quantity
                    break;
                }
            }
        }
        System.out.println("Device not found.");
    }

    /**
     * This method displays all devices in the inventory.
     * worst case time complexity: O(n^m) : because it has nested loops. n is category count and m is device count.
     */
    public void displayDevices() {
        int i=0; // set i to 0
        
        // Display all devices in the inventory
        // if there is no device in the inventory, print a message
        if(deviceLists.isEmpty()){
            System.out.println("There is no device in the inventory.");
            return;
        }
        for (List<Device> list : deviceLists) {
            
            for (Device device : list) {
                i++;
                System.out.println(i +".Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "$, Quantity: " + device.getQuantity());
            }
        }
        
    }

    /**
     * This method finds the cheapest device in the inventory.
     * @return The cheapest device.
     * worst case time complexity: O(n^m) : because it has nested loops. n is category count and m is device count.
     */
    
    public Device findCheapestDevice() {
        Device cheapestDevice = null; //set the cheapest device to null
        double cheapestPrice = Double.MAX_VALUE; // set the cheapest price to the maximum value of double
        if(deviceLists.isEmpty()){ // if there is no device in the inventory, print a message
            System.out.println("There is no device in the inventory.");
            return null;
        }
    
        for (List<Device> list : deviceLists) { 
            
            for (Device device : list) { 
                if (device.getPrice() < cheapestPrice) {  // if the price of the device is less than the cheapest price, set the cheapest device to the device and the cheapest price to the price of the device
                    cheapestDevice = device;
                    cheapestPrice = device.getPrice();
                }
            }
        }
        
        return cheapestDevice; // return the cheapest device
    }

    /**
     * This method sorts the devices in the inventory by price.
     * O(n logn) : because it uses the sort method which has O(n logn) time complexity.
     * Merge all devices: This merges the elements of all sublists in the deviceLists list containing all devices into a single list. This is based on the sum of the number of devices in all sublists in the deviceLists list. Hence, the time complexity of merging all devices is O(n), where n represents the number of all devices.
     * Sort all devices by price: This process sorts all devices by price. An efficient sorting algorithm is used for this operation, usually quicksort or TimSort, using Java's sort method. The time complexity of the sorting algorithm will usually be O(n log n), where n represents the number of all devices.
     * Printing sorted devices to the screen: This process prints the sorted devices to the screen. Depending on the number of all devices, this process takes O(n) time.
     * As a result, the total time complexity of the new function will depend in the worst case on the sum of the time complexity of all operations. In this case, the total time complexity will generally be O(n log n) because the operations of merging and sorting devices have the highest time complexity.
     */
    public void sortDevicesByPrice() {
        // allDevices list to store all devices
        List<Device> allDevices = new ArrayList<>();
    
        // Merge all devices
        for (List<Device> list : deviceLists) {
            allDevices.addAll(list);
        }
    
        // If there is no device in the inventory, print a message
        if (allDevices.isEmpty()) {
            System.out.println("There is no device in the inventory.");
            return;
        }
    
        // Sort all devices by price
        allDevices.sort((d1, d2) -> Double.compare(d1.getPrice(), d2.getPrice()));
    
        System.out.println("Devices sorted by price:");
        int i=0;
        // print all devices sorted by price
        for (Device device : allDevices) {
            i++;
            //1.Category: Tv, Name: Samsung 1, Price: 200.0$, Quantity: 5
            System.out.println(i+".Category: " + device.getCategory() + ", Name: " + device.getName() + ", Price: " + device.getPrice() + "$, Quantity: " + device.getQuantity());
        }
    }
    

    /**
     * This method calculates the total value of the inventory.
     * @return The total value of the inventory.
     * worst case  time complexity: O(n^m) : because it has nested loops. n is category count and m is device count.
     */
    public double calculateTotalValue() {
        // calculate the total value of the inventory
        double totalValue = 0; // set the total value to 0
        for (List<Device> list : deviceLists) {
            for (Device device : list) {
                // calculate the total value by multiplying the price of the device by the quantity of the device and adding it to the total value
                totalValue += device.getPrice() * device.getQuantity();


            }
        }
        return totalValue;
    }

    /**
     * This method restocks a device in the inventory.
     * @param name The name of the device to be restocked.
     * @param quantity The quantity to be added or removed.
     * @param addOrRemove The action to be taken (add or remove).
     * worst case  time complexity: O(n^m) : because it has nested loops. n is category count and m is device count.
     */
    public void restockDevice(String name, int quantity, String addOrRemove) {
        // restock the device in the inventory by adding or removing the quantity
        boolean flag = false; // If the device is found in the list, it is set to true
        
        for (List<Device> list : deviceLists) {
            for (Device device : list) {
                if (device.getName().equals(name)) { // Check if the device name matches with the name to be restocked
                    flag = true;
    
                    if(addOrRemove.equals("Remove") && device.getQuantity() < -1*quantity){ // If the action is remove and the quantity is greater than the quantity of the device, print a message
                        System.out.println("The quantity to be removed is greater than the quantity of the device.");
                        System.out.println("Device quantity: " + device.getQuantity() + ", Quantity to be removed: " + quantity);
                        return;
                    }
                    device.setQuantity(device.getQuantity() + quantity); // Add or remove the quantity
                    // Print a message based on the action taken
                    if(addOrRemove.equals("Add")){
                        System.out.println(device.getName() + " restocked. New quantity: " + device.getQuantity());
                    }else{
                        System.out.println(device.getName() + " stock reduced. New quantity: " + device.getQuantity());
                        if(device.getQuantity() == 0){
                            list.remove(device);
                            if (list.isEmpty()) {
                                deviceLists.remove(list);
                            }
                        }
                    }
                    break;
                }
            }
        }
        if (!flag) 
            System.out.println("Device not found.");
    }

    /**
     * This method exports the inventory report.
     * worst case  time complexity: O(n^m) : because it has nested loops. n is category count and m is device count.
     */
    public void exportInventoryReport() {
        // Dosya adını ve yolu belirtin
        String fileName = "inventory_report.txt";
    
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Eğer envanter boşsa, bir mesaj yazdırın ve fonksiyondan çıkın
            if (deviceLists.isEmpty()) {
                writer.println("There is no device in the inventory.");
                return;
            }
    
            // Raporu dosyaya yazdırın
            int i = 0; // i'yi 0 olarak ayarlayın
            int totalDevices = 0; // toplam cihazları 0 olarak ayarlayın
            double totalValue = 0; // toplam değeri 0 olarak ayarlayın
            writer.println("--------------------------------------");
            writer.println("| No. | Category | Name | Price | Quantity |");
            writer.println("--------------------------------------");
            for (List<Device> list : deviceLists) {
                for (Device device : list) {
                    i++; // i'yi 1 artır
                    writer.println("| " + i + " | " + device.getCategory() + " | " + device.getName() + " | $" + device.getPrice() + " | " + device.getQuantity() + " |");
                    totalDevices += device.getQuantity();
                    totalValue += device.getPrice() * device.getQuantity();
                }
            }
            writer.println("--------------------------------------");
            writer.println("Summary:");
            writer.println("-Total devices: " + totalDevices);
            writer.println("-Total value: $" + totalValue);
            writer.println("\nEnd of the report.");
            System.out.println("Report exported to " + fileName);
        } catch (IOException e) {
            System.err.println("An error occurred while exporting the report: " + e.getMessage());
        }
    }

}
