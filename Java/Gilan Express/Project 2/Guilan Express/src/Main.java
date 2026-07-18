import java.util.Random;
import java.util.Scanner;
import static java.lang.System.exit;

class Shipment {
    String name;
    String sender;
    String receiver;
    int dd; //destination distance
    int tc; //tracking code
    String[] status;

}
class LinkNode {
    Shipment data;
    LinkNode next;
    LinkNode (Shipment data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static LinkNode list; //list of all shipments
    static Shipment[] array; //list of received shipments
    static Shipment[] heap; //list of shipment in form of min heap
    static int last; //last index of heap
    static int statusTop; //status stack top index
    static int top; //array stack top index

    public static void main(String[] args) {
        //initializing
        list = null;
        array = new Shipment[100];
        heap = new Shipment[100];
        top = -1; //array is empty so top is -1
        last = -1; //heap is empty so last is -1

        menu();
    }
    public static void menu() {
        System.out.println("""
                1. Receive the shipment
                2. Send the shipment
                3. Register new status
                4. Track the shipment status
                5. Shipments archive
                6. Exit""");

        int menuInput = scanner.nextInt(); //menu option
        switch (menuInput) {
            case 1 -> receive();
            case 2 -> send();
            case 3 -> newStatus();
            case 4 -> trackShipment();
            case 5 -> archive();
            case 6 -> exit(0);
            default -> {
                System.out.println("There is no such option. Please try again!");
                menu();
            }
        }
    } //displays the menu
    public static void receive() {
        Shipment shipment = new Shipment(); //creating a new shipment
        shipment.status = new String[2]; //initializing new status array
        statusTop = -1; //status is empty so top is -1


        System.out.println("Shipments name: ");
        shipment.name = scanner.next();
        System.out.println("Senders name: ");
        shipment.sender = scanner.next();
        System.out.println("Receivers name: ");
        shipment.receiver = scanner.next();
        System.out.println("Destination distance: ");
        shipment.dd = scanner.nextInt();

        Random random = new Random();
        shipment.tc = random.nextInt(999999 - 100000 + 1) + 100000; //random 6 digits number for shipments tracking code
        statusTop++;
        shipment.status[statusTop] = "Received";

        push(shipment); //adding shipment to the list
        insert(shipment); //adding shipment to min heap
        list = (LinkNode) addNode(list, shipment); //adding shipment to the linked list

        System.out.println("1. Receive another shipment\n2. Exit");
        int input = scanner.nextInt();
        if (input == 1)
            receive();
        else if (input == 2)
            menu();
    } //receives the shipments and add it to the list
    public static void send() {
        System.out.println("1. The first shipment in the queue\n2. The nearest destination\n3. Exit");

        int menuInput = scanner.nextInt(); //menu option

        switch(menuInput) {
            case 1 -> firstInQueue();
            case 2 -> nearestDestination();
            case 3 -> menu();
            default -> {
                System.out.println("There is no such option. Please try again!");
                send();
            }
        }
    } //sends the shipments
    public static void newStatus() {
        System.out.println("Tracking code: ");
        int tc = scanner.nextInt();
        System.out.println("New status: ");
        String status = scanner.next();

        Shipment shipment = (Shipment) find(tc);
        if (shipment != null) { //if the shipment exists in the list
            if (statusTop != shipment.status.length - 1) {
                statusTop++;
                shipment.status[statusTop] = status;
                System.out.println("Status change was successful!");
            }
            else //if status array is full it means it is delivered
                System.out.println("You can not change the status after being delivered.");

            System.out.println("1. Change another shipments status\n2. Exit");
            int input = scanner.nextInt();
            if (input == 1)
                newStatus();
            else if (input == 2)
                menu();
        }
        else {
            System.out.println("Theres is no such shipment with this tracking code. Please try again");
            newStatus();
        }
    } //changing the status of the shipment
    public static void trackShipment() {
        System.out.println("Tracking code: ");
        int tc = scanner.nextInt();
        Shipment shipment = (Shipment) find(tc);

        System.out.println("Previous status: " + shipment.status[0]);
        System.out.println("Current status: " + shipment.status[1]);

        System.out.println("1. Track another shipment\n2. Exit");
        int input = scanner.nextInt();
        if (input == 1)
            trackShipment();
        else if (input == 2)
            menu();
    } //tracks the shipment by its tracking code
    public static void archive() {
        System.out.println("1. List of all shipments\n2. Search the shipment\n3. Exit");
        int option = scanner.nextInt();
        switch (option) {
            case 1 -> {
                printLinkedList();
                archive();
            }
            case 2 -> trackShipment();
            case 3 -> menu();
            default -> {
                System.out.println("There is no such option. Please try again!");
                archive();
            }
        }
    } //shipments archive
    public static void firstInQueue() {
        Shipment latestShipment = (Shipment) peek(); //peek the latest shipment in the list
        assert latestShipment != null; //shipment shouldn't be null
        System.out.println("Name: " + latestShipment.name);
        System.out.println("Senders name: " + latestShipment.sender);
        System.out.println("Receivers name: " + latestShipment.receiver);
        System.out.println("Destination distance: " + latestShipment.dd);
        System.out.println("Tracking code: " + latestShipment.tc);

        System.out.println("\n1. Confirm\n2.Disprove\n3. Exit");
        int confirm = scanner.nextInt();
        switch (confirm) {
            case 1 -> { //delivery
                System.out.println("Your Shipment has been sent successfully!");
                pop();
                menu();
            }
            case 2 -> send(); //previous menu
            case 3 -> menu();
            default -> {
                System.out.println("There is no such option. Please try again!");
                firstInQueue();
            }
        }
    } //first in queue priority
    public static void nearestDestination() {
        Shipment nearestShipment = heap[0]; //nearest shipment is the root of the min heap
        assert nearestShipment != null; //shipment shouldn't be null
        System.out.println("Name: " + nearestShipment.name);
        System.out.println("Senders name: " + nearestShipment.sender);
        System.out.println("Receivers name: " + nearestShipment.receiver);
        System.out.println("Destination distance: " + nearestShipment.dd);
        System.out.println("Tracking code: " + nearestShipment.tc);

        System.out.println("\n1. Confirm\n2.Disprove\n3. Exit");
        int confirm = scanner.nextInt();
        switch (confirm) {
            case 1 -> { //delivery
                System.out.println("Your Shipment has been sent successfully!");
                remove();
                menu();
            }
            case 2 -> send(); //previous menu
            case 3 -> menu();
            default -> {
                System.out.println("There is no such option. Please try again!");
                nearestDestination();
            }
        }
    } //nearest destination distance priority
    public static void printLinkedList() {
        LinkNode head = list;
        while (head.data != null) {
            System.out.println("Shipments Name: " + head.data.name);
            System.out.println("Shipments Sender: " + head.data.sender);
            System.out.println("Shipments Receiver: " + head.data.receiver);
            System.out.println("Shipments Destination Distance: " + head.data.dd);
            System.out.println("Shipments Tracking Code: " + head.data.tc);
            System.out.println("Shipments Previous Status: " + head.data.status[0]);
            System.out.println("Shipments Previous Current: " + head.data.status[1]);
            System.out.println("\n\n");

            head = head.next;
        }
    } //prints all the shipments that are stored in archive
    public static Object find(int trackingCode) {
        Shipment shipment = null; //if we couldn't fine the shipment returns null
        LinkNode ll = list; //temp list for searching
        while (ll.data != null) {
            if (ll.data.tc == trackingCode) {
                shipment = ll.data;
                break;
            }
            ll = ll.next; //next element
        }
        return shipment;
    } //linear search for searching
    public static Object addNode(Object head, Object data) {
        if (head == null)
            head = new LinkNode((Shipment) data); //if the head is null create a new head
        else { //if not insert the new node to last
            LinkNode node = (LinkNode) head;
            while (node.next != null)
                node = node.next;
            node.next = new LinkNode((Shipment) data);
        }
        System.out.println("Your shipment received successfully!");
        return head;
    } //adding to the list
    public static void heapify(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild <= last && heap[leftChild].dd < heap[index].dd) //if the left child is smaller than this element
            smallest = leftChild;
        if (rightChild <= last && heap[rightChild].dd < heap[smallest].dd) //if the right child is smaller than this element
            smallest = rightChild;

        if (smallest != index) { //if the current element is not the smallest we swap the current with smallest
            swap(index, smallest);
            heapify(smallest);
        }
    } //heapify the min heap
    public static void insert(Object shipment) {
        if (last >= heap.length)
            return;
        last++;
        heap[last] = (Shipment) shipment; //insert the element at the end
        int current = last; //current index
        int parent = (current - 1) / 2; //parent of current index
        //fix the min heap property if it is violated
        while (current > 0 && heap[current].dd < heap[parent].dd) {
            swap(current, parent);
            current = parent;
        }
        heapify(last);
    } //adding to the min heap
    public static void remove() {
        heap[0] = heap[last];  //update root value with the last element
        last--; //now remove the last element, by decreasing the size
        heapify(0); //call heapify to maintain the min heap
    } //removing the root(min element)
    public static void swap(int i, int j) {
        Shipment temp = heap[i];
        heap[i] = heap[j];
        heap[i] = temp;
    } //swap function
    public static void push(Object shipment) {
        if (top == array.length - 1) { //array is full
            //creating a new array to copy the original array into for more capacity
            Shipment[] newArray = new Shipment[array.length + 10];
            System.arraycopy(array, 0, newArray, 0, 10);
            array = newArray;
        }
        top++;
        array[top] = (Shipment) shipment; //add shipment to array
    } //adding to the queue
    public static void pop() {
        if (top == -1) //list is empty
            System.out.println("Shipment queue is empty. Can't pop shipment.");
        else
            top--; //changing the array pointer to top - 1
    } //deletes the top element of the queue
    public static Object peek() {
        if (top == -1) { //list is empty
            System.out.println("Shipment queue is empty. No shipment to peek.");
            return null;
        }
        else
            return array[top];
    } //returns the top element of the queue
}