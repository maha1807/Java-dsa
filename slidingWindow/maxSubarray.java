
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {

        // Stores the frequency of each number in the current window
        Map<Integer, Integer> map = new HashMap<>();

        // Stores the sum of the current window
        long windowSum = 0;

        // Stores the maximum valid window sum found so far
        long maxSum = 0;

        // j represents the right end of the sliding window
        for (int j = 0; j < nums.length; j++) {

            // Add the new element entering the window
            windowSum += nums[j];

            // Increase its frequency in the HashMap
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            // If window size becomes greater than k,
            // remove the leftmost element
            if (j >= k) {

                // Element leaving the window
                int left = nums[j - k];

                // Subtract it from the current window sum
                windowSum -= left;

                // Decrease its frequency
                map.put(left, map.get(left) - 1);

                // If frequency becomes 0,
                // completely remove it from the map
                if (map.get(left) == 0) {
                    map.remove(left);
                }
            }

            // Window size becomes exactly k when j >= k-1
            // map.size() == k means all elements are distinct
            if (j >= k - 1 && map.size() == k) {

                // Update the maximum sum
                maxSum = Math.max(maxSum, windowSum);
            }
        }

        return maxSum;
    }
}
// ```


// Dry Run
// Input
// nums = [1,5,4,2,9,9,9]
// k = 3

// Initially:

// windowSum = 0
// maxSum = 0
// map = {}
// Iteration 1
// j = 0
// nums[j] = 1

// Add 1:

// windowSum = 1
// map = {1=1}

// Window size < k.

// Iteration 2
// j = 1
// nums[j] = 5

// Add 5:

// windowSum = 6
// map = {1=1, 5=1}

// Window size < k.

// Iteration 3
// j = 2
// nums[j] = 4

// Add 4:

// windowSum = 10
// map = {1=1, 5=1, 4=1}

// Now:

// j >= k-1
// 2 >= 2 ✓
// map.size() = 3 = k ✓

// So:

// maxSum = max(0,10) = 10

// Current window:

// [1,5,4]
// Iteration 4
// j = 3
// nums[j] = 2

// Add 2:

// windowSum = 12
// map = {1=1,5=1,4=1,2=1}

// Window size becomes 4, so remove:

// left = nums[3-3]
//      = nums[0]
//      = 1

// Remove 1:

// windowSum = 12 - 1 = 11
// map = {5=1,4=1,2=1}

// Distinct:

// map.size() = 3

// Update:

// maxSum = max(10,11)
//        = 11

// Current window:

// [5,4,2]
// Iteration 5
// j = 4
// nums[j] = 9

// Add 9:

// windowSum = 20
// map = {5=1,4=1,2=1,9=1}

// Remove:

// left = nums[1] = 5
// windowSum = 20 - 5 = 15
// map = {4=1,2=1,9=1}

// Distinct:

// map.size() = 3

// Update:

// maxSum = max(11,15)
//        = 15

// Current window:

// [4,2,9]
// Iteration 6
// j = 5
// nums[j] = 9

// Add:

// windowSum = 24
// map = {4=1,2=1,9=2}

// Remove:

// left = nums[2] = 4
// windowSum = 20
// map = {2=1,9=2}

// Distinct?

// map.size() = 2

// No.

// Current window:

// [2,9,9]

// Invalid because 9 appears twice.

// Iteration 7
// j = 6
// nums[j] = 9

// Add:

// windowSum = 29
// map = {2=1,9=3}

// Remove:

// left = nums[3] = 2
// windowSum = 27
// map = {9=3}

// Distinct?

// map.size() = 1

// No.

// Current window:

// [9,9,9]

// Invalid.

// Final Answer
// Maximum Sum = 15
// Subarray = [4,2,9]
// Window Movement
// [1,5,4] → sum = 10 ✅
//   [5,4,2] → sum = 11 ✅
//     [4,2,9] → sum = 15 ✅
//       [2,9,9] → invalid ❌
//         [9,9,9] → invalid ❌