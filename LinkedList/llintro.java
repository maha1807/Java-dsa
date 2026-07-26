What should you know before Linked Lists?
1. Variables and Memory References ⭐

A linked list stores data using references (addresses) instead of continuous memory.

For example:

int a = 10;
int b = a;

Here b stores the value of a.

But with objects:

Node n1 = new Node();
Node n2 = n1;

Both n1 and n2 point to the same object in memory.

This concept is very important.

2. Classes and Objects

A linked list is nothing but a collection of Node objects.

Example:

class Node {
    int data;
    Node next;
}

Here,

data stores the value.
next stores the address of the next node.

You should know

Classes
Objects
Constructors
3. Constructors

Example

class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }
}

Whenever we write

Node node = new Node(10);

A new node is created.

4. References (Pointers)

Java doesn't have explicit pointers like C/C++, but object variables act as references.

Example

Node first = new Node(10);
Node second = new Node(20);

first.next = second;

Memory

first
  |
  V
+------+-------+
| 10   |   ----|---->
+------+-------+     |
                     V
               +------+------+
               | 20   | null |
               +------+------+

Notice that

first.next

contains the reference of the second node.

5. null

You must know what null means.

Node head = null;

means

"There is no node."

Example

10 -> 20 -> 30 -> null

The last node always points to null.

6. Object Creation

Understand

new Node(5)

It allocates memory dynamically.

Each node lives separately in memory.

Unlike arrays, nodes are not stored together.

7. Traversing

Before learning linked lists, understand loops.

Example

while(current != null){
    System.out.println(current.data);
    current = current.next;
}

The idea of moving from one node to another is the heart of linked lists.

8. Condition Statements

You'll frequently use

if(head == null)

or

if(current.next == null)

Understanding if conditions is essential.

What is a Linked List?

A Linked List is a linear data structure where each element is stored in a separate object called a node, and every node contains a reference to the next node.

Unlike an array, the elements are not stored in contiguous memory.

Structure of a Node
+---------+-----------+
|  data   |   next    |
+---------+-----------+

Example

+------+------+
| 10   |  •---|---->
+------+------+

+------+------+
| 20   |  •---|---->
+------+------+

+------+------+
| 30   | null |
+------+------+
Real-Life Analogy

Imagine a treasure hunt.

Every clue tells you where the next clue is.

Clue1 → Clue2 → Clue3 → Treasure

You cannot jump directly to Clue 3.

Similarly,

10 → 20 → 30 → 40

Each node only knows where the next node is.

Components of a Linked List
1. Node

Stores

Data
Address of next node
class Node{
    int data;
    Node next;
}
2. Head

The first node.

head
 |
 V
10 ->20 ->30

Without head, you cannot access the list.

3. Tail (optional)

Points to the last node.

head
 |
 V
10 ->20 ->30
           ^
           |
          tail
Why Do We Need Linked Lists?

Suppose you have an array:

10 20 30 40

To insert 25:

10 20 25 30 40

You need to shift elements.

Time Complexity

O(n)

In a linked list:

10 ->20 ->30

Insert

25
10 ->20 ->25 ->30

Only the links change.

No shifting is required.

Memory Representation

Array

100
104
108
112

Continuous memory.

Linked List

Address 500
+------+------+
| 10   | 900  |
+------+------+

Address 900
+------+------+
| 20   | 300  |
+------+------+

Address 300
+------+------+
| 30   | null |
+------+------+

The nodes can be anywhere in memory.

Advantages
Dynamic size.
Easy insertion and deletion.
No need for contiguous memory.
Memory is allocated only when needed.
Disadvantages
Extra memory is needed for storing the next reference.
Slower random access (you cannot directly access the 5th element).
Traversal is sequential only.
Time Complexities
Operation	Array	Linked List
Access by index	O(1)	O(n)
Search	O(n)	O(n)
Insert at beginning	O(n)	O(1)
Insert at end (with tail)	O(1)	O(1)
Insert in middle	O(n)	O(n)
Delete at beginning	O(n)	O(1)
Delete in middle	O(n)	O(n)
Basic Java Node Class
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

Creating and linking nodes:

public class Main {
    public static void main(String[] args) {

        Node first = new Node(10);
        Node second = new Node(20);
        Node third = new Node(30);

        first.next = second;
        second.next = third;

        Node current = first;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
}

Output:

10 20 30