package SortingAlgorithms;

import Application.SortApplication;
import SortPane.SortPane;

import static Application.SortApplication.waitTime;

public class BubbleSort extends SortingAlgorithm {
    public BubbleSort() {
    }



    @Override
    public String getTitle() {
        return "Bubble Sort";
    }

    @Override
    public void run() {
       // try {
            for (int k = getData().length-1; k >0; k--) {
                selectPoint(k);
                for (int i = k; i >=0; i--) {
                    if (getPane().compare(i, k) > 0) // data[i] > data[k]
                        getPane().swap(i, k);
                    //Thread.sleep(waitTime);
                    selectPoint(i);
                }
                selectPoint(k);
            }
        //} catch (InterruptedException ex) {}
    }
}
