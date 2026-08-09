/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;          // Stores the value of the node
 *     ListNode next;    // Stores the reference to the next node
 *
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {

    public boolean hasCycle(ListNode head) {

        // Slow pointer starts from the head.
        // It moves one step at a time.
        ListNode slow = head;

        // Fast pointer also starts from the head.
        // It moves two steps at a time.
        ListNode fast = head;

        // Continue until fast reaches the end of the list.
        // If fast becomes null, there is no cycle.
        while (fast != null && fast.next != null) {

            // Move slow pointer by one node
            slow = slow.next;

            // Move fast pointer by two nodes
            fast = fast.next.next;

            // If both pointers point to the same node,
            // a cycle exists.
            if (slow == fast) {
                return true;
            }
        }

        // If we exit the loop,
        // fast reached the end of the list.
        // Therefore, no cycle exists.
        return false;
    }
}

Logic Behind the Approach

We use Floyd's Cycle Detection Algorithm, also known as the Tortoise and Hare Algorithm.

There are two pointers:

Slow Pointer
Moves 1 node at a time.
Fast Pointer
Moves 2 nodes at a time.
Case 1: No Cycle

Example:

1 → 2 → 3 → 4 → null

Fast pointer keeps moving faster.

Eventually it reaches null.

Hence,

return false;
Case 2: Cycle Exists

Example:

1 → 2 → 3 → 4
    ↑       ↓
    ← ← ← ←

Here, the fast pointer keeps moving inside the loop.

Since it is faster than the slow pointer, it will eventually catch up.

Once

slow == fast

we know a cycle exists.

Return

true
Why Does This Work?

Think of two people running on a circular race track.

Person A runs slowly.
Person B runs twice as fast.

Even if Person B starts at the same place, eventually B will lap A.

Exactly the same thing happens in a cyclic linked list.

Dry Run

Input

head = [3,2,0,-4]
           ↑    |
           |____|

The tail points back to node 2.

Initial State
Slow = 3

Fast = 3
Iteration 1

Move pointers

Slow = 2

Fast = 0

Diagram

3 → 2 → 0 → -4
    S     F
      ↑    |
      |____|
Iteration 2

Move pointers

Slow = 0

Fast = 2

Diagram

3 → 2 → 0 → -4
    F     S
      ↑    |
      |____|
Iteration 3

Move pointers

Slow = -4

Fast = -4

Now

Slow == Fast

Return

true
Dry Run Table
Iteration	Slow Pointer	Fast Pointer	Are They Equal?
Start	3	3	Yes (starting point, ignored)
1	2	0	No
2	0	2	No
3	-4	-4	Yes → Return true
Example Without a Cycle
1 → 2 → 3 → 4 → null
Dry Run Table
Iteration	Slow	Fast
Start	1	1
1	2	3
2	3	null

The loop stops because

fast == null

Return

false
Why Do We Check This Condition?
while (fast != null && fast.next != null)

Suppose the list is

1 → 2 → null

If we directly write

fast = fast.next.next;

then fast.next is null, and accessing fast.next.next would throw a NullPointerException.

So we first check:

fast != null → Fast pointer itself exists.
fast.next != null → There is another node to move two steps.

Only then is it safe to execute:

fast = fast.next.next;
Time Complexity
Every node is visited at most a constant number of times.

Time Complexity: O(n)

Space Complexity

Only two pointers (slow and fast) are used.

Space Complexity: O(1)