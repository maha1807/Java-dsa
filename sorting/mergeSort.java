class Solution {

    public int[] sortArray(int[] nums) {

        // Start Merge Sort on the entire array.
        // left = 0  -> first index
        // right = nums.length - 1 -> last index
        mergeSort(nums, 0, nums.length - 1);

        // Return the sorted array.
        return nums;
    }

    private void mergeSort(int[] arr, int left, int right) {

        // ---------------- BASE CASE ----------------
        // If the subarray has only one element (or no elements),
        // it is already sorted.
        //
        // Example:
        // [8] or []
        //
        // This condition stops the recursion.
        if (left >= right)
            return;

        // ---------------- DIVIDE ----------------
        // Find the middle index to split the array.
        //
        // We use:
        // left + (right - left)/2
        //
        // instead of:
        // (left + right)/2
        //
        // because it avoids integer overflow.
        int mid = left + (right - left) / 2;

        // ---------------- RECURSE ON LEFT HALF ----------------
        // Sort the left subarray:
        //
        // Example:
        // [8,3,4]
        //
        // left = 0
        // mid  = 2
        mergeSort(arr, left, mid);

        // ---------------- RECURSE ON RIGHT HALF ----------------
        // Sort the right subarray:
        //
        // Example:
        // [12,5,6]
        //
        // mid+1 = 3
        // right = 5
        mergeSort(arr, mid + 1, right);

        // ---------------- MERGE ----------------
        // At this point:
        //
        // Left half is sorted.
        // Right half is sorted.
        //
        // Now combine them into one sorted array.
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr,
                       int left,
                       int mid,
                       int right) {

        // Temporary array to store the merged result.
        //
        // Size:
        // right - left + 1
        //
        // Example:
        // left = 0
        // right = 5
        //
        // size = 6
        int[] temp = new int[right - left + 1];

        // ---------------- POINTERS ----------------

        // Pointer for LEFT sorted half.
        //
        // Example:
        // [3,4,8]
        //  ^
        //  i
        int i = left;

        // Pointer for RIGHT sorted half.
        //
        // Example:
        // [5,6,12]
        //  ^
        //  j
        int j = mid + 1;

        // Pointer for temp array.
        //
        // temp = [_,_,_,_,_,_]
        //         ^
        //         k
        //
        // k tells us where to place
        // the next smallest element.
        int k = 0;

        // ---------------- MAIN MERGING LOOP ----------------
        //
        // Continue while BOTH halves
        // still have elements.
        while (i <= mid && j <= right) {

            // Compare the current elements
            // from both halves.

            // If left element is smaller,
            // place it into temp.
            if (arr[i] <= arr[j]) {

                // temp[k] = arr[i]
                // then move both pointers.
                temp[k++] = arr[i++];
            }
            else {

                // Otherwise, take the
                // right element.
                temp[k++] = arr[j++];
            }
        }

        // ---------------- LEFTOVER LEFT ELEMENTS ----------------
        //
        // If some elements remain
        // in the left half,
        // copy them.
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // ---------------- LEFTOVER RIGHT ELEMENTS ----------------
        //
        // If some elements remain
        // in the right half,
        // copy them.
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // ---------------- COPY BACK ----------------
        //
        // temp contains the sorted order.
        //
        // Copy temp back into arr.
        //
        // Example:
        //
        // temp = [3,4,5,6,8,12]
        //
        // becomes:
        //
        // arr = [3,4,5,6,8,12]
        for (i = left, k = 0;
             i <= right;
             i++, k++) {

            arr[i] = temp[k];
        }
    }
}