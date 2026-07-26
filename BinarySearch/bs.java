class Solution {
    public int search(int[] nums, int target) {

        // Start searching from the first element
        int low = 0;

        // End searching at the last element
        int high = nums.length - 1;

        // Continue searching until the search space becomes empty
        while (low <= high) {

            // Calculate the middle index
            // Using this formula avoids integer overflow
            int mid = low + (high - low) / 2;

            // If middle element is the target,
            // we have found the answer.
            if (nums[mid] == target) {
                return mid;
            }

            // If middle element is smaller than target,
            // target can only be present in the right half.
            else if (nums[mid] < target) {

                // Ignore left half
                low = mid + 1;
            }

            // Otherwise target is smaller than middle element,
            // so search in the left half.
            else {

                // Ignore right half
                high = mid - 1;
            }
        }

        // Target is not present in the array
        return -1;
    }
}

Binary Search - Approach
Idea

Since the array is sorted, we don't need to search every element.

At every step:

Find the middle element.
If middle element is the target → return its index.
If target is greater than middle element → search in the right half.
Otherwise → search in the left half.

Every iteration removes half of the remaining elements, making Binary Search very efficient.

Time Complexity
Best Case: O(1)
Worst Case: O(log n)
Space Complexity
O(1) (No extra space used)


Logic Behind Every Variable
low
Left boundary of the current search space.
high
Right boundary of the current search space.
mid
Middle index of the current search space.

Formula:

mid = low + (high - low) / 2;

Instead of

(low + high) / 2

because

low + high

may overflow for very large numbers.

Dry Run

Example:

nums = [2,4,6,8,10,12,14]
target = 10

Initial State

low = 0
high = 6

Array

Index : 0 1 2 3 4 5 6
Value : 2 4 6 8 10 12 14
Iteration 1
low = 0
high = 6

mid = 0 + (6-0)/2
    = 3
nums[mid] = 8

Compare

8 < 10

So target is on the right side.

Update

low = mid + 1
    = 4

high = 6

Remaining Search Space

10 12 14
Iteration 2
low = 4
high = 6

mid = 4 + (6-4)/2
    = 5
nums[mid] = 12

Compare

12 > 10

Target must be on the left side.

Update

high = mid - 1
     = 4

Remaining Search Space

10
Iteration 3
low = 4
high = 4

mid = 4
nums[mid] = 10

Target found.

Return

4
Visualization
Initial

2 4 6 8 10 12 14
L     M        H

Target > Mid
↓

Ignore Left Half

10 12 14
L  M   H

Target < Mid
↓

Ignore Right Half

10
L
M
H

Target Found
How the Search Space Shrinks
7 elements
↓

3 elements
↓

1 element
↓

Found

Instead of checking

2
4
6
8
10

one by one (Linear Search),

Binary Search keeps discarding half of the array after every comparison.

Small Correction in Your Code

Your original code used:

if(a[mid] == target)

and

else if(a[mid] < target)

There is no variable named a. It should be:

if (nums[mid] == target)

and

else if (nums[mid] < target)

Otherwise, the code will produce a compilation error.