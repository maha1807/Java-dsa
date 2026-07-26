class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // List to store all unique triplets whose sum is 0
        List<List<Integer>> result = new ArrayList<>();

        // Sort the array.
        // Sorting helps us:
        // 1. Use the two-pointer technique.
        // 2. Skip duplicate triplets easily.
        Arrays.sort(nums);

        // Traverse the array.
        // We stop at nums.length - 2 because we need at least
        // two more elements after the current element.
        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate first elements.
            // Example:
            // [-1,-1,-1,2]
            // If we process every -1 as the first element,
            // we'll generate the same triplet multiple times.
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Left pointer starts immediately after i
            int left = i + 1;

            // Right pointer starts from the last element
            int right = nums.length - 1;

            // Continue searching until both pointers meet
            while (left < right) {

                // Calculate the sum of the current triplet
                int sum = nums[i] + nums[left] + nums[right];

                // If the sum is exactly 0,
                // we found a valid triplet.
                if (sum == 0) {

                    // Store the triplet in the answer list
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate values on the left side.
                    // Example:
                    // [-1,0,0,0,1]
                    // We don't want duplicate triplets.
                    while (left < right && nums[left] == nums[left + 1])
                        left++;

                    // Skip duplicate values on the right side.
                    while (left < right && nums[right] == nums[right - 1])
                        right--;

                    // Move both pointers inward
                    // to search for the next possible pair.
                    left++;
                    right--;
                }

                // Sum is too small.
                // Since the array is sorted,
                // move the left pointer to increase the sum.
                else if (sum < 0) {
                    left++;
                }

                // Sum is too large.
                // Move the right pointer to decrease the sum.
                else {
                    right--;
                }
            }
        }

        // Return all unique triplets
        return result;
    }
}


Approach
Intuition

The brute-force solution checks every possible triplet, which takes O(n³) time.

We can optimize it by:

Sorting the array.
Fixing one number (nums[i]).
Using two pointers (left and right) to find the other two numbers whose sum equals -nums[i].

Since the array is sorted:

If the sum is too small, move left to increase the sum.
If the sum is too large, move right to decrease the sum.
If the sum is 0, store the triplet and skip duplicates.

This reduces the time complexity to O(n²)


Dry Run
Input
nums = [-1,0,1,2,-1,-4]
Step 1: Sort
[-4,-1,-1,0,1,2]
Iteration 1
i = 0

nums[i] = -4

left = 1

right = 5

Current array

[-4,-1,-1,0,1,2]
  i  L         R
Check Sum
-4 + (-1) + 2 = -3

Too small.

Move left.

left = 2

Sum = -4 + (-1) + 2

= -3

Still too small.

Move left.

left = 3

Sum = -4 + 0 + 2

= -2

Still too small.

Move left.

left = 4

Sum = -4 + 1 + 2

= -1

Still too small.

Move left.

Now

left == right

Stop.

No triplet found.

Iteration 2
i = 1

nums[i] = -1

left = 2

right = 5

Current

[-4,-1,-1,0,1,2]
     i  L      R

Sum

-1 + (-1) + 2 = 0

Found

[-1,-1,2]

Store it.

Move both pointers.

left = 3

right = 4

Now

[-4,-1,-1,0,1,2]
     i     L R

Sum

-1 + 0 + 1 = 0

Found

[-1,0,1]

Store it.

Move both pointers.

left = 4

right = 3

Loop ends.

Iteration 3
i = 2

nums[2] == nums[1]

Duplicate first element.

Skip.

Remaining Iterations

No new triplets are found.

Final Answer
[
 [-1,-1,2],
 [-1,0,1]
]
Pointer Movement Summary
Sorted Array

[-4,-1,-1,0,1,2]

---------------------------------

i=-4

L=-1
R=2

Sum=-3

Move L →

---------------------------------

i=-4

L=-1
R=2

Sum=-3

Move L →

---------------------------------

i=-4

L=0
R=2

Sum=-2

Move L →

---------------------------------

i=-4

L=1
R=2

Sum=-1

Move L →

Stop

---------------------------------

i=-1

L=-1
R=2

Sum=0

Store [-1,-1,2]

Move both

---------------------------------

i=-1

L=0
R=1

Sum=0

Store [-1,0,1]

Move both

Done
Time Complexity
Sorting: O(n log n)
Outer loop: O(n)
Two-pointer scan: O(n) for each fixed element

Overall:

O(n²)
Space Complexity
O(1)

(ignoring the space used for the output list)

Interview Trick to Remember

Whenever you see a problem asking for:

3 numbers whose sum equals a target,
Unique triplets,
Or triplets in a sorted array,

think of this pattern:

Sort the array.
Fix one element (i).
Use two pointers (left and right) to find the remaining two numbers.
Skip duplicates for i, left, and right to avoid repeated triplets. This is a classic Sorting + Two Pointers pattern that is frequently tested in coding interviews.