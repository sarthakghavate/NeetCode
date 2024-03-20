package Intervals;
//https://leetcode.com/problems/non-overlapping-intervals/description/
import java.util.*;
public class OverlappingIntervals {
    public static void main(String[] args) {
        int [][]  intervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervals(intervals));
    }
    public static int eraseOverlapIntervals(int[][] intervals) {
        // count overlapping intervals
        if(intervals.length == 1){
            return 0;
        }

        // sort intervals by start
        //Arrays.sort(intervals , Comparator.comparingInt(a -> a[0]));
        Arrays.sort(intervals , (a,b) -> a[0]-b[0]);


        int end = intervals[0][1];
        int count = 0; // to count overlapping intervals

        for(int [] i :intervals){
            if(i[0] < end){
                // overlapping so increment counter
                count++;
                // adjust end to min
                end = Math.min(i[1],end);
            }
            else{
                // update end to current intervals end
                end =  i[1];
            }
        }
        // since we count for first interval as well so return count -1
        return count-1;
    }
}
