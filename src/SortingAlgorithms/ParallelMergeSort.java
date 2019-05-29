package SortingAlgorithms;

import SortPane.SortPane;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends MergeSort {
    public ParallelMergeSort() {
        setTitle("Parallel Merge Sort");
    }

    @Override
    public void run() {
        MergeSortTask mainTask = new MergeSortTask(this, 0, getData().length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);

    }

    private class MergeSortTask extends RecursiveAction {
        private MergeSort algorithm;
        private int start;
        private int end;

        public MergeSortTask(MergeSort mergeSort, int start, int end) {
            this.algorithm = mergeSort;
            this.start = start;
            this.end = end;
        }

        @Override
        public void compute() {
            if (end - start > 1) { // Divide and conquer
                int mid = start + (end - start) / 2;
                MergeSortTask task1 = new MergeSortTask(algorithm, start, mid);
                MergeSortTask task2 = new MergeSortTask(algorithm, mid, end);
                invokeAll(task1, task2);
                algorithm.merge(start, mid, mid, end);
            }
        }
    }
}
