import java.util.*;
class Solution {
    public int[] nextGreaterElements(int[] nums) {

        // Size of the array
        int n = nums.length;

        // Stores the final answers
        int[] ans = new int[n];

        // Monotonic decreasing stack.
        // It stores elements that can potentially be
        // the next greater element for future indices.
        Stack<Integer> st = new Stack<>();

        // Traverse 2*n times because the array is circular.
        // We start from the end and move towards the beginning.
        for (int i = 2 * n - 1; i >= 0; i--) {

            // Convert the imaginary index into the real index.
            // Example:
            // n = 3
            // i = 5 -> idx = 2
            // i = 4 -> idx = 1
            // i = 3 -> idx = 0
            int idx = i % n;

            // Remove all elements that are smaller than or equal
            // to nums[idx] because they can never be the next
            // greater element for nums[idx] or any element to its left.
            while (!st.isEmpty() && st.peek() <= nums[idx]) {
                st.pop();
            }

            // Only during the second pass (i < n)
            // do we calculate the answers.
            if (i < n) {

                // If the stack is empty,
                // there is no greater element.
                // Otherwise, the top of the stack
                // is the next greater element.
                ans[idx] = st.isEmpty() ? -1 : st.peek();
            }

            // Push the current element into the stack.
            // It may become the next greater element
            // for elements on its left.
            st.push(nums[idx]);
        }

        // Return the final answer array.
        return ans;
    }
}
// ```

// # Dry Run

// Input:

// ```text
// nums = [1,2,1]
// n = 3
// ```

// Since the array is circular, we process:

// ```text
// 1,2,1,1,2,1
// ```

// using:

// ```text
// i = 5,4,3,2,1,0
// ```

// ---

// ### Initial State

// ```text
// stack = []
// ans = [0,0,0]
// ```

// ---

// ### i = 5

// ```text
// idx = 5 % 3 = 2
// current = nums[2] = 1
// ```

// Stack before:

// ```text
// []
// ```

// No popping.

// Push 1.

// ```text
// stack = [1]
// ans = [0,0,0]
// ```

// ---

// ### i = 4

// ```text
// idx = 1
// current = 2
// ```

// Stack before:

// ```text
// [1]
// ```

// Pop because:

// ```text
// 1 <= 2
// ```

// Stack:

// ```text
// []
// ```

// Push 2.

// ```text
// stack = [2]
// ans = [0,0,0]
// ```

// ---

// ### i = 3

// ```text
// idx = 0
// current = 1
// ```

// Stack before:

// ```text
// [2]
// ```

// No popping.

// Push 1.

// ```text
// stack = [2,1]
// ans = [0,0,0]
// ```

// ---

// ### i = 2

// ```text
// idx = 2
// current = 1
// ```

// Stack before:

// ```text
// [2,1]
// ```

// Pop:

// ```text
// 1 <= 1
// ```

// Stack:

// ```text
// [2]
// ```

// Since:

// ```text
// i < n
// ```

// Answer:

// ```text
// ans[2] = 2
// ```

// Push 1.

// ```text
// stack = [2,1]
// ans = [0,0,2]
// ```

// ---

// ### i = 1

// ```text
// idx = 1
// current = 2
// ```

// Stack before:

// ```text
// [2,1]
// ```

// Pop:

// ```text
// 1 <= 2
// 2 <= 2
// ```

// Stack:

// ```text
// []
// ```

// Answer:

// ```text
// ans[1] = -1
// ```

// Push 2.

// ```text
// stack = [2]
// ans = [0,-1,2]
// ```

// ---

// ### i = 0

// ```text
// idx = 0
// current = 1
// ```

// Stack before:

// ```text
// [2]
// ```

// No popping.

// Answer:

// ```text
// ans[0] = 2
// ```

// Push 1.

// ```text
// stack = [2,1]
// ans = [2,-1,2]
// ```

// ---

// # Final Answer

// ```text
// [2,-1,2]
// ```

// # Key Idea

// ```java
// for (int i = 2 * n - 1; i >= 0; i--) {
//     int idx = i % n;
// }
// ```

// This line makes the array behave like:

// ```text
// [1,2,1,1,2,1]
// ```

// without actually creating another array.

// This is the standard pattern for solving Circular Next Greater Element problems using a Monotonic Stack.
