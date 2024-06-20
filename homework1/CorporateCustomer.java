class CorporateCustomer extends Customer {
    private String company_name; //extra info

    // corporate customer ctor
    public CorporateCustomer(String name,String surname,String address,String phone,int id,int op_id,String company_name) {
        super(name,surname,phone,address,id,op_id);
        this.company_name = company_name;
    }

    // it is override function because there is extra info which has to be printed.
    @Override
    public void print_customer(){
        System.out.println("Name & Surname: " + name+ " "+surname);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("ID: " + ID);
        System.out.println("Operator ID: " + operator_ID);
        System.out.println("Company name: " + company_name);
    }
}