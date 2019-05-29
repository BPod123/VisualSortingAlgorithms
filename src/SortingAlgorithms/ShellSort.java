package SortingAlgorithms;

import javafx.scene.paint.Color;

public class ShellSort extends SortingAlgorithm {
    public ShellSort(){
        super("Shell Sort");
    }
    @Override
    public void run(){
        int i, k, j;
        for(k = getData().length/2; k>0; k--)
        for( i = 0, j = k; j<getData().length; i++, j++){
            if(compare(i,j)>0)
                swap(i, j);
            if(j-i==1){
                selectPoint(i, Color.GREEN);
                selectPoint(j, Color.GREEN);
            }
        }
    }
}
