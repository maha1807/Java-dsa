class Solution {
    public void moveZeroes(int[] nums) {

        // 'left' points to the position where the next non-zero
        // element should be placed.
        int left = 0;

        // 'right' traverses every element of the array.
        for (int right = 0; right < nums.length; right++) {

            // If the current element is non-zero,
            // move it to the 'left' position.
            if (nums[right] != 0) {

                // Swap the non-zero element with the element
                // currently at the 'left' pointer.
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                // Move 'left' to the next position where
                // another non-zero element can be placed.
                left++;
            }
        }
    }
}

💡 Approach
Idea

We need to:

Move all non-zero elements to the front.
Keep their original order.
Move all zeros to the end.
Do everything in-place (without using another array).
Observation
left points to the position where the next non-zero element should be placed.
right scans every element in the array.
Whenever right finds a non-zero element, we swap it with the element at left and increment left.
If left == right, the swap happens with itself, which is completely fine.
Time Complexity
O(n) → We traverse the array only once.
Space Complexity
O(1) → No extra space is used.


Dry Run
Input
nums = [0,1,0,3,12]

Initially

left = 0
right	nums[right]	Condition	Swap	Array After Swap	left
0	0	Zero → Skip	No	[0,1,0,3,12]	0
1	1	Non-zero	Swap nums[0] & nums[1]	[1,0,0,3,12]	1
2	0	Zero → Skip	No	[1,0,0,3,12]	1
3	3	Non-zero	Swap nums[1] & nums[3]	[1,3,0,0,12]	2
4	12	Non-zero	Swap nums[2] & nums[4]	[1,3,12,0,0]	3

Final Array

[1,3,12,0,0]
Pointer Visualization
Initially
Index : 0 1 2 3 4
Array : 0 1 0 3 12
        L
        R
right = 1 (Found 1)

Swap with left

Before:
0 1 0 3 12
L   R

After:
1 0 0 3 12
  L
right = 3 (Found 3)
Before:
1 0 0 3 12
  L   R

After:
1 3 0 0 12
    L
right = 4 (Found 12)
Before:
1 3 0 0 12
    L     R

After:
1 3 12 0 0
      L
Why does this work?

Think of the array as having two regions:

| Processed | Unprocessed |

The left pointer always marks the beginning of the place where the next non-zero element should go.

As right scans the array:

If it sees a 0, it ignores it.
If it sees a non-zero, it places it at left, expanding the "non-zero" region.

Example:

Original

0 1 0 3 12
L
R

After processing 1:

1 | 0 0 3 12
  L

After processing 3:

1 3 | 0 0 12
    L

After processing 12:

1 3 12 | 0 0
       L

All non-zero elements remain in their original order, and all zeros naturally end up at the back.

When should you think of this approach?

Use this Two Pointers (Read–Write Pointer) pattern when:

You need to move certain elements to the front or back of an array.
You must perform the operation in-place.
You need to preserve the relative order of the remaining elements.
You're asked to partition an array based on a condition.

Other problems that use the same idea include:

Remove Element
Remove Duplicates from Sorted Array
Sort Array by Parity
Partition Array by a Pivot
Move Negative Numbers to One Side (depending on whether order must be preserved)