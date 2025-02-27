public class Node {
    // Attribute:
    private String data;
    private Node next;

    // Constructor:
    public Node(String data) {
        this.data = data;
        next = null;
    }

    // Method:
    public void setData(String data) {
        this.data = data;
    }
    public String getData(){
        return this.data;
    }
    public void setNext(Node node){
        this.next = node;
    }
    public Node getNext() {
        return this.next;
    }
}
