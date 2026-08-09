
class Solution {
    public int majorityElement(int[] nums) {

        // Stores the current majority candidate
        int candidate = 0;

        // Stores the vote count of the current candidate
        int count = 0;

        // Traverse each element in the array
        for (int num : nums) {

            // If count becomes 0, choose the current element
            // as the new candidate.
            if (count == 0) {
                candidate = num;
            }

            // If the current element is the candidate,
            // increase its vote count.
            if (candidate == num) {
                count++;
            }

            // Otherwise, a different element cancels one vote
            // of the current candidate.
            else {
                count--;
            }
        }

        // The remaining candidate is the majority element.
        return candidate;
    }
}


What does each variable mean?
int candidate = 0;
Stores the element that is currently assumed to be the majority element.

Initially, we don't know the majority, so we assign any value.

int count = 0;
Stores the number of votes for the current candidate.

Think of it as the candidate's score.

for (int num : nums)

Traverse every element in the array one by one.

For

nums = [2,2,1,1,1,2,2]

num becomes

2

2

1

1

1

2

2
if(count == 0)

If the current candidate has no votes left,

choose the current element as the new candidate.

candidate = num;

Example

Before

Candidate = 2

Count = 0

Current number

1

Now

Candidate = 1
if(candidate == num)

If the current number is the same as the candidate,

increase its votes.

count++;

Example

Candidate = 2

Current number = 2

Count = 3

After

Count = 4
else

If the current number is different,

cancel one vote.

count--;

Example

Candidate = 2

Current number = 1

One vote gets cancelled.

Logic Behind the Algorithm

Suppose

[2,2,1,1,1,2,2]

Frequency

2 → 4 times

1 → 3 times

Imagine every occurrence of 2 gets one vote.

Every occurrence of 1 removes one vote.

2 ✔

2 ✔

1 ✘

1 ✘

1 ✘

2 ✔

2 ✔

Cancel one 2 with one 1.

2 ❌ 1

2 ❌ 1

2 ❌ 1

2

One 2 remains.

Therefore,

Majority = 2
Why Does It Work?

The majority element appears more than n/2 times.

Even after cancelling every non-majority element,

at least one majority element is still left.

Therefore,

the final candidate must be the majority element.

Dry Run

Input

nums = [2,2,1,1,1,2,2]

Initially

candidate = 0

count = 0
Iteration 1

Current number

2

count == 0

Choose

candidate = 2

Current number equals candidate

count = 1
Iteration 2

Current number

2

Same as candidate

count = 2
Iteration 3

Current number

1

Different

count = 1
Iteration 4

Current number

1

Different

count = 0
Iteration 5

Current number

1

Since

count = 0

Choose new candidate

candidate = 1

Increase count

count = 1
Iteration 6

Current number

2

Different

count = 0
Iteration 7

Current number

2

Choose new candidate

candidate = 2

Increase count

count = 1

Loop ends.

Return

2
Dry Run Table
Iteration	Current Number	Count Before	Candidate Before	Action	Count After	Candidate After
Start	-	0	-	Initialize	0	0
1	2	0	0	New candidate, increment	1	2
2	2	1	2	Same candidate → increment	2	2
3	1	2	2	Different → decrement	1	2
4	1	1	2	Different → decrement	0	2
5	1	0	2	New candidate, increment	1	1
6	2	1	1	Different → decrement	0	1
7	2	0	1	New candidate, increment	1	2

Final Answer: 2

Time Complexity
We traverse the array only once.

Time Complexity: O(n)

Space Complexity
We use only two variables (candidate and count).

Space Complexity: O(1)

Interview Explanation

"I used the Boyer-Moore Voting Algorithm. I maintain a candidate and its vote count. When the count becomes zero, I select the current element as the new candidate. If the current element matches the candidate, I increase the count; otherwise, I decrease it because different elements cancel each other's votes. Since the majority element appears more than half of the array size, it cannot be completely cancelled and will remain as the final candidate. This gives an O(n) time and O(1) space solution."