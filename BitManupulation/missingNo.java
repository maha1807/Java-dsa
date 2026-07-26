class Solution {
    public int missingNumber(int[] nums) {

        // Initialize xor with n (nums.length).
        // We start with n because the loop only goes from 0 to n-1.
        // This ensures that all numbers from 0 to n are included in the XOR operation.
        int xor = nums.length;

        // Traverse the array once
        for (int i = 0; i < nums.length; i++) {

            // XOR the current index.
            // This represents the expected numbers (0 to n-1).
            xor ^= i;

            // XOR the current array element.
            // This represents the actual numbers present in the array.
            xor ^= nums[i];
        }

        // All numbers that appear in both the expected range (0 to n)
        // and the array cancel each other out because:
        // x ^ x = 0
        // 0 ^ y = y
        //
        // The only number left after all cancellations
        // is the missing number.
        return xor;
    }
}

Approach Behind the Code
Idea

The array contains n distinct numbers from the range 0 to n, with one number missing.

Instead of searching for the missing number directly, we XOR:

Every expected number (0 to n)
Every number actually present in the array

Since every existing number appears twice (once as an index value and once in the array), they cancel each other out.

XOR Properties Used
a ^ a = 0        // Same numbers cancel each other.

a ^ 0 = a        // XOR with 0 leaves the number unchanged.

XOR is commutative and associative.
Order does not matter.
Example
nums = [3,0,1]
n = 3

Expected numbers:
0 1 2 3

Actual numbers:
3 0 1

The code computes:

3 ^ 0 ^ 3 ^ 1 ^ 0 ^ 2 ^ 1

Rearranging:

(3 ^ 3) ^ (0 ^ 0) ^ (1 ^ 1) ^ 2

Everything cancels:

0 ^ 0 ^ 0 ^ 2 = 2

So the missing number is:

2




import java.util.HashSet;

class Solution {
    public int missingNumber(int[] nums) {

        // Create a HashSet to store all numbers from the array
        HashSet<Integer> set = new HashSet<>();

        // Add every element of the array into the HashSet
        for (int num : nums) {
            set.add(num);
        }

        // Check every number from 0 to n
        for (int i = 0; i <= nums.length; i++) {

            // If the current number is not present,
            // it is the missing number
            if (!set.contains(i)) {
                return i;
            }
        }

        // This line will never be reached because
        // one number is always missing
        return -1;
    }
}