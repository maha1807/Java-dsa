class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {

        // Stores the total amount of gas available in all stations
        int totalGas = 0;

        // Stores the total amount of gas needed to travel
        int totalCost = 0;

        // Traverse every gas station
        for (int i = 0; i < gas.length; i++) {

            // Add current station gas
            totalGas += gas[i];

            // Add travelling cost
            totalCost += cost[i];
        }

        // If total gas is less than total cost,
        // completing the circuit is impossible.
        if (totalGas < totalCost) {
            return -1;
        }

        // Candidate starting station
        int pos = 0;

        // Stores the gas remaining while travelling
        int remainingGas = 0;

        // Traverse every station once
        for (int i = 0; i < gas.length; i++) {

            // Gain gas at current station
            // Spend gas to travel to next station
            remainingGas += gas[i] - cost[i];

            // If gas becomes negative,
            // current starting point cannot complete the journey.
            if (remainingGas < 0) {

                // Start from the next station
                pos = i + 1;

                // Reset remaining gas
                remainingGas = 0;
            }
        }

        // Return the valid starting station
        return pos;
    }
}

Approach (Greedy)

The idea is based on two observations:

Step 1: Check if the journey is possible

Calculate

Total Gas available
Total Cost required

If

Total Gas < Total Cost

then completing the circuit is impossible.

Return -1.

Step 2: Find the starting station

Now traverse every station.

At each station calculate

remainingGas += gas[i] - cost[i]

This tells us how much fuel is left after travelling to the next station.

If

remainingGas < 0

it means

We cannot reach the next station starting from our current starting point.

Therefore,

All stations between current starting station and current station are invalid.
Start again from the next station.
Reset remainingGas to 0.

This greedy idea works because if we fail at station i, none of the stations before i can be the answer.

Time Complexity:

O(n)

Space Complexity:

O(1)

Why does resetting the starting point work?

Suppose

Start = 2

At station 5

remainingGas becomes negative.

That means

2 -> 3 -> 4 -> 5

already consumed more fuel than available.

If station 3 or 4 were chosen as the start,

they would have even less gas available before reaching station 5.

So none of

2
3
4
5

can be the answer.

Hence we directly move to

Start = 6

This is why the greedy solution works in O(n).

Dry Run

Input

gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]
First Pass
Total Gas

1+2+3+4+5 = 15

Total Cost

3+4+5+1+2 = 15

Since

15 >= 15

Solution exists.

Second Pass
i	gas	cost	gain (gas-cost)	remainingGas	pos
0	1	3	-2	-2 → reset to 0	1
1	2	4	-2	-2 → reset to 0	2
2	3	5	-2	-2 → reset to 0	3
3	4	1	+3	3	3
4	5	2	+3	6	3

Loop ends.

Answer

3
Visual Journey
Start at station 3

Gas = 0

Station 3

+4
-1

Remaining = 3

↓

Station 4

+5
-2

Remaining = 6

↓

Station 0

+1
-3

Remaining = 4

↓

Station 1

+2
-4

Remaining = 2

↓

Station 2

+3
-5

Remaining = 0

Back to station 3

Journey completed ✔
Key Intuition to Remember
Total Gas < Total Cost → Impossible → Return -1.
Maintain a running fuel balance (remainingGas).
If the balance becomes negative at station i, none of the stations from the current start through i can be valid starting points.
Reset the balance and choose i + 1 as the new candidate start.