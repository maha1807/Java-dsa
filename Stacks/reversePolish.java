import java.util.*;
class Solution {
    public int evalRPN(String[] tokens) {

        // Stack stores numbers while evaluating the expression.
        Stack<Integer> st = new Stack<>();

        // Traverse every token in the input.
        for (String t : tokens) {

            // Check if the current token is an operator.
            if (t.equals("+") ||
                t.equals("-") ||
                t.equals("*") ||
                t.equals("/")) {

                // Pop the topmost number.
                // This is the SECOND operand.
                int a = st.pop();

                // Pop the next number.
                // This is the FIRST operand.
                int b = st.pop();

                // Perform the operation and push
                // the result back into the stack.

                if (t.equals("+"))
                    st.push(b + a);

                else if (t.equals("-"))
                    st.push(b - a);

                else if (t.equals("*"))
                    st.push(b * a);

                else
                    st.push(b / a);
            }
            else {

                // Current token is a number.
                // Convert String to Integer and push.
                st.push(Integer.parseInt(t));
            }
        }

        // Final answer is the only element left.
        return st.peek();
    }
}

// Why do we do this?

// Suppose:

// ["4","13","5","/","+"]

// Expression:

// 4 + (13 / 5)

// RPN evaluates from left to right using a stack.

// Important Concept

// When we pop:

// int a = st.pop();
// int b = st.pop();

// The operation is:

// b operator a

// NOT

// a operator b

// because the first popped element is actually the second operand.

// Dry Run 1
// Input
// ["2","1","+","3","*"]
// Token = "2"

// Push:

// stack = [2]
// Token = "1"

// Push:

// stack = [2,1]
// Token = "+"

// Pop:

// a = 1
// b = 2

// Compute:

// 2 + 1 = 3

// Push:

// stack = [3]
// Token = "3"

// Push:

// stack = [3,3]
// Token = "*"

// Pop:

// a = 3
// b = 3

// Compute:

// 3 * 3 = 9

// Push:

// stack = [9]
// End

// Answer:

// 9
// Dry Run 2
// Input
// ["4","13","5","/","+"]
// Push 4
// stack = [4]
// Push 13
// stack = [4,13]
// Push 5
// stack = [4,13,5]
// Token = "/"

// Pop:

// a = 5
// b = 13

// Compute:

// 13 / 5 = 2

// Push:

// stack = [4,2]
// Token = "+"

// Pop:

// a = 2
// b = 4

// Compute:

// 4 + 2 = 6

// Push:

// stack = [6]
// End

// Answer:

// 6
// Pattern to Remember

// For every RPN problem:

// If token is number:
//     push it.

// If token is operator:
//     a = pop()
//     b = pop()
//     push(b operator a)
// Complexity
// Time Complexity
// O(N)

// Each token is processed once.

// Space Complexity
// O(N)

// In the worst case, all tokens are numbers and are stored in the stack.