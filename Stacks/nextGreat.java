
import java.util.*;

class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Monotonic decreasing stack.
        // Stores elements from nums2 that can potentially
        // be the next greater element.
        Stack<Integer> st = new Stack<>();

        // Stores:
        // number -> next greater number
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traverse nums2 from right to left.
        for (int i = nums2.length - 1; i >= 0; i--) {

            // Remove all smaller or equal elements because
            // they can never be the next greater element.
            while (!st.isEmpty() && st.peek() <= nums2[i]) {
                st.pop();
            }

            // If stack is empty, no greater element exists.
            // Otherwise, the top of the stack is the answer.
            int nextGreat = st.isEmpty() ? -1 : st.peek();

            // Store:
            // current number -> next greater number
            map.put(nums2[i], nextGreat);

            // Push the current number into the stack.
            st.push(nums2[i]);
        }

        // Final answer array for nums1.
        int[] ans = new int[nums1.length];

        // Since nums1 is a subset of nums2,
        // directly get the answers from the map.
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}
// ```

// # Dry Run

// Input:

// ```text
// nums1 = [4,1,2]
// nums2 = [1,3,4,2]
// ```

// ---

// ## Initial State

// ```text
// stack = []
// map = {}
// ```

// ---

// ## i = 3

// ```text
// current = 2
// ```

// Stack:

// ```text
// []
// ```

// No greater element.

// ```text
// nextGreat = -1
// map = {2=-1}
// stack = [2]
// ```

// ---

// ## i = 2

// ```text
// current = 4
// ```

// Stack:

// ```text
// [2]
// ```

// Pop because:

// ```text
// 2 <= 4
// ```

// Stack:

// ```text
// []
// ```

// No greater element.

// ```text
// nextGreat = -1
// map = {2=-1, 4=-1}
// stack = [4]
// ```

// ---

// ## i = 1

// ```text
// current = 3
// ```

// Stack:

// ```text
// [4]
// ```

// No popping because:

// ```text
// 4 > 3
// ```

// Top of stack is the answer.

// ```text
// nextGreat = 4
// map = {2=-1, 4=-1, 3=4}
// stack = [4,3]
// ```

// ---

// ## i = 0

// ```text
// current = 1
// ```

// Stack:

// ```text
// [4,3]
// ```

// No popping because:

// ```text
// 3 > 1
// ```

// Top of stack:

// ```text
// nextGreat = 3
// ```

// ```text
// map = {
//     2=-1,
//     4=-1,
//     3=4,
//     1=3
// }

// stack = [4,3,1]
// ```

// ---

// # Map after processing nums2

// ```text
// 1 -> 3
// 3 -> 4
// 4 -> -1
// 2 -> -1
// ```

// ---

// # Build answer for nums1

// ```text
// nums1 = [4,1,2]
// ```

// Lookup:

// ```text
// ans[0] = map.get(4) = -1
// ans[1] = map.get(1) = 3
// ans[2] = map.get(2) = -1
// ```

// Final Answer:

// ```text
// [-1,3,-1]
// ```

// # Time Complexity

// ```text
// O(n + m)
// ```

// where:

// * n = nums1.length
// * m = nums2.length

// # Space Complexity

// ```text
// O(m)
// ```

// for the stack and HashMap.
