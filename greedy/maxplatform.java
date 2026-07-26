import java.util.*;   // Imports Arrays class and other utility classes

class Platform {

    // Function to find minimum platforms required
    public static int findPlatform(int[] arr, int[] dep, int n) {

        // Sort arrival times
        Arrays.sort(arr);

        // Sort departure times
        Arrays.sort(dep);

        // Pointer for arrival array
        int i = 0;

        // Pointer for departure array
        int j = 0;

        // Current number of platforms occupied
        int platform = 0;

        // Maximum platforms occupied at any time
        // This will be our answer
        int maxPlatform = 0;

        // Process arrivals and departures in chronological order
        while (i < arr.length && j < dep.length) {

            // If next train arrives before or at the time
            // the earliest train departs
            if (arr[i] <= dep[j]) {

                // Need one more platform
                platform++;

                // Update answer if current platforms exceed previous maximum
                maxPlatform = Math.max(maxPlatform, platform);

                // Move to next arriving train
                i++;
            }
            else {

                // A train has departed
                // One platform becomes free
                platform--;

                // Move to next departure
                j++;
            }
        }

        // Return minimum number of platforms required
        return maxPlatform;
    }

    public static void main(String[] args) {

        // Arrival times of trains
        int[] arr = {900, 940, 950, 1100, 1500, 1800};

        // Departure times of trains
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};

        // Print answer
        System.out.println(findPlatform(arr, dep, arr.length));
    }
}