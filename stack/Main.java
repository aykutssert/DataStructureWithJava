package stack;

public class Main {
    public static void main(String[] args){
        Stack myStack = new Stack(2);
        myStack.Push(3);
        myStack.Push(4);
        myStack.Push(5);
        myStack.Push(6);
        myStack.Push(7);
        myStack.Push(8);
        myStack.Push(9);
    
        myStack.print();
        System.out.println("Pop data:"+myStack.Pop());
        
        
    }
    public static class Stack{
        
        public Node front;
        public Node rear;
        public Node iter;
        public Stack(int data) {
            Node node = new Node(data);
            front = node;
            iter = node;
            rear = node;
            front.next=null;
        }
        public void Push(int data){
            Node node = new Node(data);
            Node iterPrev = iter;
            iter = node;
            iter.next = iterPrev;
            rear = node;
            

        }
        public int Pop(){
            int num = rear.data;
            rear = rear.next;
            return num;
        }
        public void print() {
            Node temp = rear;
            while (temp != null) {
                System.out.print(temp.data+"-");
        
                temp = temp.next;
            }
            System.out.println();  // Add a new line after printing the list
        }
    }
    public static class Node {
        public int data;
        public Node next;
        public Node(int data) {
            this.data = data;
            next = null;
        }
        
    }
}
