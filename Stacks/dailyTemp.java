import java.util.*;
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        // Stack stores INDICES, not temperatures.
        // The top of the stack always contains the index
        // of the nearest warmer temperature on the right.
        Stack<Integer> st = new Stack<>();

        // Size of the temperatures array.
        int n = temperatures.length;

        // Stores the final answer.
        // ans[i] = number of days to wait for a warmer temperature.
        int[] ans = new int[n];

        // Traverse from right to left because we need information
        // about future days (elements on the right).
        for (int i = n - 1; i >= 0; i--) {

            // Remove all temperatures that are smaller than
            // or equal to the current temperature because
            // they can never be the next warmer day.
            while (!st.isEmpty()
                    && temperatures[st.peek()] <= temperatures[i]) {
                st.pop();
            }

            // If the stack is empty,
            // there is no warmer temperature in the future.
            if (st.isEmpty()) {
                ans[i] = 0;
            }
            // Otherwise, the top of the stack contains the
            // index of the next warmer temperature.
            else {
                // Number of days to wait =
                // next warmer day's index - current index
                ans[i] = st.peek() - i;
            }

            // Push the current index into the stack.
            // This index may become the answer for
            // elements to its left.
            st.push(i);
        }

        // Return the final answer array.
        return ans;
    }
}
// ```

// # Dry Run

// Input:

// ```text
// temperatures = [73,74,75,71,69,72,76,73]
//                 0  1  2  3  4  5  6  7
// ```

// ---

// ## Initial State

// ```text
// stack = []
// ans = [0,0,0,0,0,0,0,0]
// ```

// ---

// ## i = 7

// ```text
// temperature = 73
// stack = []
// ```

// No warmer day exists.

// ```text
// ans[7] = 0
// push 7
// ```

// ```text
// stack = [7]
// ans = [0,0,0,0,0,0,0,0]
// ```

// ---

// ## i = 6

// ```text
// temperature = 76
// stack = [7]
// ```

// Pop because:

// ```text
// 73 <= 76
// ```

// ```text
// stack = []
// ans[6] = 0
// push 6
// ```

// ```text
// stack = [6]
// ans = [0,0,0,0,0,0,0,0]
// ```

// ---

// ## i = 5

// ```text
// temperature = 72
// stack = [6]
// ```

// Top:

// ```text
// 76 > 72
// ```

// ```text
// ans[5] = 6 - 5 = 1
// push 5
// ```

// ```text
// stack = [6,5]
// ans = [0,0,0,0,0,1,0,0]
// ```

// ---

// ## i = 4

// ```text
// temperature = 69
// stack = [6,5]
// ```

// Top:

// ```text
// 72 > 69
// ```

// ```text
// ans[4] = 5 - 4 = 1
// push 4
// ```

// ```text
// stack = [6,5,4]
// ans = [0,0,0,0,1,1,0,0]
// ```

// ---

// ## i = 3

// ```text
// temperature = 71
// stack = [6,5,4]
// ```

// Pop:

// ```text
// 69 <= 71
// ```

// ```text
// stack = [6,5]
// ```

// Top:

// ```text
// 72 > 71
// ```

// ```text
// ans[3] = 5 - 3 = 2
// push 3
// ```

// ```text
// stack = [6,5,3]
// ans = [0,0,0,2,1,1,0,0]
// ```

// ---

// ## i = 2

// ```text
// temperature = 75
// stack = [6,5,3]
// ```

// Pop:

// ```text
// 71 <= 75
// 72 <= 75
// ```

// ```text
// stack = [6]
// ```

// Top:

// ```text
// 76 > 75
// ```

// ```text
// ans[2] = 6 - 2 = 4
// push 2
// ```

// ```text
// stack = [6,2]
// ans = [0,0,4,2,1,1,0,0]
// ```

// ---

// ## i = 1

// ```text
// temperature = 74
// stack = [6,2]
// ```

// Top:

// ```text
// 75 > 74
// ```

// ```text
// ans[1] = 2 - 1 = 1
// push 1
// ```

// ```text
// stack = [6,2,1]
// ans = [0,1,4,2,1,1,0,0]
// ```

// ---

// ## i = 0

// ```text
// temperature = 73
// stack = [6,2,1]
// ```

// Top:

// ```text
// 74 > 73
// ```

// ```text
// ans[0] = 1 - 0 = 1
// push 0
// ```

// ```text
// stack = [6,2,1,0]
// ans = [1,1,4,2,1,1,0,0]
// ```

// ---

// # Final Answer

// ```text
// [1,1,4,2,1,1,0,0]
// ```

// # Why do we store indices instead of temperatures?

// Because the question asks:

// ```text
// "How many days do I need to wait?"
// ```

// So we need:

// ```java
// days = nextWarmerIndex - currentIndex;
// ```

// which is:

// ```java
// ans[i] = st.peek() - i;
// ```

// because `st.peek()` stores the index of the next warmer day.
