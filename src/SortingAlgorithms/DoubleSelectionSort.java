package SortingAlgorithms;

import javafx.scene.paint.Color;

public class DoubleSelectionSort extends SortingAlgorithm {
    public DoubleSelectionSort(){
        super("Double Selection Sort");
    }

    @Override
    public String getTitle(){
        return "Double Selection Sort";
    }
    @Override
    public void run() {
        sort(); // Sort
        long frameRate = super.getFrameRateMillis();
        super.setFrameRateMillis(100);
        super.setVisibility(false);
        sort(); // Go back and fix errors
        //sort();
        super.setVisibility(true);
        super.setFrameRateMillis(frameRate);
        updateAndColorAllIndecies(Color.GREEN,25);

    }
    public void sort() {
        int max = getData().length-1, min = 0;
        for(int leftEnd = 0, rightEnd = getData().length-1, i; rightEnd-leftEnd>0; rightEnd--, leftEnd++){
            for(i = leftEnd, max = rightEnd, min = leftEnd; i<=rightEnd; i++){
                if(compare(i, min)<0)
                    min = i;
                if(compare(i, max)>0)
                    max = i;
            }
            swap(leftEnd, min);
            try{
                Thread.sleep(200);
                Thread.yield();
                Thread.sleep(getFrameRateMillis());
            }
            catch(InterruptedException ex){}
            swap(rightEnd, max);
            if(super.getVisibility()) {
                selectPoint(leftEnd, Color.GREEN);
                selectPoint(rightEnd, Color.GREEN);
            }
            boolean originalVisibility = getVisibility();
            setVisibility(false);
            updateAllIndecies(0);
            setVisibility(originalVisibility);
        }
        boolean originalVisibility = getVisibility();
        setVisibility(false);
        updateAllIndecies( 5);
        setVisibility(originalVisibility);
        updateAndColorAllIndecies(Color.GREEN, getFrameRateMillis());
    }
}