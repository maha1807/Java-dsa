public class longestPalindromeSubstring {
    
}
class Solution {

    // Main function that returns the longest palindromic substring.
    public String longestPalindrome(String s) {

        // If the string is empty, null, or has only one character,
        // it is already a palindrome.
        if (s == null || s.length() < 2)
            return s;

        // 'start' and 'end' store the boundaries
        // of the longest palindrome found so far.
        int start = 0;
        int end = 0;

        // Consider every index as the center of a palindrome.
        for (int i = 0; i < s.length(); i++) {

            // Find the longest odd-length palindrome
            // centered at index i.
            //
            // Example:
            // "racecar"
            //     ^
            int len1 = expand(s, i, i);

            // Find the longest even-length palindrome
            // centered between i and i+1.
            //
            // Example:
            // "abba"
            //    ^^
            int len2 = expand(s, i, i + 1);

            // Take whichever palindrome is longer.
            int len = Math.max(len1, len2);

            // If the current palindrome is longer than
            // the previously found longest palindrome,
            // update its boundaries.
            if (len > end - start + 1) {

                // Calculate the starting index
                // of the current palindrome.
                start = i - (len - 1) / 2;

                // Calculate the ending index.
                end = i + len / 2;
            }
        }

        // Return the substring from start to end.
        // substring() excludes the ending index,
        // so use end + 1.
        return s.substring(start, end + 1);
    }

    // Helper function that expands around the center
    // and returns the palindrome length.
    private int expand(String s, int left, int right) {

        // Expand while:
        // 1. indices are inside the string
        // 2. characters are equal
        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        // The loop stops AFTER moving one step beyond
        // the palindrome boundaries.
        //
        // Therefore,
        // palindrome length =
        // (right - 1) - (left + 1) + 1
        // = right - left - 1
        return right - left - 1;
    }
}

Logic Behind the Approach

The idea is:

Every palindrome has a center.

For every character in the string, treat it as a center and expand outward as long as the left and right characters are equal.

There are two types of palindromes:

1. Odd Length

Example:

racecar

    e

Center is one character.

Call:

expand(s, i, i)
2. Even Length

Example:

abba

 bb

Center lies between two characters.

Call:

expand(s, i, i + 1)
Why do we call expand() twice?

Suppose

s = "abba"

If we only check

expand(i, i)

we get

a
b
b
a

No center character forms "abba".

Instead,

expand(i, i+1)

starts here

a b b a
  L R

and correctly expands to

abba

That's why both odd and even centers must be checked.

Understanding the expand() Function

Suppose

s = "racecar"

We call

expand(s,3,3)

because 'e' is the center.

Initially

r a c e c a r
      L R
First iteration
e == e

Move

r a c e c a r
    L   R
Second iteration
c == c

Move

r a c e c a r
  L       R
Third iteration
a == a

Move

r a c e c a r
L           R
Fourth iteration
r == r

Move

left = -1
right = 7

Loop stops because

left >= 0

is false.

Now

return right-left-1;

becomes

7 - (-1) - 1

= 7

which is the palindrome length.

Why right - left - 1?

Suppose

abba

After expansion

left = -1
right = 4

Actual palindrome is

0...3

Length

4

Formula

right-left-1

4-(-1)-1

=4

works because both pointers have moved one position beyond the palindrome.

Why these formulas?
start = i - (len - 1) / 2;
end = i + len / 2;

These formulas compute the palindrome's boundaries correctly for both odd and even lengths.

Example 1: Odd Length ("racecar")
Length = 7

Center = 3 ('e')

Start:

3-(7-1)/2

=3-3

=0

End:

3+7/2

=3+3

=6

Correct substring:

0...6
Example 2: Even Length ("abba")
Length = 4

Center between 1 and 2

During the loop, i = 1.

Start:

1-(4-1)/2

1-1

0

End:

1+4/2

1+2

3

Correct substring:

0...3
Dry Run ("babad")

Initial:

start = 0
end = 0
i = 0

Odd:

b

Length = 1

Longest = "b".

i = 1

Odd:

bab

Length = 3

Update:

start = 0
end = 2

Longest = "bab".

i = 2

Odd:

aba

Length = 3

Same length as current longest, so no update because the condition is > (not >=).

i = 3

Only "a".

No update.

i = 4

Only "d".

No update.

Final answer:

substring(0,3)

= "bab"