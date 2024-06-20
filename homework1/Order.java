class Order {
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    protected int customer_ID;
    private String statusOut;
    
    //order ctor
    public Order(String productName,int count_,int total_price_,int status_,int customer_ID_) {
        this.count = count_;
        this.customer_ID = customer_ID_;
        this.status = status_;
        this.product_name = productName;
        this.total_price = total_price_;

        // Assigned the status string
        if(status==1){
            statusOut="Processing";
        }
        else if(status==0){
            statusOut="Initialized";
        }
        else if(status==2){
            statusOut="Completed";
        }
        else if(status==3){
            statusOut="Cancelled";
        }
    }
    
    //printing the order info's
    public void print_order(){
        
        String s =String.format("Product name: %s - Count: %d - TotalPrice: %d - Status: %s",product_name,count,total_price,statusOut);
        System.out.println(s);
    }
    
    
}