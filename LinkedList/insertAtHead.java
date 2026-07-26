// ====================== Node Class ======================
// The Node class represents a single node in the linked list.
class Node {

    // Stores the value/data of the node.
    int data;

    // Stores the reference(address) of the next node.
    Node next;

    // Constructor 1:
    // Used when we know both the data and the next node.
    Node(int data1, Node next1) {

        // Store the given data inside the node.
        data = data1;

        // Connect the current node to the next node.
        next = next1;
    }

    // Constructor 2:
    // Used when creating a node without knowing its next node.
    Node(int data1) {

        // Store the data.
        data = data1;

        // Since there is no next node yet,
        // initialize it as null.
        next = null;
    }
}


// ====================== Solution Class ======================
class Solution {

    /*
     * APPROACH:
     * ---------
     * To insert a node at the beginning (head):
     *
     * Step 1: Create a new node.
     * Step 2: Make its next pointer point to the current head.
     * Step 3: Return the new node because it becomes the new head.
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */

    // Function to insert a node at the head.
    public Node insertAtHead(Node head, int newData) {

        // Create a new node.
        // data = newData
        // next = current head
        Node newNode = new Node(newData, head);

        // The new node becomes the new head.
        return newNode;
    }

    /*
     * Function to print all elements of the linked list.
     *
     * APPROACH:
     * ---------
     * Start from the head.
     * Visit every node until null is reached.
     */

    public void printList(Node head) {

        // Temporary pointer used for traversal.
        // We don't move head directly because we want to preserve it.
        Node temp = head;

        // Continue until temp becomes null.
        while (temp != null) {

            // Print current node's data.
            System.out.print(temp.data + " ");

            // Move to the next node.
            temp = temp.next;
        }

        // Move to the next line after printing.
        System.out.println();
    }
}


// ====================== Main Class ======================
public class Main {

    public static void main(String[] args) {

        // Create an object of Solution class
        // so that we can call its methods.
        Solution sol = new Solution();


        // ================= Creating Linked List =================

        // Create the first node with data = 2.
        // head points to this node.
        Node head = new Node(2);

        // Create another node with data = 3
        // and connect it to the first node.
        head.next = new Node(3);

        // Current Linked List:
        // head
        //  ↓
        // [2 | •] ----> [3 | null]


        // Print the original list.
        System.out.print("Original List: ");
        sol.printList(head);


        // ================= Insert at Head =================

        // Insert a new node with value 1.
        // The returned node becomes the new head.
        head = sol.insertAtHead(head, 1);

        /*
         * After insertion:
         *
         * head
         *  ↓
         * [1 | •] ----> [2 | •] ----> [3 | null]
         */


        // Print the updated list.
        System.out.print("After Insertion at Head: ");
        sol.printList(head);
    }
}