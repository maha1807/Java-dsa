class Solution {
    public int trap(int[] height) {

        // Left pointer starts from the first building
        int left = 0;

        // Right pointer starts from the last building
        int right = height.length - 1;

        // Stores the total amount of trapped rainwater
        int water = 0;

        // Highest wall seen so far from the left
        int leftmax = height[left];

        // Highest wall seen so far from the right
        int rightmax = height[right];

        // Continue until both pointers meet
        while (left < right) {

            // If the left maximum wall is smaller,
            // then the left side determines how much water can be trapped.
            if (leftmax < rightmax) {

                // Move the left pointer one step forward
                left++;

                // Update the highest wall seen from the left
                leftmax = Math.max(leftmax, height[left]);

                // Water trapped at the current position
                // = left maximum wall - current building height
                water += leftmax - height[left];
            }

            // Otherwise, the right side determines the trapped water.
            else {

                // Move the right pointer one step backward
                right--;

                // Update the highest wall seen from the right
                rightmax = Math.max(rightmax, height[right]);

                // Water trapped at the current position
                // = right maximum wall - current building height
                water += rightmax - height[right];
            }
        }

        // Return the total trapped water
        return water;
    }
}


Approach (Two Pointers)
Intuition

For every building, the water trapped depends on:

Water = min(Left Maximum Height, Right Maximum Height) - Current Height

Instead of creating two extra arrays (leftMax[] and rightMax[]), we can maintain:

leftMax → tallest wall seen from the left.
rightMax → tallest wall seen from the right.

We use two pointers:

left starts from the beginning.
right starts from the end.

At every step:

If leftMax < rightMax
The left side is the limiting boundary.
So, calculate water on the left and move left.
Otherwise
The right side is the limiting boundary.
So, calculate water on the right and move right.

This allows us to solve the problem in O(n) time using O(1) extra space.



Dry Run
Input
height = [4,2,0,3,2,5]

Initially

Variable	Value
left	0
right	5
leftMax	4
rightMax	5
water	0
Iteration 1
leftMax = 4
rightMax = 5

4 < 5

Move left.

left = 1

leftMax = max(4,2)=4

Water += 4-2 = 2

Current

water = 2
Iteration 2
left = 1

right = 5

leftMax = 4

rightMax = 5

Again

4 < 5

Move left.

left = 2

leftMax=max(4,0)=4

Water+=4-0=4

Current

water=6
Iteration 3

Move left.

left=3

leftMax=max(4,3)=4

Water+=4-3=1

Current

water=7
Iteration 4

Move left.

left=4

leftMax=max(4,2)=4

Water+=4-2=2

Current

water=9
Iteration 5

Move left.

left=5

leftMax=max(4,5)=5

Water+=5-5=0

Current

water=9

Now

left == right

Loop ends.

Return

9
Pointer Movement Visualization
Initial

L                       R
↓                       ↓
4   2   0   3   2   5

leftMax = 4
rightMax = 5

Move Left

    L                   R
    ↓                   ↓
4   2   0   3   2   5

Water = 2

Move Left

        L               R
        ↓               ↓
4   2   0   3   2   5

Water = 6

Move Left

            L           R
            ↓           ↓
4   2   0   3   2   5

Water = 7

Move Left

                L       R
                ↓       ↓
4   2   0   3   2   5

Water = 9

Move Left

                    L,R
                     ↓
4   2   0   3   2   5

Finished.

Complexity Analysis
Time Complexity: O(n)
Each pointer moves at most n times.
Space Complexity: O(1)
Only a few variables are used.