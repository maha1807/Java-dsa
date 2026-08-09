class Solution {
    public String reverseWords(String s) {

        // StringBuilder is used to efficiently build the final answer.
        // Strings are immutable in Java, so using StringBuilder avoids
        // creating multiple unnecessary String objects.
        StringBuilder ans = new StringBuilder();

        // Start from the last character of the string.
        // Our idea is to read the words from right to left.
        int i = s.length() - 1;

        // Continue until we have processed the entire string.
        while (i >= 0) {

            // ----------------------------------------------------------
            // STEP 1 : Skip all trailing spaces.
            //
            // Example:
            // "  the sky is blue   "
            //                     ^
            //                     i
            //
            // Move i left until it reaches the last character of a word.
            // ----------------------------------------------------------
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }

            // If i becomes -1, it means the string contained only spaces
            // or we have already processed every word.
            if (i < 0) {
                break;
            }

            // ----------------------------------------------------------
            // STEP 2 : Find the starting index of the current word.
            //
            // Initially,
            //          blue
            //             ^
            //             i
            //
            // j starts from i and moves left until it finds a space.
            // ----------------------------------------------------------
            int j = i;

            while (j >= 0 && s.charAt(j) != ' ') {
                j--;
            }

            // ----------------------------------------------------------
            // STEP 3 : Extract the current word.
            //
            // substring(start,end)
            //
            // start = j + 1
            // end   = i + 1
            //
            // Why i+1?
            //
            // substring() excludes the ending index.
            //
            // Example:
            //
            // String = "the sky is blue"
            //
            //             j      i
            //             |      |
            //             10    13
            //
            // substring(11,14)
            //
            // gives "blue"
            // ----------------------------------------------------------
            ans.append(s.substring(j + 1, i + 1));

            // ----------------------------------------------------------
            // STEP 4 : Skip all spaces before the next word.
            //
            // Example:
            //
            // "the   sky"
            //      ^
            //      j
            //
            // Move j left until another word begins.
            // ----------------------------------------------------------
            while (j >= 0 && s.charAt(j) == ' ') {
                j--;
            }

            // ----------------------------------------------------------
            // STEP 5 : Add one space only if another word exists.
            //
            // Without this condition,
            //
            // "blue is sky the "
            //
            // would contain an extra space at the end.
            // ----------------------------------------------------------
            if (j >= 0) {
                ans.append(' ');
            }

            // ----------------------------------------------------------
            // STEP 6 : Move i to continue processing the remaining words.
            //
            // Example:
            //
            // the sky is blue
            //        ^
            //        i
            //
            // ----------------------------------------------------------
            i = j;
        }

        // Convert StringBuilder into String.
        return ans.toString();
    }
}

Approach

Instead of splitting the string into an array of words, we scan the string from right to left.

The algorithm follows these steps:

Start from the last character.
Skip all spaces.
Find the current word.
Add the word to the answer.
Skip spaces before the next word.
Repeat until the beginning of the string.

This avoids using split() and still runs in O(n) time.

Logic

Suppose the input is

"  the   sky   is   blue  "
Initial Position
  the   sky   is   blue
                      ^
                      i

Skip trailing spaces.

  the   sky   is   blue
                     ^
                     i
Find the Word

Move j backwards.

  the   sky   is   blue
                 ^    ^
                 j    i

Current word:

blue

Append it.

ans = "blue"
Skip Spaces
  the   sky   is   blue
              ^
              j

Append one space.

ans = "blue "
Repeat

Next word

is
ans = "blue is "

Next

sky
ans = "blue is sky "

Next

the
ans = "blue is sky the"

Done.

Complete Dry Run

Input

s = "  the   sky   is   blue  "
Iteration 1
Variable	Value
i	Points to e
j	Moves left until space
Word	blue
ans	"blue"
Iteration 2
Variable	Value
i	Points to s
j	Moves left until space
Word	is
ans	"blue is"
Iteration 3
Variable	Value
i	Points to y
j	Moves left until space
Word	sky
ans	"blue is sky"
Iteration 4
Variable	Value
i	Points to e
j	Moves left until beginning
Word	the
ans	"blue is sky the"
Final Output
blue is sky the
Visualization
Input

"  the   sky   is   blue  "

                         i
                         ↓

Skip spaces
                         ↓

Find word
                  j      i
                  ↓      ↓
              blue

Answer

blue

----------------------------------

Move to previous word

          j      i
          ↓      ↓
          is

Answer

blue is

----------------------------------

Move again

      j      i
      ↓      ↓
      sky

Answer

blue is sky

----------------------------------

Move again

j      i
↓      ↓
the

Answer

blue is sky the
Time Complexity
Scanning the string: O(n)
Each character is visited at most twice.

Overall Time Complexity: O(n)

Space Complexity
StringBuilder stores the final output.

Space Complexity: O(n)