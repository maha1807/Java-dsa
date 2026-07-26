class Solution {

    // Main function
    public int subarraysWithKDistinct(int[] nums, int k) {

        // Exactly K Distinct
        //
        // = At Most K Distinct
        // - At Most (K-1) Distinct
        //
        // This is the main trick of this problem.
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    // Function to count the number of subarrays
    // having AT MOST k distinct integers.
    private int atMost(int[] nums, int k) {

        // HashMap
        //
        // Key   -> Number
        // Value -> Frequency inside the current window
        HashMap<Integer, Integer> map = new HashMap<>();

        // Left pointer of the sliding window
        int left = 0;

        // Stores the total number of valid subarrays
        int count = 0;

        // Move the right pointer one element at a time
        for (int right = 0; right < nums.length; right++) {

            // Add the current element into the window.
            // Increase its frequency.
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            // If the window contains more than k distinct numbers,
            // shrink the window from the left.
            while (map.size() > k) {

                // Remove one occurrence of the left element
                map.put(nums[left], map.get(nums[left]) - 1);

                // If frequency becomes zero,
                // remove that element completely.
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }

                // Move left pointer
                left++;
            }

            // Current window is valid.
            //
            // Number of valid subarrays ending at "right"
            // is equal to window length.
            //
            // Window Length = right-left+1
            count += right - left + 1;
        }

        // Return total number of valid subarrays.
        return count;
    }
}

This is one of the most important Hard Sliding Window problems. The main trick is:

Exactly K Distinct = At Most K Distinct − At Most (K−1) Distinct

Instead of solving "Exactly K" directly, we solve the easier problem "At Most K" twice.

Approach Name

Sliding Window + HashMap

Intuition

Suppose

nums = [1,2,1,2,3]

k = 2

We want

Exactly 2 distinct

This is difficult to calculate directly.

Instead calculate

At Most 2 distinct

and

At Most 1 distinct

Then

Exactly 2
=
At Most 2
-
At Most 1

Example

At Most 2 = 12

At Most 1 = 5

Exactly 2 = 12 - 5 = 7


Why do we add
count += right-left+1;

Suppose

left = 2

right = 5

Window

Index

2 3 4 5

All subarrays ending at index 5

[5]

[4,5]

[3,4,5]

[2,3,4,5]

Total

4

Formula

right-left+1

5-2+1

4

That is why

count += right-left+1;
Why while?

Suppose

Window

[1,2,1,2,3]

Map

1 → 2

2 → 2

3 → 1

Distinct numbers

3

Not allowed.

Remove left.

Still

1

2

3

Three distinct numbers.

Need to remove again.

So

while(map.size()>k)

instead of

if(map.size()>k)
Dry Run

Input

nums = [1,2,1,2,3]

k = 2

We first calculate

AtMost(2)
Initial
left = 0

count = 0

map = {}
right = 0

Current number

1

Map

1 → 1

Window

[1]

Window Length

1

Subarrays ending here

[1]

Update

count = 1
right = 1

Number

2

Map

1 → 1

2 → 1

Window

[1 2]

Window Length

2

Subarrays ending here

[2]

[1,2]

Update

count = 3
right = 2

Number

1

Map

1 → 2

2 → 1

Window

[1 2 1]

Window Length

3

Subarrays ending here

[1]

[2,1]

[1,2,1]

Update

count = 6
right = 3

Number

2

Map

1 → 2

2 → 2

Window

[1 2 1 2]

Window Length

4

Subarrays ending here

[2]

[1,2]

[2,1,2]

[1,2,1,2]

Update

count = 10
right = 4

Number

3

Map

1 → 2

2 → 2

3 → 1

Distinct

3

Invalid.

Shrink.

Remove

1

Map

1 → 1

2 → 2

3 → 1

Still invalid.

Shrink.

Remove

2

Map

1 → 1

2 → 1

3 → 1

Still invalid.

Shrink.

Remove

1

Map

1 → 0

Remove it.

Map

2 → 1

3 → 1

Window

[2 3]

Window Length

2

Subarrays ending here

[3]

[2,3]

Update

count = 12

So

AtMost(2)

=

12

Similarly

AtMost(1)

=

5

Finally

Exactly2

=

12-5

=

7
Time Complexity

Each element is added to the window once and removed at most once.

Time Complexity = O(n)

Since atMost() is called twice:

O(n) + O(n) = O(2n)

Ignoring constants:

Final Time Complexity = O(n)
Space Complexity

The HashMap stores at most k + 1 distinct elements before shrinking.

Space Complexity = O(k)
Sliding Window Pattern Summary
Problem Type	Update Statement
Longest valid window	ans = Math.max(ans, right-left+1)
Smallest valid window	ans = Math.min(ans, right-left+1)
Count all valid subarrays	count += right-left+1
Exactly K distinct	atMost(k) - atMost(k-1)