import java.util.Arrays;

class Solution {

    public String longestCommonPrefix(String[] strs) {

        // StringBuilder is used to efficiently build the common prefix.
        // It is better than using String concatenation inside a loop.
        StringBuilder ans = new StringBuilder();

        // Sort the array in lexicographical (dictionary) order.
        //
        // Example:
        // Before Sorting:
        // interview
        // internet
        // internal
        // interval
        //
        // After Sorting:
        // internal
        // internet
        // interval
        // interview
        Arrays.sort(strs);

        // Store the first string after sorting.
        // This is the lexicographically smallest string.
        String first = strs[0];

        // Store the last string after sorting.
        // This is the lexicographically largest string.
        String last = strs[strs.length - 1];

        // Compare the characters of the first and last strings.
        //
        // We only compare until the length of the shorter string.
        // This prevents StringIndexOutOfBoundsException.
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {

            // If characters are different,
            // the common prefix ends here.
            if (first.charAt(i) != last.charAt(i)) {

                // Convert StringBuilder into String
                // and return the answer.
                return ans.toString();
            }

            // Characters are same.
            // Add the matching character to the answer.
            ans.append(first.charAt(i));
        }

        // If the loop finishes,
        // the entire shorter string is the common prefix.
        return ans.toString();
    }
}


Approach
Step 1

Sort the array lexicographically.

Example

Before Sorting

interview
internet
internal
interval

↓

After Sorting

internal
internet
interval
interview
Step 2

Take

first = internal

last = interview

These two strings are the most different after sorting.

If they share a prefix,

then every string in between also shares that prefix.

Step 3

Compare characters one by one.

If

first[i] == last[i]

append that character.

Otherwise,

stop immediately.

Logic

Why do we compare only the first and last strings?

Suppose

apple
application
apply

After sorting

apple
application
apply

The first and last strings are the farthest apart in dictionary order.

If they both start with

app

then every string between them must also start with

app

So comparing only the first and last strings is enough.

Dry Run
Input
["interview",
 "internet",
 "internal",
 "interval"]
Step 1

After sorting

Index	String
0	internal
1	internet
2	interval
3	interview
Step 2
first = internal

last = interview
Step 3

Compare characters.

Dry Run Table
Iteration (i)	first.charAt(i)	last.charAt(i)	Match?	ans
0	i	i	✔	i
1	n	n	✔	in
2	t	t	✔	int
3	e	e	✔	inte
4	r	r	✔	inter
5	n	v	❌	Return "inter"

Final Answer

inter
Another Example

Input

["flower","flow","flight"]

After sorting

flight
flow
flower

Compare

Dry Run Table
i	First	Last	Match	ans
0	f	f	✔	f
1	l	l	✔	fl
2	i	o	❌	Return "fl"

Answer

fl
Visualization

Before Sorting

interview
internet
internal
interval

↓

After Sorting

internal
internet
interval
interview

↓

Compare

internal
|||||X
interview

Matching characters

inter

Return

inter
Why Math.min(first.length(), last.length())?

Suppose

first = car

last = carriage

The shorter string has only 3 characters.

If we try to compare beyond index 2, we'll access characters that don't exist in "car".

Using

Math.min(first.length(), last.length())

ensures we only compare up to the length of the shorter string.

Time Complexity

Sorting the array:

O(n log n)

where n is the number of strings.

Comparing the first and last strings:

O(m)

where m is the length of the shorter string.

Overall:

Time Complexity = O(n log n + m)
Space Complexity

The algorithm uses only a few extra variables and a StringBuilder for the output.

Space Complexity = O(1)

(If you count the returned prefix itself, it is O(m), where m is the length of the common prefix.)