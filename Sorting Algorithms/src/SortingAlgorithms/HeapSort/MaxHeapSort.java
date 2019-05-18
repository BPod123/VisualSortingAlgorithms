package SortingAlgorithms.HeapSort;

import javafx.scene.paint.Color;

public class MaxHeapSort extends HeapSort {
    public MaxHeapSort(){
        super(1);
    }
    @Override
    public boolean needSwap(int parent, int child){
        return compare(parent, child)<0;
    }

    @Override
    public String getTitle(){
        return "Max Heap Sort";
    }
    @Override
    public void run(){
        setFrameRateMillis(Math.max(getFrameRateMillis(), 20));
        createHeap(0, getData().length);
        sort();
    }
    public void sort(){
        for(int i = getData().length-1; i>0; i--) {
            swap(0, i);
            selectPoint(i, Color.GREEN);
            heapify(0, i);
            //createHeap(0, i);

        }
    }
}
