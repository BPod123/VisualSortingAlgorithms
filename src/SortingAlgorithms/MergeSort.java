package SortingAlgorithms;

import javafx.scene.paint.Color;

import static Application.ApplicationPresets.waitTime;

public class MergeSort extends SortingAlgorithm {
    private double[] data;

    public MergeSort() {
        super("Merge Sort");
    }


    @Override
    public void run() {
        data = getData();
        sort(0, getData().length);
        //updateAndColorAllIndecies(Color.GREEN, true, true);
    }

    public void sort(int start, int end) {
        int mid = start + (end - start) / 2;
        if (end - start > 1) {
            sort(start, mid);
            sort(mid, end);
        }
        merge(start, mid, mid , end);
    }

    public void merge(int group1Start, int group2Start, int group1End, int group2End) {
        int left, right, i, group1Length = group1End-group1Start, group2Length = group2End-group2Start;
        double[] merged = new double[group1Length + group2Length];
        for(i = 0, left = group1Start, right = group2Start; left<group1Start+group1Length &&
                right<group2Start+group2Length; i++){
            if(compare(left, right)<0)
                merged[i] = getData()[left++];
            else
                merged[i] = getData()[right++];
        }
        while (left < group1Start + group1Length)
            merged[i++] = getData()[left++];
        while (right < group2Start+group2Length)
            merged[i++] = getData()[right++];
        for(i = 0; i<merged.length; i++)
            getData()[group1Start+i] = merged[i];
        if(group1Start == 0 && group2Start+group2Length == getData().length){
            updateAndColorAllIndecies(Color.GREEN, waitTime);
            return;
        }
        else {
            for(i = 0; i<merged.length; i++)
                if(getPane().getPointColor(group1Start+i) == Color.GREEN)
                    break;
                else
                    setPoint(group1Start + i, true, waitTime);
        }
    }
}