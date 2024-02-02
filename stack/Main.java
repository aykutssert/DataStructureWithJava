package stack;

public class Main {
    public static void main(String[] args){
        Generic g1 = new Generic("Aykut");
        Generic g2 = new Generic("Sert");
        Generic g3 = new Generic("Tukya");
        Generic g4 = new Generic("Tres");
        Stack<Generic> myStack = new Stack<>(g1);
        myStack.Push(g2);
        myStack.Push(g3);
        myStack.Push(g4);
        myStack.print();
        
        
        
    }
    public static class Generic{
        public String name;
        public Generic(String name) {
            this.name = name;
        }
        @Override 
        public String toString() {
            return "Name:"+name;
        }
    }
    public static class Stack<T>{
        
        public Node<T> front;
        public Node<T> rear;
        public Node<T> iter;
        public Stack(T data) {
            Node<T> node = new Node<T>(data);
            front = node;
            iter = node;
            rear = node;
            front.next=null;
        }
        public void Push(T data){
            Node<T> node = new Node<T>(data);
            Node<T> iterPrev = iter;
            iter = node;
            iter.next = iterPrev;
            rear = node;
            

        }
        public T Pop(){
            T num = rear.data;
            rear = rear.next;
            return num;
        }
        public void print() {
            Node<T> temp = rear;
            while (temp != null) {
                System.out.print(temp.data+"-");
        
                temp = temp.next;
            }
            System.out.println();  // Add a new line after printing the list
        }
    }
    public static class Node <T>{
        public T data;
        public Node<T> next;
        public Node(T data) {
            this.data = data;
            next = null;
        }
        
    }
}
