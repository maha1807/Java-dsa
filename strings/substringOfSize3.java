class Solution {
    public int countGoodSubstrings(String s) {

        // If the string has fewer than 3 characters,
        // it is impossible to form a substring of length 3.
        if (s.length() < 3)
            return 0;

        // Frequency array for the current window.
        // window[i] stores how many times ('a' + i)
        // appears in the current window.
        int[] window = new int[26];

        // Stores the number of distinct characters
        // present in the current window.
        int distinct = 0;

        // Stores the total number of good substrings.
        int count = 0;

        // -----------------------------
        // Build the first window
        // -----------------------------
        for (int i = 0; i < 3; i++) {

            // If this character is appearing
            // for the first time in the window,
            // increase the distinct count.
            if (window[s.charAt(i) - 'a'] == 0)
                distinct++;

            // Increase the frequency of this character.
            window[s.charAt(i) - 'a']++;
        }

        // If all three characters are distinct,
        // then the first window is a good substring.
        if (distinct == 3)
            count++;

        // Left pointer of the sliding window.
        int left = 0;

        // -----------------------------
        // Slide the window
        // -----------------------------
        for (int right = 3; right < s.length(); right++) {

            // Remove the leftmost character
            // because it is leaving the window.
            window[s.charAt(left) - 'a']--;

            // If its frequency becomes zero,
            // it is no longer present in the window.
            if (window[s.charAt(left) - 'a'] == 0)
                distinct--;

            // Move the left pointer.
            left++;

            // If the new right character
            // is not already present,
            // a new distinct character enters.
            if (window[s.charAt(right) - 'a'] == 0)
                distinct++;

            // Add the right character.
            window[s.charAt(right) - 'a']++;

            // If the window contains exactly
            // three distinct characters,
            // count this substring.
            if (distinct == 3)
                count++;
        }

        // Return total number of good substrings.
        return count;
    }
}


Dry Run
Input
s = "xyzzaz"

Window size

3
Initial Window

Window

xyz

Frequency

x = 1
y = 1
z = 1

Distinct

3

Good substring

count = 1
Slide 1

Current Window

xyz

Remove

x

Frequency

x = 0

Since frequency became zero

distinct = 2

Add

z

Frequency

z = 2

Since z was already present,

distinct remains 2

New Window

yzz

Distinct

2

Not good.

Slide 2

Current Window

yzz

Remove

y

Frequency

y = 0

Distinct

1

Add

a

Frequency

a = 1

Distinct

2

Window

zza

Not good.

Slide 3

Current Window

zza

Remove

z

Frequency

z = 1

Notice:

z is still present, so

distinct remains 2

Add

z

Frequency

z = 2

Distinct

2

Window

zaz

Not good.

Final Answer
count = 1
Why do we use distinct?

Suppose the window is

abc

Frequency

a = 1
b = 1
c = 1

Distinct

3

All characters are different.

Suppose the window is

aab

Frequency

a = 2
b = 1

Distinct

2

Not all characters are different.

Suppose the window is

aaa

Frequency

a = 3

Distinct

1

Definitely not a good substring.

Why check window[ch] == 0 before incrementing?

Suppose the window is

ab

Frequency

a = 1
b = 1

Now add another a.

Before adding:

window['a'] = 1

Since it is not zero, a is already in the window.

So we do not increase distinct.

Frequency becomes

a = 2

Distinct remains

2
Why check window[ch] == 0 after decrementing?

Suppose the window is

abb

Frequency

a = 1
b = 2

Remove a.

Frequency becomes

a = 0

Since a has completely disappeared from the window,

distinct--

Now

distinct = 1
Sliding Window Visualization
String = x y z z a z

Window 1
[ x y z ]   distinct = 3 ✓

↓

Remove x
Add z

[ y z z ]   distinct = 2 ✗

↓

Remove y
Add a

[ z z a ]   distinct = 2 ✗

↓

Remove z
Add z

[ z a z ]   distinct = 2 ✗
Time Complexity
Building the first window: O(3) = O(1)
Sliding the window across the string: O(n)
Each slide performs only constant-time updates.

Overall Time Complexity: O(n)

Space Complexity
Frequency array of size 26: O(26) = O(1)