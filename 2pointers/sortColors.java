class Solution {
    public void sortColors(int[] nums) {

        // Length of the array
        int n = nums.length;

        // low -> points to the position where the next 0 should be placed
        int low = 0;

        // mid -> current element that we are processing
        int mid = 0;

        // high -> points to the position where the next 2 should be placed
        int high = n - 1;

        /*
         Dutch National Flag Algorithm

         We divide the array into four parts:

         -----------------------------------------------------
         | 0s | 1s | Unknown Elements | 2s |
         -----------------------------------------------------

         0 .... low-1        -> All are 0s
         low .... mid-1      -> All are 1s
         mid .... high       -> Unknown elements
         high+1 .... n-1     -> All are 2s

         Initially:
         Entire array is unknown.
        */

        // Continue until there are no unknown elements left
        while (mid <= high) {

            // Case 1 : Current element is 0
            if (nums[mid] == 0) {

                /*
                 0 belongs to the left side.

                 Instead of swapping, we simply:

                 1. Copy nums[low] into nums[mid]
                 2. Put 0 at nums[low]

                 This works because we already know that
                 nums[low] is NOT in its correct position.
                */

                nums[mid] = nums[low];
                nums[low] = 0;

                // One more 0 has been placed correctly
                low++;

                // Move to the next unknown element
                mid++;
            }

            // Case 2 : Current element is 1
            else if (nums[mid] == 1) {

                /*
                 1 already belongs in the middle region.

                 Nothing needs to be changed.

                 Simply move to the next element.
                */

                mid++;
            }

            // Case 3 : Current element is 2
            else {

                /*
                 2 belongs at the end.

                 Instead of swapping:

                 1. Copy nums[high] to nums[mid]
                 2. Place 2 at nums[high]

                 Do NOT increment mid because the new value
                 copied from nums[high] has not been checked yet.
                */

                nums[mid] = nums[high];
                nums[high] = 2;

                // One more 2 has been placed correctly
                high--;
            }
        }
    }
}


// Approach
// Step 1

// Use three pointers.

// low
// mid
// high

// Initially

// low = 0
// mid = 0
// high = n-1
// Step 2

// Maintain four regions.

// -------------------------------------------------
// | 0s | 1s | Unknown Elements | 2s |
// -------------------------------------------------

// Initially

// Unknown = Entire array
// Step 3

// Process the unknown region.

// There are only three possibilities.

// If current element is 0

// Move it to the left.

// nums[mid] = nums[low];
// nums[low] = 0;

// low++;
// mid++;
// If current element is 1

// It is already in the correct position.

// mid++;
// If current element is 2

// Move it to the right.

// nums[mid] = nums[high];
// nums[high] = 2;

// high--;

// Notice

// mid is NOT incremented.

// Reason:

// The value copied from high has never been checked.

// Dry Run

// Input

// nums = [2,0,2,1,1,0]

// Initially

// low = 0
// mid = 0
// high = 5
// Step	low	mid	high	Current	Operation	Array
// Initial	0	0	5	2	Start	[2,0,2,1,1,0]
// 1	0	0	5	2	Move 2 to end	[0,0,2,1,1,2]
// 					high--	low=0 mid=0 high=4
// 2	0	0	4	0	Place 0 at front	[0,0,2,1,1,2]
// 					low++ mid++	low=1 mid=1
// 3	1	1	4	0	Place another 0	[0,0,2,1,1,2]
// 					low++ mid++	low=2 mid=2
// 4	2	2	4	2	Move 2 to end	[0,0,1,1,2,2]
// 					high--	low=2 mid=2 high=3
// 5	2	2	3	1	Middle element is already correct	[0,0,1,1,2,2]
// 					mid++	low=2 mid=3
// 6	2	3	3	1	Middle element is already correct	[0,0,1,1,2,2]
// 					mid++	low=2 mid=4

// Now

// mid = 4
// high = 3

// Since

// mid > high

// the loop ends.

// Final Output
// [0,0,1,1,2,2]