package SortingAlgorithms;

import SortPane.SortPane;
import javafx.scene.paint.Color;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort extends SortingAlgorithm {
    public ParallelQuickSort(){
        super(1);
    }
    @Override
    public String getTitle(){
        return "Parallel Quick Sort";
    }
    @Override
    public void run(){
        ParallelQuickSortTask mainTask = new ParallelQuickSortTask(this);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }
    private class ParallelQuickSortTask extends RecursiveAction{
        private SortingAlgorithm algorithm;
        private int start;
        private int end;
        public ParallelQuickSortTask(SortingAlgorithm algorihtm){
            this.algorithm = algorihtm;
            start = 0;
            end = algorithm.getData().length;
        }
        public ParallelQuickSortTask(SortingAlgorithm algorithm, int start, int end){
            this.algorithm = algorithm;
            this.start = start;
            this.end = end;
        }
        @Override
        public void compute(){
            if(end-start==2){
                if(compare(start, end-1)>0)
                    swap(start, end-1);
                selectPoint(start, Color.GREEN);
                selectPoint(end-1, Color.GREEN);
                return;
            }
            else if(end-start<2) {
                selectPoint(start, Color.GREEN);
                return;
            }
            else {
                int mid = start + (end-start)/2;
                // Arrange, start, mid, and end-1
                if(compare(start, mid)>0)
                    swap(start, mid);
                if(compare(mid,end-1)>0) {
                    swap(mid, end-1);
                    if(compare(start, mid)>0)
                        swap(mid, start);
                }
                // Move swap mid with end-1 and begin sorting
                swap(mid, end-1);
                int left = start, right = end-2, leftSwap = start, rightSwap = end-2;
                boolean leftReady = false, rightReady = false;
                while(leftSwap<rightSwap){
                    if(!leftReady && compare(left,end-1)>0){
                        leftSwap = left;
                        leftReady = true;
                    }
                    else
                        if(!leftReady)
                            left++;
                    if(!rightReady && compare(right,end-1)<0){
                        rightSwap = right;
                        rightReady = true;
                    }
                    else
                        if(!rightReady)
                            right--;
                    if(  !(leftSwap>rightSwap) && leftReady && rightReady) {
                        swap(rightSwap, leftSwap);
                        leftReady = false;
                        rightReady = false;
                    }
                }
                swap(leftSwap, end-1);
                selectPoint(leftSwap, Color.GREEN);
                // Divide and execute
                ParallelQuickSortTask leftSide = new ParallelQuickSortTask(algorithm, start, leftSwap);
                ParallelQuickSortTask rightSide = new ParallelQuickSortTask(algorithm, leftSwap+1, end);
                invokeAll(leftSide, rightSide);
            }
        }
    }
}
