package Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        int [][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int [] [] ans = mergeIntervals(intervals);
        for(int [] arr : ans){
            System.out.print(Arrays.toString(arr) + ", ");
        }
    }

    private static int [][] mergeIntervals(int[][] intervals) {
        if(intervals.length == 1){
            return intervals;
        }

        // arrayList of type int [] to store ans
        List<int []> res = new ArrayList<>();

        // sort the intervals based on staring value
        // Method 1 :
        // Arrays.sort(intervals,(a,b) -> a[0] - b[0]);    // if a[0] = b[0] = 2^31 - 1 then this won't work
        // Method 2 :
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int start = intervals[0][0];
        int end = intervals[0][1];

        for(int [] interval : intervals){
            // if the start of the new interval is less than or equal to end of the previous interval then merge
            // the intervals by updating end to the max of the new interval end and previous end
            if(interval[0] <= end){
                // update end
                end = Math.max(interval[1], end);
            }
            // else add start and end to the res
            else{
                res.add(new int [] {start,end});
                // update start and end to new intervals start and end
                start = interval[0];
                end = interval[1];
            }
        }

        // add last interval
        res.add(new int[]{start,end});

        // convert list to 2-D array
        return res.toArray(new int [res.size()][]);
    }

}
