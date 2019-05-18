package SortingAlgorithms.HeapSort;

import SortingAlgorithms.SortingAlgorithm;

public abstract class HeapSort extends SortingAlgorithm {
    public abstract boolean needSwap(int parent, int child);
    public void createHeap(int start, int end){
        for(int i = end-1; (i-1+start)/2>=start; i--){
            if(needSwap( (i-1+start)/2, i ))
                swap((i - 1 + start) / 2, i);
        }
    }
    public void heapify(int start, int end) {
            if(start*2+2<end){
                if(needSwap(start*2+1,start*2+2) && needSwap(start, start*2+2)) {
                    swap(start, start * 2 + 2);
                    heapify(start * 2 + 2, end);
                    if(needSwap(start, start*2+2))
                        swap(start, start*2+2);
                    return;
                }
            }
            if(start*2+1<end && needSwap(start, start*2+1)){
                swap(start, start*2+1);
                heapify(start*2+1, end);
                if(needSwap(start, start*2+1))
                    swap(start, start*2+1);
            }
    }
    public HeapSort(int numPermits){
        super(numPermits);
    }
}
