class Solution {
    public int totalFruit(int[] fruits) {

        // HashMap stores:
        // Key   -> Fruit type
        // Value -> Frequency of that fruit inside the current window
        HashMap<Integer, Integer> map = new HashMap<>();

        // Left pointer of sliding window
        int l = 0;

        // Right pointer (used in for loop)
        int r = 0;

        // Stores the maximum number of fruits collected
        int ans = 0;

        // Expand the window by moving right pointer
        for (r = 0; r < fruits.length; r++) {

            // Add current fruit into the window
            // If fruit already exists -> increase frequency
            // Otherwise insert with frequency = 1
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);

            // If more than 2 different fruit types are present,
            // shrink the window from the left
            while (map.size() > 2) {

                // Remove one occurrence of the left fruit
                map.put(fruits[l], map.get(fruits[l]) - 1);

                // If frequency becomes 0,
                // completely remove that fruit from HashMap
                if (map.get(fruits[l]) == 0) {
                    map.remove(fruits[l]);
                }

                // Move left pointer forward
                l++;
            }

            // Current window contains at most 2 fruit types
            // Update maximum window size
            ans = Math.max(ans, r - l + 1);
        }

        // Return maximum fruits collected
        return ans;
    }
}


Approach Name

Sliding Window + HashMap (Variable Size Sliding Window)

Intuition

Imagine you're walking through a row of fruit trees.

You have only 2 baskets.

Each basket can hold only one fruit type.

You can collect fruits continuously.

If you encounter a third fruit type, you must throw away fruits from the left side until only two fruit types remain.

The longest such window is the answer.


Logic Step-by-Step
Step 1

Start with an empty window.

[]
Step 2

Move the right pointer.

Whenever a fruit comes,

Add it into the HashMap.

Fruit -> Count

Example

1 arrives

Map

1 → 1
Step 3

Continue expanding.

1 2

Map

1 → 1
2 → 1

Still only 2 fruit types.

Valid.

Step 4

Suppose another fruit arrives.

1 2 3

Map becomes

1 → 1
2 → 1
3 → 1

Now

map.size()==3

Not allowed.

Step 5

Shrink from the left.

Decrease frequency.

If frequency becomes zero,

remove that fruit.

Repeat until only

map.size()==2
Step 6

Calculate

Window Size = r-l+1

Keep the maximum.

Why HashMap?

Because we need to know

How many fruit types exist
Frequency of each fruit

Example

Current Window

2 2 1 1 2

HashMap

2 → 3
1 → 2

When left moves,

we decrease frequencies.

Why r-l+1 ?

Suppose

l = 3
r = 7

Window is

Index

3 4 5 6 7

There are

7-3+1 = 5 elements

Always

Window Size = r-l+1
Dry Run

Input

fruits = [1,2,1,2,3]
Initial
l = 0
r = 0

Map = {}
ans = 0
Iteration 1
r = 0

Fruit = 1

Map

1 → 1

Window

[1]

Size

1

ans

1
Iteration 2
r = 1

Fruit = 2

Map

1 → 1
2 → 1

Window

[1 2]

Valid

2 fruit types

Size

2

ans

2
Iteration 3
r = 2

Fruit = 1

Map

1 → 2
2 → 1

Window

[1 2 1]

Size

3

ans

3
Iteration 4
r = 3

Fruit = 2

Map

1 → 2
2 → 2

Window

[1 2 1 2]

Size

4

ans

4
Iteration 5
r = 4

Fruit = 3

Map

1 → 2
2 → 2
3 → 1

Now

3 fruit types

Invalid.

Shrink.

Shrink 1

Left fruit

1

Decrease

1 → 1

Move

l = 1

Still

1
2
3

Three fruit types.

Continue.

Shrink 2

Left fruit

2

Decrease

2 → 1

Move

l = 2

Still

1
2
3

Continue.

Shrink 3

Left fruit

1

Decrease

1 → 0

Remove

1

Map

2 → 1
3 → 1

Move

l = 3

Now only

2 fruit types

Window

[2 3]

Size

2

ans remains

4
Final Answer
4

The longest valid window is:

[1,2,1,2]
Time Complexity
Each element is added to the window once and removed at most once.
Time = O(n)
Space Complexity
The map stores at most 3 fruit types temporarily (before shrinking), so the auxiliary space is effectively constant.
Space = O(1)

(If generalized to allow k fruit types, the space complexity would be O(k).)