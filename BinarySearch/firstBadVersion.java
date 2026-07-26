/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version);
*/

public class Solution extends VersionControl {

    public int firstBadVersion(int n) {

        // Binary Search Search Space:
        // Versions are numbered from 1 to n.
        // We search within this range to find the first bad version.
        int low = 1;
        int right = n;

        // Stores the current possible first bad version.
        // Every time we find a bad version, we save it here.
        int ans = 0;

        // Continue searching while there are versions left to check.
        while (low <= right) {

            // Find the middle version.
            // Using this formula prevents integer overflow.
            int mid = low + (right - low) / 2;

            // Check whether this version is bad.
            if (isBadVersion(mid)) {

                // mid is a bad version.
                // It could be the first bad version,
                // so save it as a possible answer.
                ans = mid;

                // There might be another bad version before mid.
                // Search the left half.
                right = mid - 1;

            } else {

                // mid is a good version.
                // Therefore, every version before mid is also good.
                // The first bad version must be on the right side.
                low = mid + 1;
            }

        }

        // Return the earliest bad version found.
        return ans;
    }
}


Approach

We use Binary Search because the versions follow this pattern:

Good Good Good Good Bad Bad Bad Bad

or

false false false true true true

There is a single transition point:

false  →  true

Our goal is to find the first true (first bad version).

Logic

At every step:

Case 1
isBadVersion(mid) == false

Example

1 2 3 4 5

mid = 3

3 is good

Since version 3 is good,

1
2
3

are definitely good.

So the answer must be after 3.

Move right.

low = mid + 1;
Case 2
isBadVersion(mid) == true

Example

1 2 3 4 5

mid = 4

4 is bad

Now,

Is 4 the first bad?

Maybe.

Could 3 also be bad?

Maybe.

So we cannot return immediately.

We save

ans = mid;

Then search left.

right = mid - 1;
Dry Run

Suppose

n = 5

Versions

1 2 3 4 5

G G G B B

where

First bad = 4
Initial Values
low = 1
right = 5
ans = 0
Iteration 1
mid = 3

Check

isBadVersion(3)

returns

false

Meaning

1
2
3

are good.

Move right.

low = 4
right = 5
Iteration 2
mid = 4

Check

isBadVersion(4)

returns

true

Store answer.

ans = 4

Search left.

right = 3

Now

low = 4
right = 3

Loop ends.

Return

4

Correct.

Another Dry Run

Suppose

Versions

1 2 3 4 5 6 7

Bad starts at 2

Initial

low = 1
right = 7
Iteration 1
mid = 4

Bad

ans = 4
right = 3
Iteration 2
low = 1
right = 3

mid = 2

Bad

ans = 2
right = 1
Iteration 3
low = 1
right = 1

mid = 1

Good

low = 2

Now

low = 2
right = 1

Stop.

Return

2

Correct.

Time Complexity

Every iteration cuts the search space in half.

Time Complexity = O(log n)
Space Complexity

We only use a few variables.

Space Complexity = O(1)
Interview Trick to Remember

This problem follows the "Find the First True" binary search pattern.

If isBadVersion(mid) is false → search right (low = mid + 1).
If isBadVersion(mid) is true → save mid as a possible answer and search left (right = mid - 1).

This same pattern is used in many binary search interview problems where you need to find the first occurrence or leftmost valid value.