package SortingAlgorithms.HeapSort;

import SortingAlgorithms.SortingAlgorithm;

public abstract class HeapSort extends SortingAlgorithm {
    public abstract boolean needSwap(int parent, int child);
    public void createHeap(int start, int end){
        //for(int i = end-1; (i-1+start)/2>=start; i--){
          //  if(needSwap( (i-1+start)/2, i ))
            //    swap((i - 1 + start) / 2, i);
        //}
        for(int i = end/2; i>0; i--)
              heapify(i, end);
    }
    public void heapify(int start, int end) {
        int largest = start, first = start*2+1, second = start*2+2;
        if(first<end && needSwap(largest, first))
            largest = first;
        if(second<end && needSwap(largest, second))
            largest = second;
        if(largest != start){
            swap(start, largest);
            heapify(largest, end);
        }
    }
    public HeapSort(String title){
        super(title);
    }
}
