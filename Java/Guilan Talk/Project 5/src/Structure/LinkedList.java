package Structure;

class LinkedNode {
    Object data;
    LinkedNode next;

    public LinkedNode(Object data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    LinkedNode head;

    public void addNode(Object data) {
        if (head == null) {
            this.head = new LinkedNode(data);
        }
        else {
            LinkedNode temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = new LinkedNode(data);
        }
    }

    public LinkedNode getHead() {
        return head;
    }
}
