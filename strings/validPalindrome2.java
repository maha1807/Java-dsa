class Solution {

    // Main function to check if the string can become
    // a palindrome after deleting at most one character.
    public boolean validPalindrome(String s) {

        // Two pointers:
        // left starts from the beginning,
        // right starts from the end.
        int left = 0;
        int right = s.length() - 1;

        // Continue until the pointers cross each other.
        while (left < right) {

            // If characters are equal,
            // move both pointers inward.
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {

                /*
                 We found the first mismatch.

                 Since we are allowed to delete ONLY ONE character,
                 we have two possibilities:

                 1. Delete the left character
                    -> Check if substring (left+1, right)
                       is a palindrome.

                 2. Delete the right character
                    -> Check if substring (left, right-1)
                       is a palindrome.

                 If either one is a palindrome,
                 return true.
                */
                return isPalindrome(s, left + 1, right) ||
                       isPalindrome(s, left, right - 1);
            }
        }

        // No mismatches found,
        // the string is already a palindrome.
        return true;
    }

    // Helper function to check whether
    // a substring is a palindrome.
    private boolean isPalindrome(String s, int left, int right) {

        // Compare characters from both ends.
        while (left < right) {

            // If characters differ,
            // it is not a palindrome.
            if (s.charAt(left) != s.charAt(right))
                return false;

            // Move towards the center.
            left++;
            right--;
        }

        // All characters matched.
        return true;
    }
}

Approach
Observation

A palindrome reads the same from both ends.

If we encounter a mismatch, we are allowed to delete at most one character.

At the first mismatch:

s[left] != s[right]

There are only two choices:

Delete s[left]
Delete s[right]

If any one of these choices makes the remaining substring a palindrome, the answer is true.

Dry Run 1
Input
s = "abca"

Initial:

a b c a
L     R

a == a

Move pointers:

a b c a
  L R

b != c

Now try both possibilities.

Case 1: Delete b

Check:

a b c a
    L R

Substring = "c"

Palindrome ✅

Case 2: Delete c

Check:

a b c a
  L
  R

Substring = "b"

Palindrome ✅

Since one of them is a palindrome:

Return true
Dry Run 2
Input
s = "abc"

Initial:

a b c
L   R

a != c

Try deleting a:

b c
L R

Not palindrome ❌

Try deleting c:

a b
L R

Not palindrome ❌

Therefore:

Return false
Dry Run 3
Input
s = "deeee"

Initial:

d e e e e
L       R

d != e

Delete d:

e e e e
L     R

Palindrome ✅

Therefore:

Return true
Time Complexity
Traversing the string: O(n)
At most one extra palindrome check: O(n)

Overall:

O(n)
Space Complexity
O(1)

because only a few variables (left, right) are used.