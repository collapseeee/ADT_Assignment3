public class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    // Push, Pop and Peek:
    public void push(char data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
    }
    public char pop() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty. Cannot be popped.");
        }
        char data = top.getData();
        top = top.getNext();
        return data;
    }
    public char peek() {
        if (isEmpty()) {
            throw new RuntimeException("The stack is empty. Cannot be peeked.");
        }
        return top.getData();
    }
    public boolean isEmpty() {
        return this.top==null;
    }
}
