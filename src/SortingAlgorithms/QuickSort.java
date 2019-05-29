package SortingAlgorithms;


import javafx.scene.paint.Color;

import java.util.Arrays;

public class QuickSort extends SortingAlgorithm {
    public QuickSort() {
        super("Quick Sort");
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        sort(0, getData().length);
        System.out.println("Quick Sort done at " + (System.currentTimeMillis() - startTime) + " milliseconds.");
    }

    public void sort(int start, int end) {
        //put largest,

        if(end-start == 2||end-start<2){
            if(compare(start, end-1)>0)
                swap(start, end-1);
            getPane().selectPoint(start, Color.GREEN);
            getPane().selectPoint(end-1, Color.GREEN);
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
        swap(mid, end-1);

        // Sort
        boolean leftFound = false, rightFound = false;
        int left, right, swapL = -1, swapR = -1;
                for(left = start, right = end-2; true;){
                    if(leftFound||compare(left, end-1)>0) {// Find left
                        if(!leftFound) {
                            swapL = left;
                        }
                        leftFound = true;
                    }
                    else
                        left++;
                    if(rightFound||compare(right, end-1)<0) { // findRight
                        if(!rightFound)
                            swapR = right;
                        rightFound = true;
                    }
                    else right--;
                    if(leftFound && rightFound) {
                        if(swapL>swapR)
                            break;
                        swap(swapL, swapR);
                        leftFound = false;
                        rightFound = false;
                    }
                }
                swap(swapL, end-1);
                    getPane().selectPoint(swapL, Color.GREEN);
                sort(start, swapL);
                sort(swapL+1, end);

    }
}