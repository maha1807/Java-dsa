class Solution {

    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // Target found
            if (nums[mid] == target)
                return mid;

            // Left half is sorted
            if (nums[left] <= nums[mid]) {

                // Target lies inside left half
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                }

                // Target lies in right half
                else {
                    left = mid + 1;
                }
            }

            // Right half is sorted
            else {

                // Target lies inside right half
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                }

                // Target lies in left half
                else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}



Approach

The array is rotated, so it is not completely sorted.

Example:

Original Array:
0 1 2 4 5 6 7

Rotated:
4 5 6 7 0 1 2

Notice that although the whole array is not sorted, one half is always sorted.

Every iteration:

Find the middle element.
Check if it is the target.
Determine which half is sorted.
Check whether the target lies inside the sorted half.
Eliminate the other half.

Because we eliminate half of the array every time, the complexity remains O(log n).




Dry Run
Example
nums = [4,5,6,7,0,1,2]

target = 0
Initial State
left = 0

right = 6

Array

Index

0 1 2 3 4 5 6

Value

4 5 6 7 0 1 2
Iteration 1

Find middle

mid = (0+6)/2

mid =3

Array

4 5 6 7 0 1 2
L     M     R

Middle value

nums[mid]=7

Target?

7==0

No

Check

nums[left] <= nums[mid]
4<=7

YES

Left half is sorted.

Now ask:

Is target inside

4 5 6 7

Check

target >= nums[left]

0>=4

False

Target is NOT inside left half.

Search right.

left = mid+1

left =4

Now

left =4

right =6
Iteration 2
mid =(4+6)/2

mid=5

Array

0 1 2

L M R

Middle value

1

Target?

1==0

No

Check

nums[left]<=nums[mid]
0<=1

YES

Left half is sorted.

Now ask

Is target inside?

target>=0

YES

target<1

YES

So search left.

right=mid-1

right=4
Iteration 3

Now

left=4

right=4

Middle

mid=4

Array

0

L
M
R

Target?

nums[mid]=0

YES

Return

4

Correct.

Why do we check nums[left] <= nums[mid]?

Suppose

4 5 6 7 0 1 2
L     M

Since

4 <= 7

everything from left to mid is in increasing order.

So the left half is sorted.

Suppose

6 7 0 1 2 4 5
L   M

Now

6 <=0

False

The left half crosses the rotation point, so it cannot be sorted.

Therefore, the right half must be sorted.

Why do we use
target >= nums[left] && target < nums[mid]

Example:

4 5 6 7

Target = 6

Is 6 inside this sorted half?

6>=4

YES

6<7

YES

So we search left.

Example

4 5 6 7

Target = 1
1>=4

NO

So target cannot be in the sorted left half.

We immediately search the right half.

Complexity
Time Complexity: O(log n) because each iteration discards half of the search space.
Space Complexity: O(1) because only a few variables (left, right, mid) are used.
Interview Tip

For every rotated sorted array problem, remember this simple checklist:

Find mid.
If nums[mid] == target, return it.
Check which half is sorted using nums[left] <= nums[mid].
If the target lies within the sorted half, search there.
Otherwise, search the other half.

This pattern is reused in many binary search interview questions involving rotated arrays.