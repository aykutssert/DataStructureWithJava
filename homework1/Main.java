import java.io.File;
import java.util.Scanner;

/*
 * @author AYKUT-SERT-200104004104
 * @date 3rd March 2024
 */


public class Main {
    public static Operator[] operators = new Operator[100]; //array to hold all operators
    public static Customer[] customers = new Customer[100]; //array to hold all customers
    public static Order[] orders = new Order[100]; //array to hold all orders
    public static int operatorCount = 0; //variable to count all valid operators
    public static int customerCount = 0; //variable to count all valid customers
    public static int orderCount = 0; //variable to count all valid orders

    public static void main(String[] args) {
        readDataFromFile("content.txt");

        Scanner scanner = new Scanner(System.in);

            int found = 0; //if a customer or operator are found = true

            try{
                System.out.println("Please enter your ID...");
                int ID = scanner.nextInt();

                // Search for operator with given ID
                for (int i = 0; i < operatorCount; i++) {
                    if (operators[i].ID == ID) {
                        found = 1;
                        operators[i].print_operator();
                        operators[i].print_customers();
                        break;
                    }
                }

                // If operator not found, search for customer with given ID
                if (found==0) {
                    for (int i = 0; i < customerCount; i++) {
                        if (customers[i].ID == ID) {
                            found = 1;
                            customers[i].print_customer();
                            customers[i].print_orders();
                            break;
                        }
                    }
                }

                // If neither operator nor customer found, inform user
                if (found==0) {
                    System.out.println("No operator or customer found with the given ID "+ID);
                }
            }
            catch(Exception e){
                System.out.println("Hatali input");
            }
        
        
        scanner.close();
    }

    /**
      * Reads and processes data from the given file.
      * @param fileName Name of the file to read
      */

