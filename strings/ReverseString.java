class Solution {

    // Function to reverse the given character array in-place.
    // "In-place" means we modify the original array without using extra space.
    public void reverseString(char[] s) {

        // 'start' points to the first character of the array.
        int start = 0;

        // 'end' points to the last character of the array.
        int end = s.length - 1;

        // Continue swapping until both pointers meet or cross each other.
        while (start < end) {

            // Store the character at the start position
            // because it will be overwritten.
            char temp = s[start];

            // Copy the character from the end position
            // to the start position.
            s[start] = s[end];

            // Place the original start character
            // (stored in temp) at the end position.
            s[end] = temp;

            // Move the start pointer one step to the right.
            start++;

            // Move the end pointer one step to the left.
            end--;
        }
    }
}

Logic Behind the Approach

The idea is to use the Two Pointer Technique.

One pointer (start) begins at the first character.
Another pointer (end) begins at the last character.
Swap the characters at these two positions.
Move both pointers toward the center.
Stop when the pointers meet or cross.

Since every swap places two characters in their correct reversed positions, we only need to process half of the array.

Dry Run
Input
s = ['h', 'e', 'l', 'l', 'o']

Initial:

Index : 0   1   2   3   4
Array : h   e   l   l   o
        ↑               ↑
      start           end
Iteration 1

Swap:

h ↔ o

Array becomes:

o   e   l   l   h
↑               ↑
start         end

Move pointers:

start = 1
end = 3
Iteration 2

Swap:

e ↔ l

Array becomes:

o   l   l   e   h
    ↑       ↑
  start    end

Move pointers:

start = 2
end = 2
Loop Ends

Condition:

start < end

becomes

2 < 2 → false

So the loop stops.

Final Output
['o', 'l', 'l', 'e', 'h']
Why while (start < end)?

Suppose the array has 5 elements:

h e l l o

After swapping:

First ↔ Last
Second ↔ Second Last

The middle character (l) is already in its correct position, so there is no need to swap it.

If the array has an even number of elements:

a b c d

After two swaps:

d c b a

The pointers cross, meaning all elements have been reversed.

Time Complexity
Each iteration swaps one pair of characters.
We process approximately n/2 pairs.

Overall:

O(n)
Space Complexity

Only one extra variable (temp) is used for swapping.

Overall:

O(1)