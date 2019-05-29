package SortingAlgorithms.HeapSort;

import javafx.scene.paint.Color;

public class MinHeapSort extends HeapSort {
    public MinHeapSort() {
        super("Min Heap Sort");
    }

    @Override
    public boolean needSwap(int parent, int child) {
        return compare(parent, child) > 0;
    }

    @Override
    public void run() {
        sort();
        long ogFrameRate = getFrameRateMillis();
        setFrameRateMillis(2);
        sort();
        sort();
        setFrameRateMillis(5);
        sort();
        setFrameRateMillis(ogFrameRate);
    }

    public void sort() {
        createHeap(0, getData().length);
        for (int i = 0; i < getData().length; i++) {
            heapifyWithOffSet(i, getData().length, i);
            selectPoint(i, Color.GREEN);
        }
    }

    public void heapifyWithOffSet(int start, int end, int offSet) {
        int largest = start, first = start*2+1 - offSet, second = start*2+2 -offSet;
        if(first<end && needSwap(largest, first))
            largest = first;
        if(second<end && needSwap(largest, second))
            largest = second;
        if(largest != start){
            swap(start, largest);
            heapifyWithOffSet(largest, end, offSet);
        }
    }
}
