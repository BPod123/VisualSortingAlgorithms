package SortingAlgorithms;

import SortPane.SortPane;
import javafx.scene.paint.Color;

public class InsertionSort extends SortingAlgorithm implements Runnable {
    private SortPane pane;

    public InsertionSort() {
        super("Insertion Sort");
    }

    @Override
    public void run() {
        for(int k = 1; k<getData().length;  k++)
            for(int i = k; i>0; i--){
                if(compare(i,i-1)<0) {
                    swap(i, i - 1);
                    getPane().selectPoint(i, Color.GREEN);
                }
                else{
                    selectPoint(i, Color.GREEN);
                    selectPoint(i-1, Color.GREEN);
                    break;
                }
            }
        selectPoint(0, Color.GREEN);
    }
}
