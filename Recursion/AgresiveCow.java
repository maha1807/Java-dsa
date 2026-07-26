// #It is asking:

// What is the maximum possible minimum distance between any two balls?

// Whenever you see:

// maximize the minimum
// minimize the maximum
// largest possible value
// smallest possible value

// a big hint should come into your mind:

// 💡 Can I Binary Search on the Answer?

import java.util.Arrays;

class Solution {

    public int maxDistance(int[] position, int m) {

        // Step 1: Sort the basket positions so that
        // we can place balls from left to right.
        Arrays.sort(position);

        // Minimum possible distance between two balls.
        int low = 1;

        // Maximum possible distance between two balls.
        int high = position[position.length - 1] - position[0];

        // Stores the final answer.
        int ans = 1;

        // Binary Search on the answer (minimum distance).
        while (low <= high) {

            // Candidate distance.
            int mid = low + (high - low) / 2;

            // Check if we can place all m balls
            // such that each pair is at least 'mid' apart.
            if (canPlace(position, m, mid)) {

                // 'mid' is possible, so store it.
                ans = mid;

                // Try for a larger minimum distance.
                low = mid + 1;
            } else {

                // 'mid' is not possible,
                // so reduce the distance.
                high = mid - 1;
            }
        }

        // Return the maximum possible minimum distance.
        return ans;
    }

    // Helper function:
    // Returns true if we can place m balls
    // with at least 'dist' distance between them.
    private boolean canPlace(int[] position, int m, int dist) {

        // Place the first ball in the first basket.
        int balls = 1;

        // Position of the last placed ball.
        int lastPos = position[0];

        // Traverse the remaining baskets.
        for (int i = 1; i < position.length; i++) {

            // If the current basket is far enough,
            // place another ball.
            if (position[i] - lastPos >= dist) {
                balls++;
                lastPos = position[i];
            }

            // If all m balls are placed,
            // then this distance is possible.
            if (balls == m) {
                return true;
            }
        }

        // Could not place all m balls.
        return false;
    }
}
```

