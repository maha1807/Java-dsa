import java.util.*;
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {

        // Stores the maximum consecutive 1s found so far
        int max_count = 0;

        // Stores the current streak of consecutive 1s
        int current_count = 0;

        // Length of the array
        int n = nums.length;

        // Traverse the entire array
        for (int j = 0; j < n; j++) {

            // If current element is 1,
            // increase the current streak
            if (nums[j] == 1) {
                current_count++;
            }
            // If current element is 0,
            // the streak breaks
            else {

                // Update the maximum streak if needed
                max_count = Math.max(max_count, current_count);

                // Reset current streak
                current_count = 0;
            }
        }

        // Needed because the array may end with 1s
        // Example: [1,1,1]
        return Math.max(max_count, current_count);
    }
}
// Dry Run
// Input
// nums = [1,1,0,1,1,1]

// Initially:

// max_count = 0
// current_count = 0
// Iteration 1
// j = 0
// nums[0] = 1

// Since it is 1:

// current_count++

// Values:

// current_count = 1
// max_count = 0
// Iteration 2
// j = 1
// nums[1] = 1

// Again:

// current_count++

// Values:

// current_count = 2
// max_count = 0
// Iteration 3
// j = 2
// nums[2] = 0

// The streak breaks.

// Update:

// max_count = max(0,2)
//           = 2

// Reset:

// current_count = 0

// Values:

// current_count = 0
// max_count = 2
// Iteration 4
// j = 3
// nums[3] = 1
// current_count = 1
// max_count = 2
// Iteration 5
// j = 4
// nums[4] = 1
// current_count = 2
// max_count = 2
// Iteration 6
// j = 5
// nums[5] = 1
// current_count = 3
// max_count = 2
// Loop Ends

// At the end:

// max_count = 2
// current_count = 3

// Return:

// Math.max(2,3)
// = 3
// Final Answer
// 3

// because the longest consecutive sequence of 1s is:

// 1 1 1
// Table Dry Run
// j	nums[j]	current_count	max_count
// 0	1	1	0
// 1	1	2	0
// 2	0	0	2
// 3	1	1	2
// 4	1	2	2
// 5	1	3	2

// Final:

// return Math.max(2,3);

// Output:

// 3
// Why do we return this?
// return Math.max(max_count, current_count);

// Consider:

// nums = [1,1,1]

// The else block never runs, so:

// max_count = 0
// current_count = 3

// If we returned only:

// return max_count;

// Answer would be:

// 0 ❌

// Therefore:

// return Math.max(max_count, current_count);

// gives:

// 3 ✅