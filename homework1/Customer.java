class Customer extends Person {
    private Order[] orders;
    private int orderCount = 0; // it helps while printing certain order number.
    protected int operator_ID;

    //customer ctor
    public Customer(String name,String surname,String address,String phone,int id,int op_id) {
        super(name,surname,phone,address,id);
        this.operator_ID = op_id;
    }
    //printing customer's info
    public void print_customer() {
        System.out.println("*** Customer Screen ***");
        System.out.println("Name & Surname: " + name+ " "+surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Operator ID: " + operator_ID);
    }

    //add order to array function
    public void addOrder(Order order){
        orders[orderCount++]=order;
        
    }

    //printing all orders of customer
    public void print_orders() {
        if (orders != null) {
            
            for (int i=0;i<orderCount;i++) {
                
                System.out.print("Order #"+(i+1)+" => ");
                orders[i].print_order();
            }
        }
    }

    //define all orders and calling addOrder function
    public void define_orders(Order[] orders) {
        this.orders = new Order[100];
        for(int i=0;i<orders.length;i++){
            if(orders[i] !=null && this.ID == orders[i].customer_ID){
                this.addOrder(orders[i]);
            }
        }
    }
}
