class Solution {
    public int maxArea(int[] height) {

        // Left pointer starts from the beginning
        int left = 0;

        // Right pointer starts from the end
        int right = height.length - 1;

        // Stores the maximum area found so far
        int maxArea = 0;

        // Continue until both pointers meet
        while (left < right) {

            // Width between the two lines
            int width = right - left;

            // Height of water is limited by the shorter line
            int minHeight = Math.min(height[left], height[right]);

            // Calculate current container area
            int currentArea = width * minHeight;

            // Update maximum area if current area is larger
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer pointing to the shorter line
            // because only that gives a chance to find a taller line
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        // Return the maximum area found
        return maxArea;
    }
}



Step 1: Understand the Problem

Each number represents the height of a vertical line.

Example:

height = [1,8,6,2,5,4,8,3,7]

Index:
0 1 2 3 4 5 6 7 8

If we choose two lines,

left = 1 (height = 8)
right = 8 (height = 7)

Container looks like

8 |           |
7 |           |
6 |           |
5 |           |
4 |           |
3 |           |
2 |           |
1 |           |
  -----------------
   1         8

Water stored depends on

smaller height
distance between them
Step 2: Formula

Area

Area = Width × Height

Width = right - left

Height = min(height[left], height[right])

Area = (right-left) * min(height[left], height[right])

Example

left = 1
right = 8

width = 8-1 = 7

height = min(8,7)=7

Area = 7 × 7 = 49
Step 3: Brute Force

Check every pair.

for every i
    for every j>i

        area=(j-i)*min(height[i],height[j])

        answer=max(answer,area)

Time

O(n²)

Too slow for large arrays.

Step 4: Think Smarter

Start from the widest possible container.

left = 0
right = n-1

Why?

Because width is maximum.

Now calculate area.

Question is:

Which pointer should move?

This is the entire trick.

Step 5: Why move the smaller height?

Suppose

3            8

|            |
|            |
|            |
-------------

Width = 8

Height = 3

Area

8 × 3 = 24

Now imagine moving the taller line.

3         7

|         |
|         |
|         |
-----------

Width became smaller.

Height is still

min(3,7)=3

Area becomes

7 × 3 = 21

It decreased.

Moving the taller line never increases the minimum height because the shorter line is still limiting the water.

Now instead move the shorter one.

Maybe we find

6          8

|          |
|          |
|          |
|          |
|          |
-------------

Width became smaller

But

Height increased

min(6,8)=6

Area

7 × 6 = 42

Now area has a chance to become larger.

Hence

Always move the pointer with the smaller height.

Step 6: Algorithm
left = 0
right = n-1

while(left<right)

    width = right-left

    area = width * min(height[left],height[right])

    answer = max(answer,area)

    if(height[left]<height[right])
        left++
    else
        right--

That's all.

Step 7: Dry Run

Input

[1,8,6,2,5,4,8,3,7]

Initially

L=0
R=8
Height = min(1,7)=1

Width =8

Area=8

Max=8

Move smaller

1<7

L++

Now

L=1
R=8
Height=min(8,7)=7

Width=7

Area=49

Max=49

Move smaller

7<8

R--

Now

L=1
R=7
Height=min(8,3)=3

Width=6

Area=18

Max=49

Move smaller

3<8

R--

Now

L=1
R=6
Height=min(8,8)=8

Width=5

Area=40

Max=49

Equal heights

Move either

R--

Continue until

left==right

Maximum remains

49