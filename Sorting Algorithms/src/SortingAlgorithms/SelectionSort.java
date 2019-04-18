package SortingAlgorithms;

import SortPane.SortPane;

public class SelectionSort extends SortingAlgorithm {
    public SelectionSort(){}
    public String getTitle(){
        return "Selection Sort";
    }
    @Override
    public void updateTitle(){
        getPane().getTitle().setText("SelectionSort\n"+
                "\nComparisons:\t"+getComparasons()+ "\nSwaps:\t"+getSwaps());
    }
    @Override
    public void run(){
        for(int k = getData().length-1, biggest = 0; k>=0; k--, biggest = 0) {
            for (int i = 0; i <= k; i++)
                if(getPane().compare(i, biggest)>0)
                    biggest = i;
            swap(k, biggest);
            selectPoint(k);
            }
        }
    }
