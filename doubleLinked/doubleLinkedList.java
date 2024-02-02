package doubleLinked;

public class doubleLinkedList {

  public static void main(String[] args) {
    MyClass myclass = new MyClass(6, "A");
    MyClass myclass1 = new MyClass(4, "B");
    MyClass myclass2 = new MyClass(1, "C");
    MyClass myclass3 = new MyClass(7, "D");
    MyClass myclass4 = new MyClass(13, "E");
    MyClass myclass5 = new MyClass(7, "D");
    MyClass myclass6 = new MyClass(13, "E");
    MyClass myclass7 = new MyClass(14, "E");
    MyLinkedList list1 = new MyLinkedList(myclass);
    list1.addToEnd(myclass4);
    list1.addToEnd(myclass2);
    list1.addToEnd(myclass3);
    list1.addToStart(myclass6);
    list1.addToEnd(myclass2);
    list1.addToEnd(myclass4);
    list1.addToStart(myclass5);
    list1.addToEnd(myclass1);
    list1.addToEnd(myclass2);
    list1.addToEnd(myclass3);
    list1.addToEnd(myclass1);
    
    
    
    list1.print();
    list1.InsertionSort(); // Sıralama işlemi

    list1.print();
    list1.find(myclass2);
    list1.find(myclass7);
  }

  public interface MyData {
    int getAge();
    String getName();
}

public static class MyClass implements MyData {

    public int age;
    public String name;

    public MyClass(int _age, String name) {
        this.age = _age;
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getName() {
        return name;
    }
}

  public static class Node {

    public MyClass data;
    public Node next;
    public Node prev;

    public Node(MyClass _data) {
       
      data = _data;
    }

    public Node getNext() {
      return next;
    }
    
  }

  public static class MyLinkedList {

    public Node start;

    public MyLinkedList(MyClass _data) {
        Node node = new Node(_data);
      start = node;
      node.data = _data;
      node.next = null;
      node.prev = null;
    }

    public void addToStart(MyClass _data) {
      Node node = new Node(_data);
      node.next = start;
      node.prev = null;
      start.prev = node;
      start = node;
      
    }

    public void addToEnd(MyClass _data) {
      Node iter = start;
      
      while (iter.next != null) {
        iter = iter.next;
      }
      Node node = new Node(_data);
      iter.next = node;
      node.prev = iter;
      node.next=null;
    }

    public void find(MyClass _data) {
      Node iter;
      iter = start;
      while (iter != null) {
        if (iter.data == _data) {
          System.out.println("Bulundu");
          return;
        }
        iter = iter.next;
      }
      System.out.println("Bulunamadı");
    }
    public void SelectionSort(){

      

        if(start == null || start.next == null) return; //liste boş ya da tek eleman varsa

        //iteration temp
        Node current = start;
        while(current.next!=null){
            //finding minimum Node
            Node minNode = findMin(current);

            //swapping
            MyClass temp = current.data;
            current.data = minNode.data;
            minNode.data=temp;

            //next iteration
            current = current.next;
        }

       
    }
    public Node findMin(Node current){
        Node iter = current;
        Node minNode = iter;
        while(iter.next!=null){
            iter = iter.next;
            if(minNode.data.age > iter.data.age){
                minNode = iter;
            }
            
        }   
        return minNode;
    }
    
    public void InsertionSort(){
        if(start == null || start.next == null) return; //liste boş ya da tek eleman varsa

        //iteration temp
        Node current = start.next;
        

        
        while(current!=null){
            
            Node tempCurrent;
            Node tempPrev;
            tempCurrent = current;
            tempPrev = current.prev;
           
            while(tempPrev!=null && tempCurrent.data.age < tempPrev.data.age ){
                
                
                MyClass temp = tempCurrent.data;
                tempCurrent.data = tempPrev.data;
                tempPrev.data=temp;
                 
                tempCurrent = tempPrev;
                tempPrev = tempPrev.prev;
                           
            }
            current = current.next;
            
        }
    }
    public void print() {
        Node iter = start;
        while (iter != null) {
            System.out.print("name:" + iter.data.getName() + " age:" + iter.data.getAge() + " - ");
    
            iter = iter.next;
        }
        System.out.println();  // Add a new line after printing the list
    }
    
  }
}
