```java
import java.util.*;   // Imports List, ArrayList, etc.

class Solution {

    // Returns all numbers in the range [1, n]
    // that are missing from the array.
    public List<Integer> findDisappearedNumbers(int[] nums) {

        // Pointer used for Cyclic Sort.
        int i = 0;

        // Rearrange the array so that every number
        // tries to go to its correct index.
        while (i < nums.length) {

            // Correct index of nums[i].
            // Example:
            // 1 should be at index 0
            // 2 should be at index 1
            // x should be at index x - 1
            int correct = nums[i] - 1;

            // If the current number is not at its
            // correct position, swap it.
            if (nums[i] != nums[correct]) {
                swap(nums, i, correct);
            }
            // Otherwise, move to the next index.
            else {
                i++;
            }
        }

        // Stores all missing numbers.
        List<Integer> ans = new ArrayList<>();

        // After cyclic sort, every incorrect index
        // represents a missing number.
        for (i = 0; i < nums.length; i++) {

            // If the number at index i is not i + 1,
            // then i + 1 is missing.
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }

        // Return the list of missing numbers.
        return ans;
    }

    // Helper function to swap two elements
    // in the array.
    private void swap(int[] nums, int first, int second) {

        // Store first element temporarily.
        int temp = nums[first];

        // Put second element in first position.
        nums[first] = nums[second];

        // Put stored value in second position.
        nums[second] = temp;
    }
}
```
