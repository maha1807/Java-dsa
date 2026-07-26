import java.util.*;

class Solution {

    // Stores all the subsets (final answer)
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {

        // Start recursion from index 0
        // Current subset is initially empty []
        backtrack(0, nums, new ArrayList<>());

        // Return all generated subsets
        return ans;
    }

    private void backtrack(int start,
                           int[] nums,
                           List<Integer> curr) {

        // Every current state represents a valid subset.
        // Make a COPY because curr keeps changing due to backtracking.
        ans.add(new ArrayList<>(curr));

        // Try choosing every element from 'start' onwards
        for (int i = start; i < nums.length; i++) {

            // ---------------- CHOOSE ----------------
            // Include nums[i] in the current subset
            curr.add(nums[i]);

            // ---------------- EXPLORE ----------------
            // Generate all subsets that contain nums[i]
            // We use i+1 because an element cannot be reused.
            backtrack(i + 1, nums, curr);

            // ---------------- UNDO (BACKTRACK) ----------------
            // Remove the last added element so that we can
            // try other possibilities.
            curr.remove(curr.size() - 1);
        }
    }
}