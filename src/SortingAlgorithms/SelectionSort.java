package SortingAlgorithms;

import SortPane.SortPane;
import javafx.scene.paint.Color;

public class SelectionSort extends SortingAlgorithm {
    public SelectionSort(){
        super("Selection Sort");
    }
    @Override
    public void run(){
        for(int k = getData().length-1, biggest = 0; k>=0; k--, biggest = 0) {
            for (int i = 0; i <= k; i++)
                if(compare(i, biggest)>0) {
                    getPane().disselectPoint(biggest);
                    biggest = i;
                    getPane().selectPoint(biggest, Color.RED);
                }
            swap(k, biggest);
                getPane().selectPoint(k, Color.GREEN);
            }
        }
    }
