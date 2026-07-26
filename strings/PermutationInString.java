// # LeetCode 567 – Permutation in String

// ## Approach

// This problem is solved using the **Fixed Sliding Window** technique along with **Frequency Arrays**.

// ### Key Observation

// A permutation (or an anagram) of `s1` must:

// * Have the same length as `s1`.
// * Contain exactly the same characters with the same frequencies.

// Instead of sorting every substring of `s2`, which would be expensive, we compare the **frequency of characters**.

// ---

// ## Steps

// 1. Create a frequency array for `s1`.
// 2. Create another frequency array for the first window of `s2`.
// 3. Compare both frequency arrays.
// 4. If they match, return `true`.
// 5. Otherwise, slide the window one character at a time:

//    * Remove the left character.
//    * Add the new right character.
//    * Compare the frequency arrays again.
// 6. If any window matches, return `true`; otherwise, return `false`.

// ---

// # Code with Detailed Comments

// ```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {

        // Frequency array for s1.
        // freqs1[i] stores the number of times ('a' + i) appears in s1.
        int[] freqs1 = new int[26];

        // Frequency array for the current sliding window in s2.
        int[] window = new int[26];

        // If s1 is null or longer than s2,
        // no permutation can exist.
        if (s1 == null || s1.length() > s2.length()) {
            return false;
        }

        // -----------------------------
        // Step 1: Build frequency array for s1
        // -----------------------------
        for (int i = 0; i < s1.length(); i++) {

            // Convert character to array index.
            // 'a' -> 0
            // 'b' -> 1
            // ...
            freqs1[s1.charAt(i) - 'a']++;
        }

        // -----------------------------
        // Step 2: Build frequency array for
        // the first window of s2.
        // Window size = s1.length()
        // -----------------------------
        for (int i = 0; i < s1.length(); i++) {

            // Count characters in the first window.
            window[s2.charAt(i) - 'a']++;
        }

        // -----------------------------
        // Step 3:
        // Compare the first window with s1.
        // Arrays.equals() compares all 26 frequencies.
        // -----------------------------
        if (Arrays.equals(freqs1, window)) {

            // First window itself is a permutation.
            return true;
        }

        // Left pointer of the sliding window.
        int left = 0;

        // -----------------------------
        // Step 4:
        // Slide the window one character at a time.
        // -----------------------------
        for (int right = s1.length(); right < s2.length(); right++) {

            // Remove the leftmost character
            // because it is leaving the window.
            window[s2.charAt(left) - 'a']--;

            // Move the left pointer.
            left++;

            // Add the new right character
            // entering the window.
            window[s2.charAt(right) - 'a']++;

            // Compare the updated window
            // with s1.
            if (Arrays.equals(freqs1, window)) {

                // A permutation is found.
                return true;
            }
        }

        // No permutation exists.
        return false;
    }
}
// ```

// ---

// # Dry Run

// ### Input

// ```text
// s1 = "ab"

// s2 = "eidbaooo"
// ```

// Window size =

// ```text
// 2
// ```

// ---

// ## Step 1

// Build frequency of `s1`

// ```text
// a = 1
// b = 1
// ```

// Frequency Array

// ```text
// a b c d e ...

// 1 1 0 0 0 ...
// ```

// ---

// ## Step 2

// Build first window

// ```text
// ei
// ```

// Window frequency

// ```text
// e = 1
// i = 1
// ```

// Compare

// ```text
// freqs1

// a=1
// b=1

// window

// e=1
// i=1
// ```

// Not Equal

// ---

// ## Slide 1

// Current Window

// ```text
// ei
// ```

// Remove

// ```text
// e
// ```

// Add

// ```text
// d
// ```

// New Window

// ```text
// id
// ```

// Compare

// ```text
// Not Equal
// ```

// ---

// ## Slide 2

// Current Window

// ```text
// id
// ```

// Remove

// ```text
// i
// ```

// Add

// ```text
// b
// ```

// Window

// ```text
// db
// ```

// Frequency

// ```text
// d=1
// b=1
// ```

// Compare

// ```text
// Not Equal
// ```

// ---

// ## Slide 3

// Current Window

// ```text
// db
// ```

// Remove

// ```text
// d
// ```

// Add

// ```text
// a
// ```

// Window

// ```text
// ba
// ```

// Frequency

// ```text
// a=1
// b=1
// ```

// Compare

// ```text
// freqs1

// a=1
// b=1

// window

// a=1
// b=1
// ```

// Equal

// Return

// ```text
// true
// ```

// ---

// # Sliding Window Visualization

// ```text
// s2 = e i d b a o o o
//      |---|

// Window = ei

// ↓

// Remove e
// Add d

// s2 = e i d b a o o o
//        |---|

// Window = id

// ↓

// Remove i
// Add b

// s2 = e i d b a o o o
//          |---|

// Window = db

// ↓

// Remove d
// Add a

// s2 = e i d b a o o o
//            |---|

// Window = ba

// Permutation Found ✓
// ```

// ---

// # Time Complexity

// Building frequency arrays

// ```text
// O(k)
// ```

// Sliding the window

// ```text
// O(n)
// ```

// Comparing two arrays

// ```text
// O(26) = O(1)
// ```

// Overall

// ```text
// O(n)
// ```

// ---

// # Space Complexity

// Two arrays of size 26

// ```text
// O(26 + 26)

// = O(1)
// ```

// ---

// # Pattern to Remember

// Whenever a problem says:

// * Permutation
// * Anagram
// * Same characters
// * Same frequency
// * Fixed-length substring

// Think of:

// ```text
// Fixed Sliding Window
//         +
// Frequency Array
//         +
// Compare Frequency Arrays
// ```
