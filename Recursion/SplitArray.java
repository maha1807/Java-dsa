class Solution {

    public int splitArray(int[] nums, int k) {

        // The answer cannot be smaller than the largest element leetcode:410
        // because every subarray must contain at least one element.
        int low = 0;

        // The answer cannot be larger than the sum of all elements
        // because one subarray can contain the entire array.
        int high = 0;

        // Find the search space [low, high]
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        // Binary Search on the answer
        while (low < high) {

            // Candidate for the largest subarray sum
            int mid = low + (high - low) / 2;

            // If it is possible to split the array into
            // at most k subarrays with max sum = mid,
            // try to find an even smaller answer.
            if (canSplit(nums, k, mid)) {
                high = mid;
            }
            // Otherwise, mid is too small,
            // so we need a larger maximum sum.
            else {
                low = mid + 1;
            }
        }

        // low == high and points to the minimum possible answer
        return low;
    }

    private boolean canSplit(int[] nums, int k, int maxSum) {

        // Number of subarrays formed so far.
        // We start with one subarray.
        int pieces = 1;

        // Sum of the current subarray.
        int currSum = 0;

        // Traverse every element in the array
        for (int num : nums) {

            // If adding the current element exceeds maxSum,
            // we must start a new subarray.
            if (currSum + num > maxSum) {
                pieces++;

                // Start the new subarray with the current element.
                currSum = num;
            }
            // Otherwise, keep adding to the current subarray.
            else {
                currSum += num;
            }
        }

        // If we can split into at most k subarrays,
        // then maxSum is a valid answer.
        return pieces <= k;
    }
}

//# Dry Run

### Input

```java
nums = [7, 2, 5, 10, 8]
k = 2
```

---

# Step 1: Find Search Space

```java
low = max(nums) = 10
high = sum(nums) = 32
```

So,

```text
Search Space = [10, 32]
```

---

# Iteration 1

```java
mid = 10 + (32 - 10) / 2
    = 21
```

Check:

```java
canSplit(nums, 2, 21)
```

---

### Building subarrays

| num | currSum       | Action                  | pieces |
| --- | ------------- | ----------------------- | ------ |
| 7   | 7             | Add to current subarray | 1      |
| 2   | 9             | Add                     | 1      |
| 5   | 14            | Add                     | 1      |
| 10  | 14+10=24 > 21 | Start new subarray      | 2      |
| 8   | 10+8=18       | Add                     | 2      |

Subarrays formed:

```text
[7,2,5]
[10,8]
```

```text
pieces = 2 <= k
```

Return:

```text
true
```

Since it is possible,

```java
high = mid = 21
```

New search space:

```text
[10, 21]
```

---

# Iteration 2

```java
mid = 10 + (21 - 10) / 2
    = 15
```

Check:

```java
canSplit(nums, 2, 15)
```

| num | currSum      | Action       | pieces |
| --- | ------------ | ------------ | ------ |
| 7   | 7            | Add          | 1      |
| 2   | 9            | Add          | 1      |
| 5   | 14           | Add          | 1      |
| 10  | 14+10=24 >15 | New subarray | 2      |
| 8   | 10+8=18 >15  | New subarray | 3      |

Subarrays:

```text
[7,2,5]
[10]
[8]
```

```text
pieces = 3 > k
```

Return:

```text
false
```

Since it is not possible,

```java
low = mid + 1 = 16
```

New search space:

```text
[16, 21]
```

---

# Iteration 3

```java
mid = 16 + (21 - 16) / 2
    = 18
```

Check:

```java
canSplit(nums, 2, 18)
```

| num | currSum      | Action       | pieces |
| --- | ------------ | ------------ | ------ |
| 7   | 7            | Add          | 1      |
| 2   | 9            | Add          | 1      |
| 5   | 14           | Add          | 1      |
| 10  | 14+10=24 >18 | New subarray | 2      |
| 8   | 10+8=18      | Add          | 2      |

Subarrays:

```text
[7,2,5]
[10,8]
```

```text
pieces = 2 <= k
```

Return:

```text
true
```

So,

```java
high = 18
```

New search space:

```text
[16, 18]
```

---

# Iteration 4

```java
mid = 16 + (18 - 16) / 2
    = 17
```

Check:

```java
canSplit(nums, 2, 17)
```

Subarrays:

```text
[7,2,5]
[10]
[8]
```

```text
pieces = 3 > 2
```

Return:

```text
false
```

So,

```java
low = mid + 1 = 18
```

---

# Final

```text
low = 18
high = 18
```

Loop stops.

Return:

```java
18
```

---

# Answer

The minimum possible largest subarray sum is:

```text
18
```

Best split:

```text
[7,2,5] -> sum = 14
[10,8]  -> sum = 18
```

Largest sum among the subarrays:

```text
18
```
