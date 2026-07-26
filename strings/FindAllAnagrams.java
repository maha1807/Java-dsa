class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        // List to store all the starting indices where an anagram of p is found.
        List<Integer> ans = new ArrayList<>();

        // If p is longer than s, it is impossible for s to contain any anagram of p.
        if (p.length() > s.length()) {
            return ans;
        }

        // Frequency array for string p.
        // freqP[i] stores how many times the character ('a' + i) appears in p.
        int[] freqP = new int[26];

        // Frequency array for the current sliding window in s.
        int[] window = new int[26];

        // -----------------------------
        // Step 1: Build frequency array for p
        // -----------------------------
        for (int i = 0; i < p.length(); i++) {

            // Convert character into index
            // Example:
            // 'a' - 'a' = 0
            // 'b' - 'a' = 1
            // 'c' - 'a' = 2
            freqP[p.charAt(i) - 'a']++;
        }

        // -----------------------------
        // Step 2: Build frequency array for the first window
        // Window size = p.length()
        // -----------------------------
        for (int i = 0; i < p.length(); i++) {

            // Count characters present in the first window of s.
            window[s.charAt(i) - 'a']++;
        }

        // -----------------------------
        // Step 3: Compare the first window with p
        // -----------------------------
        // Arrays.equals() checks whether both frequency arrays are identical.
        // If identical, the first window is an anagram.
        if (Arrays.equals(freqP, window)) {
            ans.add(0);
        }

        // Left pointer of the sliding window.
        int left = 0;

        // -----------------------------
        // Step 4: Slide the window
        // -----------------------------
        // right starts from the next character after the first window.
        for (int right = p.length(); right < s.length(); right++) {

            // Remove the leftmost character because it is leaving the window.
            window[s.charAt(left) - 'a']--;

            // Move the left pointer one step ahead.
            left++;

            // Add the new right character entering the window.
            window[s.charAt(right) - 'a']++;

            // Compare the updated window with p.
            // If frequencies match, then this window is an anagram.
            if (Arrays.equals(freqP, window)) {

                // Store the starting index of this window.
                ans.add(left);
            }
        }

        // Return all starting indices.
        return ans;
    }
}

Approach
Step 1

Create two frequency arrays.

freqP   -> Stores frequency of characters in p

window  -> Stores frequency of current window in s

Example

p = "abc"

freqP

a=1
b=1
c=1
Step 2

Build the first window whose size is equal to p.length().

s = "cbaebabacd"

First window = "cba"

Window frequency

a=1
b=1
c=1

Compare

window == freqP

Yes

Answer

0
Step 3

Slide the window one character at a time.

Instead of rebuilding the frequency array every time,

we simply

Remove left character

Add right character

This takes only O(1) time.

Step 4

After every slide

Compare

window

with

freqP

If equal

Store the starting index.
Dry Run
Input
s = "abab"

p = "ab"

Window size

2
Build freqP
a=1
b=1
First Window
ab

window

a=1
b=1

Compare

Equal

Answer

[0]
Slide Once

Current

ab

Remove left

Remove 'a'

a=0
b=1

Move left

left=1

Add right

Add 'a'

a=1
b=1

Window

ba

Compare

Equal

Answer

[0,1]
Slide Again

Current

ba

Remove left

Remove 'b'

a=1
b=0

Move left

left=2

Add right

Add 'b'

a=1
b=1

Window

ab

Compare

Equal

Answer

[0,1,2]
Sliding Window Visualization
s = a b a b
    |---|

Window = ab

↓

Remove a
Add a

s = a b a b
      |---|

Window = ba

↓

Remove b
Add b

s = a b a b
        |---|

Window = ab
Time Compl