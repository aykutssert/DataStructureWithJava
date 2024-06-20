
class RetailCustomer extends Customer {
    //there is no extra info about retail.
    //retail customer ctor
    public RetailCustomer(String name,String surname,String address,String phone,int id,int op_id) {
        super(name,surname,phone,address,id,op_id);
    }
}