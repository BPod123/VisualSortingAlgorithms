package SortingAlgorithms.HeapSort;

import javafx.scene.paint.Color;

public class MinHeapSort extends HeapSort {
    public MinHeapSort(){
        super(1);
    }
    @Override
    public boolean needSwap(int parent, int child){
        return compare(parent, child) >0;
    }
    @Override
    public String getTitle() {
        return "Min Heap Sort";
    }
    @Override
    public void run(){
        sort();
        selectPoint(getData().length-1, Color.GREEN);
    }
    public void sort(){
        for(int i = 0; i<getData().length-1; i++){
            createHeap(i, getData().length);
            selectPoint(i, Color.GREEN);
        }
    }
}
