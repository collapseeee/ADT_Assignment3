public class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    // Push, Pop and Peek:
    public void push(String data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
    }
    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty. Cannot be popped.");
        }
        String data = top.getData();
        top = top.getNext();
        return data;
    }
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty. Cannot be peeked.");
        }
        return top.getData();
    }
    public boolean isEmpty() {
        return this.top==null;
    }
}
