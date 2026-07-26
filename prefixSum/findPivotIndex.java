class Solution {
    public int pivotIndex(int[] nums) {

        // Stores the sum of all elements in the array.
        // We need this to calculate the right sum efficiently.
        int totalSum = 0;

        // Traverse the array once and calculate the total sum.
        for (int num : nums) {

            // Add the current number to the total sum.
            totalSum += num;
        }

        // Stores the sum of all elements on the LEFT side
        // of the current index.
        int leftSum = 0;

        // Traverse the array again.
        for (int i = 0; i < nums.length; i++) {

            // Calculate the sum on the RIGHT side.
            //
            // totalSum
            // - leftSum
            // - current element
            //
            // = right side sum
            int rightSum = totalSum - leftSum - nums[i];

            // If left and right sums are equal,
            // we have found the pivot index.
            if (leftSum == rightSum) {
                return i;
            }

            // Move the current element to the left side
            // before checking the next index.
            leftSum += nums[i];
        }

        // No pivot index found.
        return -1;
    }
}

Approach Name

Prefix Sum (Running Sum)

Instead of creating a separate prefix array, we use a running left sum and the total sum.

Key Idea

For every index i:

Left Sum = Sum of all elements before i

Right Sum = Sum of all elements after i

If

Left Sum == Right Sum

then i is the pivot index.



Logic Behind the Code

Suppose

nums = [1,7,3,6,5,6]

First calculate

Total Sum

= 1+7+3+6+5+6

= 28

Now imagine standing at every index.

For every position,

Left Side

|

Current Element

|

Right Side

Check whether

Left Sum == Right Sum
Dry Run
Input
nums = [1,7,3,6,5,6]
Step 1

Calculate total sum

1+7+3+6+5+6

=

28

Initially

leftSum = 0
Iteration 1
i = 0

Current = 1

Left

0

Right

28-0-1

=

27
0 == 27 ?

No

Update left

leftSum

=

1
Iteration 2
i = 1

Current = 7

Left

1

Right

28-1-7

=

20
1==20 ?

No

Update

leftSum

=

8
Iteration 3
i = 2

Current = 3

Left

8

Right

28-8-3

=

17
8==17 ?

No

Update

leftSum

=

11
Iteration 4
i = 3

Current = 6

Left

11

Right

28-11-6

=

11
11==11 ?

YES

Return

3
Why do we calculate
int rightSum = totalSum - leftSum - nums[i];

Suppose

nums

[1,7,3,6,5,6]

At

i = 3

We already know

Total Sum

28

Left Sum

1+7+3

=

11

Current Element

6

Remove

Left Sum
Current Element

from Total Sum.

28

-

11

-

6

=

11

Remaining numbers are

5+6

=

11

which is exactly the right side sum.

Why do we update
leftSum += nums[i];

Because after checking index i, we move to the next index.

The current element now becomes part of the left side.

Example

Before

i = 2

Left

1 7

Current

3

After moving to

i = 3

Left becomes

1 7 3

So

leftSum += nums[i];

adds the current element to the left side.

Visualization
nums

1  7  3  6  5  6
         ↑
         i

Left Sum

1+7

=

8

Current

3

Right Sum

6+5+6

=

17

Formula

Right

=

Total

-

Left

-

Current
Time Complexity
First loop

Calculate total sum

O(n)
Second loop

Find pivot index

O(n)

Total

O(n)
Space Complexity

Only two variables are used.

leftSum

totalSum

No extra arrays.

O(1)
Easy Trick to Remember

For every index:

Left Sum = Sum of elements before i

Right Sum = Total Sum - Left Sum - Current Element

If

Left Sum == Right Sum

✅ Return the index.

Otherwise,

leftSum += nums[i];
