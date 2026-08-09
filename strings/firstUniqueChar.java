import java.util.HashMap;

class Solution {

    public int firstUniqChar(String s) {

        // Create a HashMap to store the frequency of each character.
        // Key   -> Character
        // Value -> Number of times the character appears
        HashMap<Character, Integer> map = new HashMap<>();

        // ---------------------------
        // First Pass: Count Frequency
        // ---------------------------
        for (int i = 0; i < s.length(); i++) {

            // Get the current character
            char ch = s.charAt(i);

            // Store the frequency of the character.
            //
            // getOrDefault(ch,0)
            // If the character already exists in the HashMap,
            // return its current frequency.
            //
            // Otherwise return 0.
            //
            // Example:
            // "loveleetcode"
            //
            // First 'l'
            // map = {l=1}
            //
            // Second 'l'
            // map = {l=2}
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // ------------------------------------
        // Second Pass: Find the First Unique Character
        // ------------------------------------
        for (int i = 0; i < s.length(); i++) {

            // Get the current character
            char ch = s.charAt(i);

            // If the frequency of this character is exactly 1,
            // then this is the first unique character.
            if (map.get(ch) == 1) {
                return i;
            }
        }

        // If no unique character exists,
        // return -1.
        return -1;
    }
}

Approach

The solution uses two passes over the string.

Step 1: Count the frequency

Traverse the string once and store how many times each character appears using a HashMap.

Example:

s = "loveleetcode"

HashMap becomes

Character	Frequency
l	2
o	2
v	1
e	4
t	1
c	1
d	1
Step 2: Find the first unique character

Traverse the string again.

For every character:

Check its frequency in the HashMap.
If the frequency is 1, return its index immediately.
Logic

Think of the HashMap as a frequency counter.

String
loveleetcode

After the first traversal

l → 2
o → 2
v → 1
e → 4
t → 1
c → 1
d → 1

Now check each character one by one.

l → appears twice ❌

o → appears twice ❌

v → appears once ✔

Return its index

2
Why Do We Traverse Twice?

Suppose the string is

leetcode

At index 0

l

Can you immediately say it is unique?

No, because you haven't seen the rest of the string yet.

Maybe another 'l' appears later.

That's why we first count the frequency of every character.

Only after knowing all frequencies can we correctly identify the first unique character.

Dry Run
Input
s = "loveleetcode"
Pass 1: Build the HashMap
Dry Run Table
Iteration	Character	Action	HashMap
0	l	Add	{l=1}
1	o	Add	{l=1, o=1}
2	v	Add	{l=1, o=1, v=1}
3	e	Add	{l=1, o=1, v=1, e=1}
4	l	Increase frequency	{l=2, o=1, v=1, e=1}
5	e	Increase frequency	{l=2, o=1, v=1, e=2}
6	e	Increase frequency	{l=2, o=1, v=1, e=3}
7	t	Add	{l=2, o=1, v=1, e=3, t=1}
8	c	Add	{l=2, o=1, v=1, e=3, t=1, c=1}
9	o	Increase frequency	{l=2, o=2, v=1, e=3, t=1, c=1}
10	d	Add	{l=2, o=2, v=1, e=3, t=1, c=1, d=1}
11	e	Increase frequency	{l=2, o=2, v=1, e=4, t=1, c=1, d=1}

Final HashMap

l → 2
o → 2
v → 1
e → 4
t → 1
c → 1
d → 1
Pass 2: Find the First Unique Character
Dry Run Table
Index	Character	Frequency	Result
0	l	2	Skip
1	o	2	Skip
2	v	1	Return 2

Output

2
Visualization
String

l  o  v  e  l  e  e  t  c  o  d  e
0  1  2  3  4  5  6  7  8  9 10 11

↓

Build HashMap

l → 2
o → 2
v → 1
e → 4
t → 1
c → 1
d → 1

↓

Traverse Again

Index 0 : l → Frequency = 2 ❌

Index 1 : o → Frequency = 2 ❌

Index 2 : v → Frequency = 1 ✅

Return 2
Time Complexity
First traversal (count frequency)
O(n)
Second traversal (find first unique)
O(n)
Overall
Time Complexity = O(n)
Space Complexity

The HashMap stores the frequency of each distinct character.

Space Complexity = O(k)

where k is the number of distinct characters.

For lowercase English letters, k ≤ 26.