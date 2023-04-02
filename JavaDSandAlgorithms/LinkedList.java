import java.util.*;

/*
 * Learning how to create and work with LinkedLists in Java
 */

public class LinkedList {
    static Node head;
    Node slow_ptr, fast_ptr, second_half;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            next = null;
        }

        Node(char d) {
            this.data = d;
            next = null;
        }
    }

    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }

    // adds a node to front of list
    public void push(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    // adds a new node after a given node
    public void insertAfter(Node prev_node, int new_data) {
        if (prev_node == null) {
            System.out.println("The given node cannot be null");
            return;
        }

        Node new_node = new Node(new_data);
        new_node.next = prev_node.next;

        prev_node.next = new_node;

    }

    public void append(int new_data) {
        Node new_node = new Node(new_data);
        if (head == null) {
            head = new Node(new_data);
            return;
        }
        new_node.next = null;
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new_node;
        return;
    }

    public void deleteNode(int key) {
        Node temp = head;
        Node prev = null;
        if (temp != null && temp.data == key) {
            head = temp.next;
            return;
        }
        while (temp != null && temp.data != key) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            return;
        }
        prev.next = temp.next;

    }

    public int getCountRec(Node node, int count) {
        // Base case
        if (node == null)
            return count;

        // Count is this node plus rest of the list
        return getCountRec(node.next, 1 + count);
    }

    /* Wrapper over getCountRec() */
    public int getCount() {
        return getCountRec(head, 0);
    }

    public boolean search(Node head, int x) {
        Node current = head;
        while (current != null) {
            if (current.data == x) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int getNth(int index) {
        Node current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.data;
            }
            count++;
            current = current.next;
        }
        assert (false);
        return 0;
    }

    public boolean isPalindromeUsingStack(Node head) {
        Node current = head;
        boolean isplain = true;
        Stack<Integer> stack = new Stack<Integer>();
        while (current != null) {
            stack.push(current.data);
            current = current.next;
        }
        while (head != null) {
            int i = stack.pop();
            if (head.data == i) {
                isplain = true;
            } else {
                isplain = false;
                break;
            }
            head = head.next;
        }
        return isplain;
    }

    public Node reverse(Node node) {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }

    public void printReverse(Node head) {
        if (head == null)
            return;
        printReverse(head.next);
        System.out.print(head.data + " ");
    }

    public static void main(String[] args) {
        LinkedList l1 = new LinkedList();
        LinkedList.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        // links created nodes to head
        LinkedList.head.next = second;
        second.next = third;

        l1.push(9);
        l1.insertAfter(second, 5);
        l1.append(8);
        l1.append(8);

        System.out.println("Linked List: ");
        l1.printList();

        System.out.println();
        System.out.println();

        System.out.println("Linked List after deletion of 1: ");
        l1.deleteNode(1);
        l1.printList();

        System.out.println();
        System.out.println();

        System.out.println("Length of Linked List: " + l1.getCount());

        System.out.print("Search for 2: ");
        System.out.print(l1.search(LinkedList.head, 2));

        System.out.println();

        System.out.println("Getting element at index 0: " + l1.getNth(0));

        System.out.println("isPalidrome: " + l1.isPalindromeUsingStack(LinkedList.head));

        System.out.println();

        System.out.println("Reverse the list: ");
        head = l1.reverse(head);
        l1.printList();

    }
}

