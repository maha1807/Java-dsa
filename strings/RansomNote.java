import java.util.HashMap;

class Solution {

    public boolean canConstruct(String ransomNote, String magazine) {

        // Create a HashMap to store the frequency of each character
        // present in the magazine.
        // Key   -> Character
        // Value -> Number of times the character appears
        HashMap<Character, Integer> map = new HashMap<>();

        // Traverse the magazine string
        for (int i = 0; i < magazine.length(); i++) {

            // Get the current character
            char ch = magazine.charAt(i);

            // Store/update its frequency in the HashMap.
            //
            // getOrDefault(ch,0)
            // If the character already exists,
            // return its current frequency.
            //
            // Otherwise return 0.
            //
            // Example:
            // magazine = "aab"
            //
            // First 'a'
            // map = {a=1}
            //
            // Second 'a'
            // map = {a=2}
            //
            // 'b'
            // map = {a=2,b=1}
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Traverse every character of ransomNote
        for (int i = 0; i < ransomNote.length(); i++) {

            // Current character needed
            char ch = ransomNote.charAt(i);

            // If the character does not exist
            // OR
            // its frequency has become zero,
            // then magazine doesn't have enough letters.
            if (!map.containsKey(ch) || map.get(ch) == 0) {
                return false;
            }

            // One occurrence of this character is used.
            // Therefore decrease its frequency by one.
            //
            // Example:
            // Before:
            // a -> 2
            //
            // After using one 'a':
            // a -> 1
            map.put(ch, map.get(ch) - 1);
        }

        // If all characters were found,
        // ransomNote can be constructed.
        return true;
    }
}

Approach
Step 1

Count the frequency of every character in magazine.

Example

magazine = "aab"

HashMap becomes

Character	Frequency
a	2
b	1
Step 2

Traverse the ransomNote.

For every character:

Check whether it exists.
If it doesn't exist → return false.
If frequency is zero → return false.
Otherwise decrease the frequency by one.
Step 3

If every character is successfully used,

return

true
Logic

Think of the HashMap as a box of available letters.

Initially

Magazine

a a b

HashMap

a → 2
b → 1

Now build the ransom note.

Need

a

Use one.

a → 1

Need another

a

Use one.

a → 0

Need another

a

Not available.

Return

false
Dry Run
Input
ransomNote = "aa"

magazine = "aab"
Step 1: Build the HashMap
Iteration	Character	Action	HashMap
1	a	Add frequency	{a=1}
2	a	Increase frequency	{a=2}
3	b	Add frequency	{a=2, b=1}
Step 2: Traverse ransomNote
Iteration	Needed Character	Frequency Before	Frequency After	Result
1	a	2	1	Continue
2	a	1	0	Continue

Loop finishes.

Return

true
Another Dry Run
Input
ransomNote = "aa"

magazine = "ab"
Build HashMap
Character	Frequency
a	1
b	1
Traverse ransomNote
Iteration	Needed Character	Frequency Before	Frequency After	Result
1	a	1	0	Continue
2	a	0	Not Possible	Return false
Visualization
Magazine
-------------------
a   a   b
↓   ↓   ↓

HashMap

a → 2
b → 1

Need first a

a → 1
b → 1

Need second a

a → 0
b → 1

Finished ✔

Return true

Time Complexity

Building the HashMap:

O(m)

where m = length of magazine.

Traversing the ransom note:

O(n)

where n = length of ransomNote.

Overall:

Time Complexity = O(m + n)
Space Complexity

In the worst case, the HashMap stores one entry for each distinct character.

Space Complexity = O(k)

where k is the number of distinct characters (at most 26 for lowercase English letters).