package Intervals;
import java.util.*;

/* Brute force :
1. Create allIntervals array of size intervals.length + 1.
2. add all the intervals of intervals array to it and at the end add newInterval
3. Apply same logic as merge intervals problem on the input array allIntervals , this includes sorting of an array by start element.

Drawback : we were not utilizing the fact that given intervals array is sorted by start in ascending(strictly increasing) order

Optimal Solution:
1. Create allIntervals array of size intervals.length + 1.
2. Insert newInterval at its appropriate position.
3. Now no need to sort the allIntervals array , so apply merge logic directly.
 */
public class InsertIntervals {
    public static void main(String[] args) {
        int [][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int [] newInterval = {4,8};
        int [][] ans  = insertInterval(intervals,newInterval);
        printArray(ans);
    }
    private static void printArray(int [][] array){
        for(int [] arr : array){
            System.out.print(Arrays.toString(arr) + " ");
        }
    }
    private static int[][] insertInterval(int[][] intervals, int[] newInterval) {

        if(intervals.length==0){
            return new int [][] {newInterval};
        }

        // since intervals is sorted by start in ascending order , we just have to find the insert position for
        // newInterval and insert it into allIntervals.
        int [][] allIntervals = new int [intervals.length+1][];

        int k = 0;
        boolean inserted = false;

        for(int [] i : intervals){
            if(!inserted && i[0] > newInterval[0]){
                allIntervals[k] = newInterval;
                inserted = true;
            }
            allIntervals[ k + (inserted ? 1 : 0)] = i;
            k++;
        }

        // if still newInterval is not inserted , i.e : newIntervals correct position is last that case

        if(!inserted){
            allIntervals[k] = newInterval;
        }

        // now merge logic
        List<int []> res = new ArrayList<>();

        int start = allIntervals[0][0];
        int end = allIntervals[0][1];

        for(int [] i : allIntervals){
            if(i[0] <= end){
                // overlap
                // update end
                end = Math.max(i[1],end);
            }
            else{
                // add to ans and update start and end
                res.add(new int [] {start,end});
                start = i[0];
                end = i[1];
            }
        }

        // add last interval
        res.add(new int []{start,end});

        // convert list of [] int  to 2D array
        return res.toArray(new int[res.size()][]);
    }
}
