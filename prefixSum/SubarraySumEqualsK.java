class Solution {
    public int subarraySum(int[] nums, int k) {

        // HashMap
        //
        // Key   -> Prefix Sum
        // Value -> Number of times this prefix sum has appeared
        HashMap<Integer, Integer> map = new HashMap<>();

        // Running prefix sum from index 0 to the current index
        int prefixSum = 0;

        // Initially, prefix sum 0 has occurred once.
        //
        // This handles cases where a subarray starting
        // from index 0 itself has sum = k.
        map.put(0, 1);

        // Stores the total number of subarrays
        // whose sum is equal to k.
        int count = 0;

        // Traverse every element in the array
        for (int right = 0; right < nums.length; right++) {

            // Update the running prefix sum
            prefixSum += nums[right];

            // We need an earlier prefix sum such that
            //
            // Current Prefix Sum - Previous Prefix Sum = k
            //
            // Therefore,
            //
            // Previous Prefix Sum = Current Prefix Sum - k
            int target = prefixSum - k;

            // If such a prefix sum exists,
            // then one or more valid subarrays end here.
            if (map.containsKey(target)) {

                // Add all occurrences because the same
                // prefix sum may have appeared multiple times.
                count += map.get(target);
            }

            // Store the current prefix sum
            // or increase its frequency.
            map.put(prefixSum,
                    map.getOrDefault(prefixSum, 0) + 1);
        }

        // Return the total number of valid subarrays.
        return count;
    }
}

Approach Name

Prefix Sum + HashMap

Main Idea

Instead of checking every subarray (which takes O(n²)), we use:

Prefix Sum → Running sum from index 0 to the current index.
HashMap → Stores how many times each prefix sum has appeared.

The key formula is:

Current Prefix Sum - Previous Prefix Sum = k

Rearranging:

Previous Prefix Sum = Current Prefix Sum - k

So, at every index:

Calculate the current prefix sum.
Compute target = prefixSum - k.
If target exists in the map, then a subarray with sum k ends at the current index.
Store the current prefix sum in the map.




Why do we use Prefix Sum?

Suppose

nums

[1,2,3,4]

Prefix Sum

Index

0 1 2 3

Value

1 3 6 10

Each prefix sum represents

Sum from index 0 to current index.
Main Formula

Suppose

Current Prefix Sum = 10

k = 7

We calculate

target

=

10-7

=

3

If Prefix Sum 3 has appeared before,

then

10-3

=

7

A valid subarray exists.

Dry Run

Input

nums = [1,1,1]

k = 2

Initially

prefixSum = 0

count = 0

map

0 → 1
Iteration 1
right = 0

nums[right]=1

Update Prefix Sum

prefixSum

=

1

Target

1-2

=

-1

Map

0→1

No -1.

Store Prefix Sum

map

0→1

1→1

Count

0
Iteration 2
right=1

nums[right]=1

Prefix Sum

2

Target

2-2

=

0

Map contains

0→1

That means

one valid subarray.

count

=

1

Store Prefix Sum

0→1

1→1

2→1
Iteration 3
right=2

nums[right]=1

Prefix Sum

3

Target

3-2

=

1

Map contains

1→1

Again

count++

=

2

Store Prefix Sum

0→1

1→1

2→1

3→1

Loop ends.

Answer

2
Visualization

Array

1 1 1

Subarrays

[1]

[1]

[1]

[1,1] ✅

[1,1] ✅

[1,1,1]

Exactly

2

subarrays have sum

2
Why do we write
map.put(0,1);

Suppose

nums

[3]

k=3

Prefix Sum

3

Target

3-3

=

0

If

0→1

is already in the map,

we correctly count the subarray

[3]

starting at index 0.

Without

map.put(0,1);

you would miss any valid subarray that begins at the start of the array.

Why do we write
count += map.get(target);

Instead of

count++;

Because the same prefix sum may occur multiple times.

Example

Prefix Sum

0

2

0

2

4

Suppose

target=2

If

2

appears twice,

both occurrences produce valid subarrays ending at the current index.

So we add the frequency, not just 1.

Time Complexity

We traverse the array only once.

O(n)

HashMap operations are approximately O(1) on average.

Space Complexity

The map stores prefix sums.

In the worst case, every prefix sum is unique.

O(n)
Easy Way to Remember
Prefix Sum
prefixSum += nums[i];

Meaning:

Running sum from index 0 to the current index.

Target
target = prefixSum - k;

Meaning:

"What previous prefix sum do I need so that the subarray between that point and the current index sums to k?"

HashMap
Key   → Prefix Sum
Value → Frequency of that Prefix Sum
Formula to Memorize
Current Prefix Sum - Previous Prefix Sum = k

Rearrange it to:

Previous Prefix Sum = Current Prefix Sum - k

This single formula is the foundation of many Prefix Sum + HashMap interview problems.