package queue;

public class Main {
    public static void main(String[] args){
        Queue myQueue = new Queue(2);
        myQueue.Enqueue(3);
        myQueue.Enqueue(4);
        myQueue.Enqueue(5);
        myQueue.Enqueue(6);
        myQueue.Enqueue(7);
        myQueue.Enqueue(8);
        myQueue.Enqueue(9);
        System.out.println("Pop:"+myQueue.Dequeue());
        System.out.println("Front:"+myQueue.front.data);
        System.out.println("Rear:"+myQueue.rear.data);
        myQueue.print();
        
    }
    public static class Node {
        public int data;
        public Node next;
        public Node(int data) {
            this.data = data;
            next = null;
        }
        
    }
    public static class Queue {
        public Node front;
        public Node rear;
        public Node iter;
        public Queue(int data) {
            Node node = new Node(data);
            front = node;
            iter = node;
            rear = node;
            front.next=null;
            rear.next=null;
        }
        public void Enqueue(int data){
            Node node = new Node(data);
            iter.next = node;
            iter = iter.next;
            rear = node;
            

        }
        public int Dequeue(){
            int num = front.data;
            front = front.next;
            return num;
        }
        public void print() {
            Node temp = front;
            while (temp != null) {
                System.out.print(temp.data+"-");
        
                temp = temp.next;
            }
            System.out.println();  // Add a new line after printing the list
        }
    }
}
