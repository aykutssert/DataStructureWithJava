//Base class for customer and operator
class Person {
    protected String name;
    protected String surname;
    protected String address;
    protected String phone;
    protected int ID;

    //person ctor
    public Person(String name,String surname,String address,String phone,int id) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.ID = id;

    }
}