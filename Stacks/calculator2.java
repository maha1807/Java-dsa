import java.util.*;
class Solution {
    public int calculate(String s) {

        // Stack stores numbers that will eventually be added.
        // For '-' we store negative numbers.
        // For '*' and '/' we immediately compute and push the result.
        Stack<Integer> st = new Stack<>();

        // Builds the current number digit by digit.
        int num = 0;

        // Stores the operator that should be applied to 'num'.
        // Initially '+' because the first number should simply be added.
        char sign = '+';

        // Traverse the entire string.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If current character is a digit,
            // keep building the number.
            // Example:
            // "123"
            // num = 1 -> 12 -> 123
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            // Process the current number when:
            // 1. We reach an operator (+,-,*,/)
            // 2. We reach the end of the string.
            if ((!Character.isDigit(c) && c != ' ')
                    || i == s.length() - 1) {

                // Previous operator was '+'
                // So add the number to the stack.
                if (sign == '+') {
                    st.push(num);
                }

                // Previous operator was '-'
                // Push negative number.
                else if (sign == '-') {
                    st.push(-num);
                }

                // Previous operator was '*'
                // Multiplication has higher precedence,
                // so compute immediately.
                else if (sign == '*') {
                    st.push(st.pop() * num);
                }

                // Previous operator was '/'
                // Division also has higher precedence,
                // so compute immediately.
                else if (sign == '/') {
                    st.push(st.pop() / num);
                }

                // Save the current operator
                // for the next number.
                sign = c;

                // Reset num because we will
                // start building the next number.
                num = 0;
            }
        }

        // Add everything in the stack.
        int ans = 0;

        while (!st.isEmpty()) {
            ans += st.pop();
        }

        return ans;
    }
}

// Dry Run
// Input
// s = "3+2*2"
// Initial State
// stack = []
// num = 0
// sign = '+'
// i = 0
// c = '3'

// Digit:

// num = 3
// i = 1
// c = '+'

// Process previous sign:

// sign = '+'

// push(3)

// stack = [3]

// Update:

// sign = '+'
// num = 0
// i = 2
// c = '2'

// Digit:

// num = 2
// i = 3
// c = '*'

// Process previous sign:

// sign = '+'

// push(2)

// stack = [3,2]

// Update:

// sign = '*'
// num = 0
// i = 4
// c = '2'

// Digit:

// num = 2

// This is the last character, so process it.

// Previous sign:

// sign = '*'

// Compute:

// pop() = 2

// 2 * 2 = 4

// push(4)

// Stack:

// [3,4]
// Final Sum
// ans = 0

// pop 4 -> ans = 4
// pop 3 -> ans = 7

// Return:

// 7
// Another Dry Run
// Input
// s = "14-3/2"
// Read 14
// stack = [14]
// sign = '-'
// Read 3
// stack = [14,-3]
// sign = '/'
// Read 2 (end)
// pop -3

// -3 / 2 = -1

// stack = [14,-1]
// Sum
// 14 + (-1) = 13

// Answer:

// 13