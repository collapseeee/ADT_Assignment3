public class Node {
    // Attribute:
    private char data;
    private Node next;

    // Constructor:
    public Node(char data) {
        this.data = data;
        next = null;
    }

    // Method:
    public void setData(char data) {
        this.data = data;
    }
    public char getData(){
        return this.data;
    }
    public void setNext(Node node){
        this.next = node;
    }
    public Node getNext() {
        return this.next;
    }
}
