import java.util.*;
class StockSpanner {

    // Stack stores pairs of:
    // [stock price, span of that price]
    //
    // Example:
    // [100,1]
    // [80,1]
    // [70,2]
    //
    // Here:
    // index 0 -> price
    // index 1 -> span
    Stack<int[]> st;

    // Constructor
    // Runs once when the object is created.
    // Creates an empty stack.
    public StockSpanner() {
        st = new Stack<>();
    }

    // Called every time a new stock price arrives.
    public int next(int price) {

        // Every price has at least a span of 1
        // because today's price counts itself.
        int span = 1;

        // Remove all previous prices that are
        // smaller than or equal to the current price.
        while (!st.isEmpty() && st.peek()[0] <= price) {

            // Add the span of the popped element.
            // Since the current price is greater,
            // all those days are also part of its span.
            span += st.peek()[1];

            // Remove the element from the stack.
            st.pop();
        }

        // Store the current price and its span.
        st.push(new int[]{price, span});

        // Return the span of today's stock price.
        return span;
    }
}
// ```

// # Dry Run

// Prices:

// ```text
// 100, 80, 60, 70, 60, 75, 85
// ```

// ---

// ## Day 1 : next(100)

// ```text
// span = 1
// stack = []
// ```

// Push:

// ```text
// [100,1]
// ```

// Stack:

// ```text
// [[100,1]]
// ```

// Return:

// ```text
// 1
// ```

// ---

// ## Day 2 : next(80)

// ```text
// span = 1
// ```

// Top:

// ```text
// 100 > 80
// ```

// No popping.

// Push:

// ```text
// [80,1]
// ```

// Stack:

// ```text
// [[100,1],[80,1]]
// ```

// Return:

// ```text
// 1
// ```

// ---

// ## Day 3 : next(60)

// ```text
// span = 1
// ```

// Top:

// ```text
// 80 > 60
// ```

// No popping.

// Push:

// ```text
// [60,1]
// ```

// Stack:

// ```text
// [[100,1],[80,1],[60,1]]
// ```

// Return:

// ```text
// 1
// ```

// ---

// ## Day 4 : next(70)

// ```text
// span = 1
// ```

// Top:

// ```text
// 60 <= 70
// ```

// Pop:

// ```text
// span = 1 + 1 = 2
// ```

// Stack:

// ```text
// [[100,1],[80,1]]
// ```

// Push:

// ```text
// [70,2]
// ```

// Stack:

// ```text
// [[100,1],[80,1],[70,2]]
// ```

// Return:

// ```text
// 2
// ```

// ---

// ## Day 5 : next(60)

// ```text
// span = 1
// ```

// Top:

// ```text
// 70 > 60
// ```

// No popping.

// Push:

// ```text
// [60,1]
// ```

// Stack:

// ```text
// [[100,1],[80,1],[70,2],[60,1]]
// ```

// Return:

// ```text
// 1
// ```

// ---

// ## Day 6 : next(75)

// ```text
// span = 1
// ```

// Top:

// ```text
// 60 <= 75
// ```

// Pop:

// ```text
// span = 2
// ```

// Stack:

// ```text
// [[100,1],[80,1],[70,2]]
// ```

// Again:

// ```text
// 70 <= 75
// ```

// Pop:

// ```text
// span = 2 + 2 = 4
// ```

// Stack:

// ```text
// [[100,1],[80,1]]
// ```

// Top:

// ```text
// 80 > 75
// ```

// Stop.

// Push:

// ```text
// [75,4]
// ```

// Stack:

// ```text
// [[100,1],[80,1],[75,4]]
// ```

// Return:

// ```text
// 4
// ```

// ---

// ## Day 7 : next(85)

// ```text
// span = 1
// ```

// Top:

// ```text
// 75 <= 85
// ```

// Pop:

// ```text
// span = 5
// ```

// Stack:

// ```text
// [[100,1],[80,1]]
// ```

// Again:

// ```text
// 80 <= 85
// ```

// Pop:

// ```text
// span = 6
// ```

// Stack:

// ```text
// [[100,1]]
// ```

// Top:

// ```text
// 100 > 85
// ```

// Stop.

// Push:

// ```text
// [85,6]
// ```

// Stack:

// ```text
// [[100,1],[85,6]]
// ```

// Return:

// ```text
// 6
// ```

// ---

// # Final Output

// ```text
// Prices : 100 80 60 70 60 75 85
// Span   :  1  1  1  2  1  4  6
// ```

// # Why does this work?

// The stack is maintained in **strictly decreasing order of prices**.

// ```text
// Bottom → Top

// 100
// 80
// 75
// ```

// Whenever a larger price arrives, we pop all smaller prices and directly add their spans:

// ```java
// span += st.peek()[1];
// ```

// This avoids counting days one by one and makes the solution:

// ```text
// Time Complexity : O(1) amortized per query
// Overall Complexity : O(N)
// Space Complexity : O(N)
// ```
