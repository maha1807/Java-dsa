class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {

        // Stores the total number of valid subarrays
        int ans = 0;

        // Stores the product of all elements inside the current sliding window.
        // We use long because product can become larger than int.
        long prod = 1;

        // Left pointer of the sliding window
        int left = 0;

        // If k is 0 or negative, no positive product can ever be less than k.
        if (k <= 0) {
            return 0;
        }

        // Expand the window by moving the right pointer
        for (int right = 0; right < nums.length; right++) {

            // Include the current element into the window product
            prod *= nums[right];

            // If product becomes greater than or equal to k,
            // shrink the window from the left
            while (prod >= k) {

                // Remove the leftmost element from the product
                prod /= nums[left];

                // Move left pointer forward
                left++;
            }

            /*
             After shrinking, every subarray ending at 'right'
             and starting anywhere from left to right
             has product less than k.

             Number of such subarrays =
             right - left + 1
            */
            ans += right - left + 1;
        }

        // Return the total count
        return ans;
    }
}
}
Approach
Algorithm Name

Sliding Window (Two Pointers)

Idea

Instead of checking every possible subarray (which takes O(n²)), maintain a window whose product is always less than k.

Expand the window by moving right.
If the product becomes too large (>= k), shrink the window by moving left.
Once the window becomes valid again, count all valid subarrays ending at right.
Why Sliding Window Works?

Since all numbers are positive, the product behaves predictably.

When we

multiply by a positive number → product increases.
divide by a positive number → product decreases.

Because of this monotonic behavior, we never need to move the left pointer backward.

Variables
Variable	Purpose
left	Start of current window
right	End of current window
prod	Product of all elements inside window
ans	Total valid subarrays
Dry Run
Example
nums = [10,5,2,6]
k = 100

Initially

left = 0
prod = 1
ans = 0
Iteration 1

right = 0

Window

[10]

Multiply

prod = 1 × 10 = 10

Is

10 >= 100 ?

No.

Valid subarrays ending at index 0

[10]

Count

right-left+1
=0-0+1
=1
ans = 1
Iteration 2

right = 1

Window

[10,5]

Multiply

prod = 10 × 5 = 50

Still

50 < 100

Valid subarrays

[5]
[10,5]

Count

1-0+1 = 2
ans = 3
Iteration 3

right = 2

Window

[10,5,2]

Multiply

prod = 50 × 2 = 100

Now

100 >=100

Window is invalid.

Shrink

Remove

10
prod =100/10=10

Move

left=1

Window becomes

[5,2]

Product

10

Valid.

Valid subarrays ending at index 2

[2]
[5,2]

Count

2-1+1=2
ans=5
Iteration 4

right = 3

Window

[5,2,6]

Multiply

prod =10×6=60

Still valid.

Subarrays ending at index 3

[6]
[2,6]
[5,2,6]

Count

3-1+1=3
ans=8

Final Answer

8
Why ans += right - left + 1?

Suppose the current valid window is:

left                 right
 ↓                     ↓
[5, 2, 6]

Every subarray ending at right is valid:

[6]
[2,6]
[5,2,6]

There are 3 such subarrays.

The number of starting positions is:

right - left + 1

If

left = 1
right = 3

then

3 - 1 + 1 = 3

This counts all valid subarrays ending at right in O(1) time instead of checking them individually.

Time Complexity
Each element is added to the window once and removed at most once.
Both pointers (left and right) move only forward.

Time: O(n)

Space Complexity

Only a few variables are used.

Space: O(1)