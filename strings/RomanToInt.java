import java.util.HashMap;
import java.util.Map;

class Solution {

    public int romanToInt(String s) {

        // This variable stores the final integer result.
        int res = 0;

        // Create a HashMap to store Roman numerals and their integer values.
        // Key   -> Roman Character
        // Value -> Integer value
        Map<Character, Integer> roman = new HashMap<>();

        // Store all Roman numeral mappings.
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        // Traverse till the second-last character.
        // We stop at s.length()-2 because inside the loop
        // we compare the current character with the next one (i+1).
        for (int i = 0; i < s.length() - 1; i++) {

            // Get the integer value of the current Roman numeral.
            int current = roman.get(s.charAt(i));

            // Get the integer value of the next Roman numeral.
            int next = roman.get(s.charAt(i + 1));

            // If the current numeral is smaller than the next,
            // it means this numeral should be SUBTRACTED.
            //
            // Example:
            // IV
            // I = 1
            // V = 5
            //
            // Since 1 < 5,
            // answer = -1 + 5 = 4
            if (current < next) {

                res -= current;

            } else {

                // Otherwise add the current value.
                //
                // Example:
                // VI
                //
                // V = 5
                // I = 1
                //
                // Since 5 > 1,
                // answer = +5 +1 = 6
                res += current;
            }
        }

        // The loop never processes the last character.
        // Therefore add its value separately.
        return res + roman.get(s.charAt(s.length() - 1));
    }
}

public class Main {

    public static void main(String[] args) {

        // Create object of Solution class.
        Solution sol = new Solution();

        // Input Roman numeral.
        String s = "MCMXCIV";

        // Convert Roman numeral into integer.
        int result = sol.romanToInt(s);

        // Print the answer.
        System.out.println("Integer value: " + result);
    }
}


Logic Behind the Solution

The important Roman numeral rule is:

If a smaller value appears before a larger value, subtract it.
Otherwise, add it.

Examples:

Roman	Calculation	Value
VI	5 + 1	6
IV	-1 + 5	4
IX	-1 + 10	9
XL	-10 + 50	40
CM	-100 + 1000	900

So for every character:

Compare it with the next character.
If current < next, subtract.
Else, add.

This lets us solve the problem in one pass through the string.

Approach
Step 1

Store Roman values in a HashMap.

I -> 1
V -> 5
X -> 10
L -> 50
C -> 100
D -> 500
M -> 1000
Step 2

Traverse the string.

For every character,

current = value of current Roman numeral
next = value of next Roman numeral
Step 3

Compare

if(current < next)

If true

subtract current

Else

add current
Step 4

Add the last character because it is never processed inside the loop.

Dry Run

Input

MCMXCIV

Expected Answer

1994

Roman values

M =1000
C =100
M =1000
X =10
C =100
I =1
V =5
Initially
res = 0
i = 0

Current

M =1000

Next

C =100

Check

1000 < 100 ?

False

Add

res = 1000
i = 1

Current

C =100

Next

M =1000

Check

100 < 1000

True

Subtract

res = 1000 -100

res = 900
i = 2

Current

M =1000

Next

X =10

Check

1000 < 10

False

Add

res = 1900
i = 3

Current

X =10

Next

C =100

Check

10 <100

True

Subtract

res = 1890
i = 4

Current

C =100

Next

I =1

Check

100 <1

False

Add

res =1990
i = 5

Current

I =1

Next

V =5

Check

1<5

True

Subtract

res =1989

Loop ends.

Now add the last character.

Last character = V =5

1989 +5

=1994

Final Answer

1994
Time Complexity
Building the HashMap stores only 7 fixed entries → O(1)
Traversing the string once → O(n)

Overall:

Time Complexity = O(n)

where n is the length of the Roman numeral string.

Space Complexity

The HashMap always contains exactly 7 entries, regardless of the input length.

Space Complexity = O(1)