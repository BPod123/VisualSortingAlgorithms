package SortingAlgorithms;

import javafx.scene.paint.Color;

public class DoubleInsertionSort extends SortingAlgorithm {
    private MergeSort merger = new MergeSort();
    public DoubleInsertionSort(){
        super("Double Insertion Sort");
    }
    @Override
    public void run(){
        sort();
    }
    public void sort(){
        int left = 0, right = getData().length-1, li, ri;
        selectPoint(left, Color.YELLOW);
        selectPoint(right, Color.YELLOW);
        for(left = 0, li = left+1, ri = right-1, right = getData().length-1; left<right; left++, right--, li=left+1, ri=right-1){
            while(li>0&&compare(li,li-1)<0) {
                swap(li--, li);
                selectPoint(li+1, Color.YELLOW);
                selectPoint(li, Color.YELLOW);
            }
            selectPoint(li+1, Color.YELLOW);
            selectPoint(li, Color.YELLOW);
            if(li>0)
                selectPoint(li-1, Color.YELLOW);
            while(ri<getData().length-1 && compare(ri,ri+1)>0) {
                swap(ri++, ri);
                selectPoint(ri-1, Color.YELLOW);
                selectPoint(ri, Color.YELLOW);
            }
            selectPoint(ri-1, Color.YELLOW);
            selectPoint(ri, Color.YELLOW);
            if(ri<getData().length-1)
            selectPoint(ri+1, Color.YELLOW);
        }
        selectPoint(left, Color.RED);
        selectPoint(right, Color.BLUE);
        merger.merge(0, left+1,left+1, getData().length);
    }

}
