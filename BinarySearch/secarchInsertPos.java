class Solution {
    public int searchInsert(int[] nums, int target) {

        // 'low' points to the beginning of the current search range.
        int low = 0;

        // 'high' points to the end of the current search range.
        int high = nums.length - 1;

        // Continue searching while there are elements left to check.
        while (low <= high) {

            // Find the middle index.
            // This formula avoids integer overflow.
            int mid = low + (high - low) / 2;

            // If the middle element is equal to the target,
            // we have found the target, so return its index.
            if (nums[mid] == target) {
                return mid;
            }

            // If the middle element is smaller than the target,
            // then the target can only be in the right half.
            // Ignore the left half by moving 'low' forward.
            else if (nums[mid] < target) {
                low = mid + 1;
            }

            // Otherwise the middle element is greater than the target.
            // The target can only be in the left half.
            // Ignore the right half by moving 'high' backward.
            else {
                high = mid - 1;
            }
        }

        // If we exit the loop, the target was not found.
        // At this point, 'low' is exactly the index where
        // the target should be inserted to maintain sorted order.
        return low;
    }
}


Approach

Since the array is sorted in ascending order, we can use Binary Search.

Instead of checking every element one by one (Linear Search), Binary Search repeatedly divides the search space into two halves.

There are two possible cases:

Target is present → Return its index.
Target is not present → Return the position where it should be inserted to keep the array sorted.

The important observation is that when the Binary Search loop finishes, low automatically points to the correct insertion position, so we simply return low.

Time Complexity
O(log n) because we eliminate half of the search space in every iteration.
Space Complexity
O(1) because no extra space is used.





Logic Behind the Algorithm

Imagine the array as a search space.

Initially:

[ Entire Array ]

Every iteration:

Find the middle.
Compare with the target.
Remove one half of the array.

Example:

1 3 5 6 8 10 12

Searching for 8

1 3 5 |6| 8 10 12
        ↑
      mid

Since

6 < 8

The left half is discarded.

Remaining search space:

8 10 12

Again find the middle.

Eventually you'll either:

Find the target, or
Reduce the search space until no elements remain.
Dry Run 1 (Target Found)

Input:

nums = [1,3,5,6]
target = 5
Initial State
low = 0
high = 3
Index : 0 1 2 3
Value : 1 3 5 6
Iteration 1
mid = 0 + (3-0)/2
    = 1
nums[mid] = 3

Compare:

3 < 5

Move right.

low = mid + 1
     = 2

Now

low = 2
high = 3
Iteration 2
mid = 2 + (3-2)/2
    = 2
nums[mid] = 5

Target found.

Return

2
Dry Run 2 (Target Not Found)

Input:

nums = [1,3,5,6]
target = 2
Initial State
low = 0
high = 3
1 3 5 6
Iteration 1
mid = 1
nums[mid] = 3

Compare

3 > 2

Search left.

high = mid - 1
     = 0

Now

low = 0
high = 0
Iteration 2
mid = 0
nums[mid] = 1

Compare

1 < 2

Search right.

low = mid + 1
    = 1

Now

low = 1
high = 0

The condition

low <= high

becomes

1 <= 0 ❌

The loop ends.

Return

low = 1

If we insert 2 at index 1, the array becomes:

1 2 3 5 6

which is still sorted.

Why Do We Return low?

At the end of the loop:

high < low

high points to the last element smaller than the target, and low points to the first position where the target can be placed without breaking the sorted order.

For example:

nums = [1,3,5,6]
target = 4

Final state:

Index : 0 1 2 3
Value : 1 3 5 6
          ↑
         low

      ↑
     high
high = 1
low = 2

So inserting 4 at index 2 gives:

1 3 4 5 6

which is correctly sorted.