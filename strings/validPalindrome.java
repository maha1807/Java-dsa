class Solution {
    public boolean isPalindrome(String s) {

        // Pointer starting from the beginning of the string
        int left = 0;

        // Pointer starting from the end of the string
        int right = s.length() - 1;

        // Continue until both pointers meet
        while (left < right) {

            // Character at the left pointer
            char l = s.charAt(left);

            // Character at the right pointer
            char r = s.charAt(right);

            // If left character is not a letter or digit,
            // skip it by moving left pointer forward
            if (!isAlphaNum(l)) {
                left++;
            }

            // If right character is not a letter or digit,
            // skip it by moving right pointer backward
            else if (!isAlphaNum(r)) {
                right--;
            }

            // Both characters are alphanumeric
            else {

                // Convert both characters to lowercase and compare
                // If they are different, it is not a palindrome
                if (Character.toLowerCase(l) !=
                    Character.toLowerCase(r)) {
                    return false;
                }

                // Characters matched, move both pointers
                left++;
                right--;
            }
        }

        // If the loop finishes, the string is a palindrome
        return true;
    }

    // Function to check whether a character is
    // a letter (a-z, A-Z) or a digit (0-9)
    private boolean isAlphaNum(char c) {
        return ((c >= 'a' && c <= 'z') || // lowercase letter
                (c >= 'A' && c <= 'Z') || // uppercase letter
                (c >= '0' && c <= '9'));  // digit
    }
}

Approach (Two Pointers)
Initialize two pointers
left = 0 (start of the string)
right = s.length() - 1 (end of the string)
Ignore non-alphanumeric characters
If s[left] is not a letter or digit, move left++.
If s[right] is not a letter or digit, move right--.
Compare characters

Convert both characters to lowercase using:

Character.toLowerCase()
If they are different, return false.
Move both pointers inward
left++
right--
If all characters match
Return true.
Example
s = "A man, a plan, a canal: Panama"
left -> A                             a <- right
Compare a and a ✔

left -> m                         m <- right
Compare m and m ✔

Skip spaces, commas, and colon.

Eventually, both pointers meet.

So the answer is true.