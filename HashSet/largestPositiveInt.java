class Solution {
    public int findMaxK(int[] nums) {

        // Create a HashSet to store all numbers in the array.
        // HashSet allows O(1) average time lookup.
        HashSet<Integer> set = new HashSet<>();

        // Add every element of the array into the HashSet.
        for (int num : nums) {
            set.add(num);
        }

        // Store the largest positive integer k
        // such that both k and -k exist.
        // Initialize with -1 because if no such number exists,
        // the problem asks us to return -1.
        int ans = -1;

        // Traverse the array again.
        for (int num : nums) {

            // Check two conditions:
            // 1. num must be positive.
            // 2. Its negative counterpart (-num) must exist in the set.
            if (num > 0 && set.contains(-num)) {

                // Update the answer with the larger value.
                ans = Math.max(ans, num);
            }
        }

        // Return the largest valid k or -1 if none exists.
        return ans;
    }
}

// Dry Run
// Input
// nums = [-1, 10, 6, 7, -7, 1]
// Step 1: Build HashSet
// set = {}

// Add elements one by one:

// num	set
// -1	{-1}
// 10	{-1,10}
// 6	{-1,10,6}
// 7	{-1,10,6,7}
// -7	{-1,10,6,7,-7}
// 1	{-1,10,6,7,-7,1}

// Final:

// set = {-1,10,6,7,-7,1}
// ans = -1
// Step 2: Traverse Again
// Iteration 1
// num = -1

// Condition:

// num > 0

// is false.

// Skip.

// ans = -1
// Iteration 2
// num = 10

// Check:

// set.contains(-10)

// False.

// ans = -1
// Iteration 3
// num = 6

// Check:

// set.contains(-6)

// False.

// ans = -1
// Iteration 4
// num = 7

// Check:

// set.contains(-7)

// True ✅

// Update:

// ans = Math.max(-1, 7)
//     = 7
// Iteration 5
// num = -7

// Negative, so skip.

// ans = 7
// Iteration 6
// num = 1

// Check:

// set.contains(-1)

// True ✅

// Update:

// ans = Math.max(7, 1)
//     = 7
// Final Answer
// return 7;
// Visualization
// nums = [-1, 10, 6, 7, -7, 1]

// Positive numbers checked:

// 10 → -10 ❌
// 6  → -6  ❌
// 7  → -7  ✅
// 1  → -1  ✅

// Largest valid k = 7
// Why HashSet?

// Without a HashSet, checking -num would take O(n) time each.

// With a HashSet:

// set.contains(-num)

// takes O(1) average time.

// Therefore:

// Time Complexity = O(n)
// Space Complexity = O(n)