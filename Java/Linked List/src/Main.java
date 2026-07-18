import java.util.Objects;
import java.util.Scanner;

class LinkNode {
    int data;
    LinkNode next;
    LinkNode (int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String listStr = scanner.nextLine();
        int k = scanner.nextInt();

        LinkNode list = null;
        String str = listStr + ',';
        for (int index = 0; index < str.length();) {
            int data = Integer.parseInt(str.substring(index, str.indexOf(',', index)));
            list = addNode(list, data); //add a new node dor every int in the str
            index = str.indexOf(',', index) + 1;
        }

        printLinkedList(Objects.requireNonNull(reverse(list, k)));
    }
    public static void printLinkedList(LinkNode head) {
        while (head.next != null) {
            System.out.print(head.data + ",");
            head = head.next;
        }
        System.out.println(head.data);
    }
    public static LinkNode reverse(LinkNode list, int k) {
        if (list == null || list.next == null)
            return list;
        LinkNode temp = new LinkNode(-1); //creating a temp list with a new head
        temp.next = list;

        LinkNode previous = temp; //initializing pointers
        LinkNode current = temp;
        LinkNode next = temp;

        int count = 0;
        while (current != null) { //counting the list length
            count++;
            current = current.next;
        }

        if (k > count - 1 || k < 0)
            return list;

        while (next != null && count - k > 0) {
            current = previous.next; //current position after every reversed k nodes
            next = current.next;

            for (int i = 1; i < k; i++) {
                current.next = next.next;
                next.next = previous.next;
                previous.next = next;
                next = current.next;
            }
            previous = current; //setting previous to the new current
            count -= k;
        }
        return temp.next;
    }
    public static LinkNode addNode(LinkNode head, int data) {
        if (head == null)
            head = new LinkNode(data); //if the head is null create a new head
        else { //if not insert the new node to last
            LinkNode node = head;
            while (node.next != null)
                node = node.next;
            node.next = new LinkNode(data);
        }
        return head;
    }
}