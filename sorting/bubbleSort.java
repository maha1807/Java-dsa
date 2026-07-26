import java.util.*;

class Solution {

    public int[] sortArray(int[] nums) {
        bubbleSort(nums);
        return nums;
    }

    private void bubbleSort(int[] nums) {

        int n = nums.length;

        // OUTER LOOP:
        // Resembles the number of passes.
        // After every pass, one largest element
        // gets placed at its correct position.
        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            // INNER LOOP:
            // Compare adjacent elements and swap if needed.
            // The largest unsorted element "bubbles up"
            // to the end of the array.
            for (int j = 0; j < n - i - 1; j++) {

                if (nums[j] > nums[j + 1]) {

                    // Swap adjacent elements
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;

                    swapped = true;
                }
            }

            // Optimization:
            // If no swaps happened in this pass,
            // the array is already sorted.
            if (!swapped) {
                break;
            }
        }
    }
}