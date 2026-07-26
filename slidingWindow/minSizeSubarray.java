class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        // Left pointer of the sliding window.
        // It represents the starting index of the current window.
        int l = 0;

        // Stores the sum of all elements currently inside the window.
        int sum = 0;

        // Stores the minimum valid window length found so far.
        // We initialize it with the largest integer because
        // we are looking for the MINIMUM length.
        int ans = Integer.MAX_VALUE;

        // Move the right pointer from left to right.
        // This expands the sliding window.
        for (int r = 0; r < nums.length; r++) {

            // Include the current element into the window.
            sum += nums[r];

            // If the window sum is greater than or equal to target,
            // the current window is VALID.
            // Now try to shrink it from the left to make it as small
            // as possible while still satisfying the condition.
            while (sum >= target) {

                // Calculate the current window length.
                // r - l + 1 gives the number of elements in the window.
                // Compare it with the previous minimum answer.
                ans = Math.min(ans, r - l + 1);

                // Remove the leftmost element from the window.
                // Since we are shrinking the window,
                // this element is no longer part of it.
                sum -= nums[l];

                // Move the left pointer one step forward.
                // The window has now become smaller.
                l++;
            }
        }

        // If ans is still Integer.MAX_VALUE,
        // it means no valid subarray was found.
        // Return 0 in that case.
        // Otherwise return the minimum window length.
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}

Approach: Sliding Window (Variable Size Window)

Why Sliding Window?
All numbers in the array are positive.
If the sum is less than the target, we expand the window by moving right.
If the sum is greater than or equal to the target, we shrink the window by moving left to find the smallest valid window.

Intuition

Suppose

target = 7

nums = [2,3,1,2,4,3]

Imagine a window.

Initially

[]

Move the right pointer.

[2]

Sum = 2

Too small.

Expand.

[2 3]

Sum = 5

Still too small.

Expand.

[2 3 1]

Sum = 6

Still too small.

Expand.

[2 3 1 2]

Sum = 8

Now

8 >= 7

Valid window.

Now don't expand.

Instead,

Start shrinking.

Dry Run
Initial
target = 7

nums = [2,3,1,2,4,3]

l = 0
sum = 0
ans = ∞
Iteration 1
r = 0

Window

[2]

Sum

2

Condition

2 >= 7 ?

NO

Do nothing.

Iteration 2
r = 1

Window

[2 3]

Sum

5

Still

5 >= 7 ?

NO
Iteration 3
r = 2

Window

[2 3 1]

Sum

6

Still

6 >= 7 ?

NO
Iteration 4
r = 3

Window

[2 3 1 2]

Sum

8

Now

8 >= 7

YES

Current window length

4

Update

ans = min(∞,4)

ans = 4

Shrink.

Remove

2

Window

[3 1 2]

Sum

6

Stop shrinking.

Iteration 5
r = 4

Window

[3 1 2 4]

Sum

10

Valid.

Window length

4

Update

ans=min(4,4)

ans=4

Shrink.

Remove

3

Window

[1 2 4]

Sum

7

Still valid.

Again

Current length

3

Update

ans=min(4,3)

ans=3

Shrink again.

Remove

1

Window

[2 4]

Sum

6

Now invalid.

Stop shrinking.

Iteration 6
r = 5

Window

[2 4 3]

Sum

9

Valid.

Length

3

Update

ans=min(3,3)

ans=3

Shrink.

Remove

2

Window

[4 3]

Sum

7

Still valid.

Current length

2

Update

ans=min(3,2)

ans=2

Shrink again.

Remove

4

Window

[3]

Sum

3

Invalid.

Stop.

Loop ends.

Return

2
Why do we use while?

Suppose

Window = [2 3 1 2 4]

Sum = 12

One removal gives

10

Still valid.

Another removal gives

7

Still valid.

Another removal gives

6

Now invalid.

Since we may need to remove multiple elements, we use:

while(sum >= target)

If we used:

if(sum >= target)

only one element would be removed, and we'd miss smaller valid windows.

Meaning of the Important Variables
Variable	Meaning
l	Left pointer (start of the current window)
r	Right pointer (end of the current window)
sum	Sum of all elements currently inside the window
ans	Smallest valid window length found so far
r - l + 1	Current window length
Time Complexity
Every element is added to the window once.
Every element is removed from the window at most once.

Time Complexity: O(n)

Space Complexity

Only a few variables are used.

Space Complexity: O(1)

Interview Tip (Easy Way to Identify)

When a question says:

"Smallest/Minimum subarray"
"Largest/Longest subarray"
"Continuous/Contiguous subarray"
"Positive numbers"

Think Sliding Window.

Then remember the pattern:

Expand the window by moving right.
Check if the condition is satisfied.
Shrink the window using while until it becomes invalid.
Update the answer before shrinking further. This is the standard variable-size sliding window template used in many interview problems.