    public static void readDataFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) { //if there is another line in File , go continue.

                String line = scanner.nextLine(); // that line is assigned to line variable.

                String[] parts = line.split(";",-1); // splitting according to ";"
    
                String type = parts[0]; // parts[0] is type of content.

                try {
                   
                    OperatorType operatorType = OperatorType.fromString(type); // with enum we find specific type of content by comparing the string.
                    // 4 enum created and with fromString function , one is returned by string check.
                    // Enum operatorType switch-case
                    switch (operatorType) {
                        case OPERATOR:
                            if (operatorCount >= 100) continue; // Ignore if array is full

                            if(parts.length !=7 ) break; // operator line has to be 7 parts.
                            if(ErrorHandling.atLeastOneLength(parts.length, parts)==0)break; // all parts has to be at least one length
                            if(!ErrorHandling.IsAllIntegersPositiveAndCheckTheConvertible(parts,operatorType))break; //some parts has to be positive and convertible to integer example: id , wage
                            if(ErrorHandling.isUniqueId(Integer.parseInt(parts[5]))==0)break; //operator id part has to be unique.
                            Operator operator = createOperator(parts); // if everything is okay then create operator.
                            operators[operatorCount++] = operator; // global array and global count variable are updated and edited.
                            break;
                        case RETAILCUSTOMER:
                            if (customerCount >= 100) continue; // Ignore if array is full
                            if(parts.length !=7 ) break;  //retail customer line has to be 7 parts
                            if(ErrorHandling.atLeastOneLength(parts.length, parts)==0)break;  // all parts has to be at least one length
                            if(!ErrorHandling.IsAllIntegersPositiveAndCheckTheConvertible(parts,operatorType))break; //some parts has to be positive and convertible to integer example: id,operator id,
                            if(ErrorHandling.isUniqueId(Integer.parseInt(parts[5]))==0)break;  // operator id and customer id have to be unique.
                            //if(Integer.parseInt(parts[5]) == Integer.parseInt(parts[6])) break; // if operator id and customer id are same break because unique is important
                            RetailCustomer retailCustomer = createRetailCustomer(parts); // if everything is okay then create retail customer.
                            customers[customerCount++] = retailCustomer;  // global array and global count variable are updated and edited.
                            break;
                        case CORPORATECUSTOMER:
                            if (customerCount >= 100) continue; // Ignore if array is full
                            if(parts.length !=8 ) break; //corporate customer part has to be 8 parts (extra:company name according to retail c.)
                            if(ErrorHandling.atLeastOneLength(parts.length, parts)==0)break; // all parts has to be at least one length
                            if(!ErrorHandling.IsAllIntegersPositiveAndCheckTheConvertible(parts,operatorType))break; //some parts has to be positive and convertible to integer example: id,operator id,
                            if(ErrorHandling.isUniqueId(Integer.parseInt(parts[5]))==0)break; // operator id and customer id have to be unique.
                            //if(Integer.parseInt(parts[5]) == Integer.parseInt(parts[6])) break; // if operator id and customer id are same break because unique is important
                            CorporateCustomer corporateCustomer = createCorporateCustomer(parts); // if everything is okay then create corporate customer.
                            customers[customerCount++] = corporateCustomer; // global array and global count variable are updated and edited.
                            break;
                        case ORDER:
                            if (orderCount >= 100) continue; // Ignore if array is full
                            if(parts.length !=6 ) break; //order parts have to be 6 parts.
                            if(ErrorHandling.atLeastOneLength(parts.length, parts)==0)break;  // all parts has to be at least one length
                            if(!ErrorHandling.IsAllIntegersPositiveAndCheckTheConvertible(parts,operatorType))break; //some parts has to be positive and convertible to integer example: customer id,count,price
                            Order order = createOrder(parts); // if everything is okay then create corporate orders.
                            orders[orderCount++] = order; // global array and global count variable are updated and edited.
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    //for example if type is order_1 , ignore it.
                }

            }
            scanner.close();
            
            addCustomerToOperator(); //customers are added to operators with id check.

            addOrderToCustomer(); //orders are added to customers with id check.
            
        } catch (Exception e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    
    // Create Operator object from input data
    public static Operator createOperator(String[] parts) {
        Operator operator = new Operator(parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]),Integer.parseInt(parts[6]));
        return operator;
    }

    // Create RetailCustomer object from input data
    public static RetailCustomer createRetailCustomer(String[] parts) {
        RetailCustomer retailCustomer = new RetailCustomer(parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]),Integer.parseInt(parts[6]));
        return retailCustomer;
    }

    // Create CorporateCustomer object from input data
    public static CorporateCustomer createCorporateCustomer(String[] parts) {
        CorporateCustomer corporateCustomer = new CorporateCustomer(parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]),Integer.parseInt(parts[6]),parts[7]);
        return corporateCustomer;
    }

    // Create Order object from input data
    public static Order createOrder(String[] parts) {
        Order order = new Order(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
        return order;
    }

     // Add customer to operator's array based on operator ID
     public static void addCustomerToOperator() {
    
        for(int i=0;i<operatorCount;i++){
            operators[i].define_customers(customers); //specific operator is calling define-customer function and that operator will has his/her customers.
            
        }
        
    }
    public static void addOrderToCustomer(){
        for(int j=0;j<customerCount;j++){
            customers[j].define_orders(orders); //specific customer is calling define-order function and that customer will has his/her orders.
        }
    }

    public class ErrorHandling {
       

            public static int atLeastOneLength(int size,String[]parts){
                for(int i=0;i<size;i++){
                    if(parts[i].length()<1){
                        return 0;
                    }
                }
                return 1;
            }


            public static boolean IsAllIntegersPositiveAndCheckTheConvertible(String[]parts,OperatorType type){

                switch(type){
                    case ORDER:
                        try {
                            int value1 = Integer.parseInt(parts[2]);
                            int value2 = Integer.parseInt(parts[3]);
                            int value22 = Integer.parseInt(parts[4]);
                            int value3 = Integer.parseInt(parts[5]);
                            return (value1 > 0) && (value2 > 0) && (value3 > 0) && (value22 >= 0);
                        } catch (NumberFormatException e) { //checking convertible.
                            return false;
                        }
                    case RETAILCUSTOMER:
                        try {
                            int value1 = Integer.parseInt(parts[5]);
                            int value2 = Integer.parseInt(parts[6]);
                            return (value1 > 0) && (value2 > 0);
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    case CORPORATECUSTOMER:
                        try {
                            int value1 = Integer.parseInt(parts[5]);
                            int value2 = Integer.parseInt(parts[6]);
                            return (value1 > 0) && (value2 > 0);
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    case OPERATOR:
                        try {
                            int value1 = Integer.parseInt(parts[5]);
                            int value2 = Integer.parseInt(parts[6]);
                            return (value1 > 0) && (value2 > 0);
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    
                }
                    return true;
            }

            public static int isUniqueId(int ID){
                
                for(int i=0;i<operatorCount;i++){
                    if(ID == operators[i].ID){
                        return 0;
                    }
                }
                for(int i=0;i<customerCount;i++){
                    if(ID == customers[i].ID){
                        return 0;
                    }
                }
                return 1;
            }
    }

    /*
     * Represents the operator type.
     */
    public enum OperatorType {
        OPERATOR("operator"),
        RETAILCUSTOMER("retail_customer"),
        CORPORATECUSTOMER("corporate_customer"),
        ORDER("order");
    
        private final String type;
    
        OperatorType(String type_) { //Enum constructor
            this.type = type_;
        }
    
        public String getType() {
            return type;
        }
    
        public static OperatorType fromString(String text) {
            for (OperatorType operator : OperatorType.values()) {
                if (operator.type.equals(text)) {
                    return operator; 
                }
            }
            throw new IllegalArgumentException("Unknown operator: " + text);
        }
    }
    
}