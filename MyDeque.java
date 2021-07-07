public class MyDeque {
    private static class Node{
        int data;
        Node previous;
        Node next;

        public static Node getNode(int data){
            Node newNode = new Node();
            newNode.data = data;
            newNode.previous = null;
            newNode.next = null;
            return newNode;
        }
    }

    Node head;
    Node tail;
    int size;

    public MyDeque(){
        head = null;
        tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void pushFront(int data){
        Node newNode = Node.getNode(data);
        if(head == null){
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    public void pushBack(int data){
        Node newNode = Node.getNode(data);
        if(tail == null){
            tail = newNode;
            head = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void deleteFront(){
        if(isEmpty()) return;
        Node tmp = head;
        head = head.next;
        if(head == null)
            tail = null;
        else
            head.previous = null;
        size--;
    }

    public void deleteBack(){
        if(isEmpty()) return;
        Node tmp = tail;
        tail = tail.previous;
        if(tail == null)
            head = null;
        else
            tail.next = null;
        size--;
    }


    public static void main(String[] args) {
        MyDeque md = new MyDeque();
        System.out.println(md.isEmpty());
        md.pushFront(10);
        System.out.println(md.head.data == 10);
        System.out.println(md.tail.data == 10);
        md.pushFront(15);
        md.pushFront(20);
        md.pushBack(11);
        md.pushBack(21);
        System.out.println(md.head.data == 20);
        System.out.println(md.tail.data == 21);
        md.deleteFront();
        System.out.println(md.tail.data == 21);
        md.deleteBack();
        System.out.println(md.tail.data == 11);
        System.out.println(md.head.data == 15);
        md.deleteBack();
        md.deleteBack();
        System.out.println(md.head.data == md.tail.data);
        md.deleteBack();
        System.out.println(md.isEmpty());
        md.deleteBack();
    }
}
