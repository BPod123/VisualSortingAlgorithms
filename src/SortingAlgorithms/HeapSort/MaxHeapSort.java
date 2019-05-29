package SortingAlgorithms.HeapSort;

import javafx.scene.paint.Color;

public class MaxHeapSort extends HeapSort {
    public MaxHeapSort(){
        super("Max Heap Sort");
    }
    @Override
    public boolean needSwap(int parent, int child){
        return compare(parent, child)<0;
    }

    @Override
    public void run(){
        setFrameRateMillis(Math.max(getFrameRateMillis(), 50));
        sort();
        updateAndColorAllIndecies(Color.GREEN, 2);
    }
    public void sort(){
        createHeap(0, getData().length);
        for(int i = getData().length-1; i>0; i--) {
            heapify(0, i);
            swap(0, i);
            selectPoint(i, Color.GREEN);
        }
    }
}
