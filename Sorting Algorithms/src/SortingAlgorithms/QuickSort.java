package SortingAlgorithms;


import java.util.Arrays;

public class QuickSort extends SortingAlgorithm {
    public QuickSort() {
    }

    public String getTitle() {
        return "Quick Sort\n" +
                "Comparisons:\t" + getComparasons() + "\n" +
                "Swaps:\t" + getSwaps() + "\n\n";
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        sort(0, getData().length);
        System.out.println("Quick Sort done at " + (System.currentTimeMillis() - startTime) + " milliseconds.");
    }

    public void sort(int start, int end) {
        if(end == 2 || end-start == 2||end-start<2){
            if(compare(start, end-1)>0)
                swap(start, end-1);
            //updateTitle();
            return;
        }

        int mid = (end-start-1)/2+start;
        // Set pivot
        if(compare(start, mid)>0)
            swap(start, mid);
        if(compare(mid, end-1)>0) {
            swap(mid, end - 1);
            if(compare(start, mid)>0)
                swap(start, mid);
        }
        // Sort

        boolean leftFound = false, rightFound = false;
        int left = mid-1, right = mid+1;
            boolean needRepeat = false;
            while(!needRepeat) {
                needRepeat = false;
                while (left > start && right < end - 1) {
                    // Find left
                    while (left > start)
                        if (compare(left, mid) > 0) {
                            needRepeat = true;
                            break;
                        } else
                            left--;
                        // find right
                    while (right < end-1)
                        if (compare(right, mid) < 0) {
                            needRepeat = true;
                            break;
                        } else
                            right++;
                    if (left > start && right < end - 1)
                        swap(left, right);
                }
            }

        if(right<end-1) {
            System.out.println("Right is less than end");
            swap(right++, end-1);
        }
        if(left>start) {
            System.out.println("Left is greater than start");
            swap(left--, start);
        }
        swap(end-1, mid);
        selectPoint(mid);

        if(mid-start>1)
            sort(start, mid-1);
        if(end-mid+1>1)
            sort(mid+1, end);
    }
}