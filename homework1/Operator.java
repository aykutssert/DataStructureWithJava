
class Operator extends Person {
    private int wage;
    private Customer[] customers;

    private int customerCount = 0; //it helps while printing certain number.
    public Operator(String name,String surname,String address,String phone,int id,int wage) {
        super(name,surname,address,phone,id);
        this.wage = wage;
    }

    //print the operator
    public void print_operator() {
        System.out.println("*** Operator Screen ***");
        System.out.println("Name & Surname: " + name + " "+surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Wage: " + wage);
    }

    //add customer to array 
    public void addCustomer(Customer customer) {
        customers[customerCount++] = customer;
    }

    //printing the all customers
    public void print_customers() {

        
            if(customerCount==0){
                System.out.println("--------------------------------------");
                System.out.println("This operator doesn't have any customer.");
            }
            for (int i=0;i<customerCount;i++) {
                System.out.println("--------------------------------------");
                System.out.print("Customer #"+(i+1));
                if (customers[i] instanceof RetailCustomer) {
                    
                    System.out.println(" (a retail customer)");
                } else if (customers[i] instanceof CorporateCustomer) {
                    
                    System.out.println(" (a corporate customer)");
                }
                customers[i].print_customer();
                customers[i].print_orders();
            }
        
    }

    //define all customers and calling addCustomer function 
    public void define_customers(Customer[] customers) {
        this.customers = new Customer[100];
        for(int i=0;i<customers.length;i++){
            
            if ( customers[i] != null && this.ID == customers[i].operator_ID) { //id check
                
                this.addCustomer(customers[i]);
            }
        }
        
    }
}