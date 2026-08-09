class Solution {

    public boolean isAnagram(String s, String t) {

        // If the lengths are different,
        // they can never be anagrams.
        if (s.length() != t.length()) {
            return false;
        }

        // Frequency array of size 26.
        // Each index represents one lowercase English letter.
        //
        // Index:
        // 0 -> a
        // 1 -> b
        // ...
        // 25 -> z
        int[] freq = new int[26];

        // Traverse both strings simultaneously.
        for (int i = 0; i < s.length(); i++) {

            // Increase the frequency of the current character
            // from string s.
            //
            // Example:
            // s = "eat"
            //
            // e -> freq[4]++
            freq[s.charAt(i) - 'a']++;

            // Decrease the frequency of the current character
            // from string t.
            //
            // Example:
            // t = "tea"
            //
            // t -> freq[19]--
            freq[t.charAt(i) - 'a']--;
        }

        // After processing both strings,
        // every frequency should become zero.
        //
        // If any value is not zero,
        // then the strings are not anagrams.
        for (int num : freq) {

            if (num != 0) {
                return false;
            }
        }

        // All frequencies became zero.
        // Therefore both strings contain
        // exactly the same characters.
        return true;
    }
}
Approach

Instead of sorting the strings (O(n log n)), we use frequency counting.

We maintain an array of size 26 because there are only 26 lowercase English letters.

Step 1

Increase the count for every character in s.

Step 2

Decrease the count for every character in t.

Step 3

If every frequency becomes 0, both strings contain exactly the same letters.

Otherwise,

they are not anagrams.

Logic

Suppose

s = "eat"

t = "tea"

Increase frequencies using s

e -> +1
a -> +1
t -> +1

Now decrease using t

t -> -1
e -> -1
a -> -1

Everything becomes

0

Therefore

true
Why charAt(i) - 'a'?

Characters have ASCII values.

Character	ASCII
a	97
b	98
c	99

Suppose

'a' - 'a'

becomes

97 - 97 = 0

Similarly,

'b' - 'a'

becomes

98 - 97 = 1
'c' - 'a'

becomes

99 - 97 = 2

Therefore,

Character	Index
a	0
b	1
c	2
d	3
...	...
z	25

This lets us store frequencies in an array.

Dry Run

Input

s = "anagram"

t = "nagaram"

Initially

freq

[a...z]

All values = 0
Iteration-wise Table
i	s[i]	Action	t[i]	Action	Non-zero frequencies after iteration
0	a	a +1	n	n -1	a=1, n=-1
1	n	n +1	a	a -1	all back to 0
2	a	a +1	g	g -1	a=1, g=-1
3	g	g +1	a	a -1	all back to 0
4	r	r +1	r	r -1	all 0
5	a	a +1	a	a -1	all 0
6	m	m +1	m	m -1	all 0

Final frequency array

Character	Frequency
a	0
b	0
c	0
...	...
m	0
n	0
r	0
z	0

Since every value is zero,

Return

true
Another Dry Run

Input

s = "rat"

t = "car"

Frequency updates

i	s[i]	+	t[i]	-	Non-zero frequencies
0	r	+1	c	-1	r=1, c=-1
1	a	+1	a	-1	r=1, c=-1
2	t	+1	r	-1	t=1, c=-1

Final frequencies

Character	Frequency
c	-1
t	+1

Since some frequencies are not zero, return

false
Visualization
String 1

eat

e → +1
a → +1
t → +1

Frequency

a = 1
e = 1
t = 1
String 2
tea

t → -1
e → -1
a → -1

Frequency becomes

a = 0
e = 0
t = 0

Everything cancels out.

Answer

true
Why Do We Check for Zero?

Imagine the frequency array as a balance sheet:

Every character in s deposits +1.
Every character in t withdraws -1.

If the two strings contain exactly the same characters with the same counts, every deposit is matched by a withdrawal, so the final balance for every letter is 0.

If any value is positive or negative, one string has more (or fewer) occurrences of that character.

Time Complexity
First loop: O(n)
Second loop over the fixed-size array (26 elements): O(26), which is constant time.

Overall:

Time Complexity = O(n)
Space Complexity

The frequency array always has 26 elements, regardless of the input size.

Space Complexity = O(1)

