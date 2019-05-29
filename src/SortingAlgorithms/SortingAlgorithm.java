package SortingAlgorithms;

import SortPane.SortPane;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

import SortPane.PaneEvent;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static Application.ApplicationPresets.paneHeight;
import static Application.ApplicationPresets.waitTime;

public abstract class SortingAlgorithm implements Runnable {
    public static Semaphore semaphore = new Semaphore(1);
    private int numPermits = 1; // The number of permits that a sorting algorithm needs, higher number if it is more inclined to mess up in the UI
    private SortPane pane;
    private long frameRateMillis = waitTime;
    private boolean visibility = true;
    private String title;
    private SimpleIntegerProperty comparisons = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty swaps = new SimpleIntegerProperty(0);
    private int[] mostRecentlyComparedIndecies = new int[2];
    public void setPane(SortPane pane){
        this.pane = pane;
    }
    public SortPane getPane(){
        return pane;
    }
    public void setData(double[] newData){
        pane.setData(newData);
    }
    public double[] getData(){
        return pane.getData();
    }

    public abstract void run();

    public SortingAlgorithm(String title){
        this.title = title;
    }

    public void clear(){
        this.pane = null;

    }
    public long getFrameRateMillis(){
        return frameRateMillis;
    }
    public void setFrameRateMillis(long newTime){
        frameRateMillis = newTime;
    }
    public int getNumPermits(){return numPermits;}
    public void setNumPermits(int num){
        numPermits = num;
    }

      public SimpleIntegerProperty getComparisons(){
        return comparisons;
    }
    public SimpleIntegerProperty getSwaps(){
        return swaps;
    }
    public boolean getVisibility(){
        return visibility;
    }
    public void setVisibility(boolean newVisibilityStatus){
        visibility = newVisibilityStatus;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String newTitle){
        title = newTitle;
    }
    @Override
    public String toString(){
        return title;
    }



    public void swap(int index1, int index2) {
        PaneEvent event = new PaneEvent(index1, index2, true);
        double temp = getData()[index1];
        getData()[index1] = getData()[index2];
        getData()[index2] = temp;
        swaps.add(1);
        //pane.getSwapsNumber().setText(""+getSwaps());
        try {
            semaphore.acquire(numPermits);
            pane.processEvent(event);
        } catch (InterruptedException ex) {
        } finally {
            semaphore.release(numPermits);
        }
    }

    public int compare(int index1, int index2){
        if(getVisibility()) {
            comparisons.add(1);

            if (mostRecentlyComparedIndecies[0] != index1 && mostRecentlyComparedIndecies[0] != index2
                    && getPane().getPointColor(mostRecentlyComparedIndecies[0]) == Color.RED)
                disselectPoint(mostRecentlyComparedIndecies[0]);
            if (mostRecentlyComparedIndecies[1] != index1 && mostRecentlyComparedIndecies[1] != index2
                    && getPane().getPointColor(mostRecentlyComparedIndecies[1]) == Color.RED)
                disselectPoint(mostRecentlyComparedIndecies[1]);
            mostRecentlyComparedIndecies[0] = index1;
            mostRecentlyComparedIndecies[1] = index2;

            //pane.getComparisonsNumber().setText(""+getComparisons());

            try {
                PaneEvent event = new PaneEvent(index1, index2, false);
                semaphore.acquire(numPermits);
                pane.processEvent(event);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                semaphore.release(numPermits);
                Thread.yield();
            }
        }
        if(getData()[index1]>getData()[index2])
            return 1;
        else
            if(getData()[index2]>getData()[index1])
                return -1;
            else
                return 0;
    }
    /** Updates a point, but adds 1 to the swaps count*/
    public void setPoint(int index, boolean visible, long pauseLength){
        swaps.add(1);
        updatePoint(index, pauseLength);
    }
    /** Used to correct the UI when Javafx messes up.
     */
    public void updatePoint(int index, long pauseLength){
        Paint originalColor = getPane().getPointColor(index);
        try{
        semaphore.acquire(numPermits);
        if(getVisibility())
        selectPoint(index, Color.RED);
        getPane().updatePoint(index);
            Thread.sleep(pauseLength);
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }
        finally{
            if(getVisibility())
                selectPoint(index, originalColor);
            semaphore.release(numPermits);
        }

    }
    public void updateAllIndecies(long pauseLength){
        for(int i = 0; i<getData().length; i++)
            updatePoint(i, pauseLength);
       // getPane().updateAllPoints();
    }
    public void updateAndColorAllIndecies(Paint color, long pauseLength){
        for(int i = 0; i<getData().length; i++) {
            updatePoint(i, pauseLength);
            selectPoint(i, color);
        }
    }

    public static double[] createDataSet(int numPoints){
        HashSet<Double> set = new HashSet<Double>();
        double[] res = new double[numPoints];
        while(set.size()<numPoints){
            set.add((Math.random()*paneHeight/3));
        }
        Object[] objs = set.toArray();
        for(int i = 0; i<res.length; i++)
            res[i] = Double.parseDouble(objs[i].toString());

        //double[] tests = {100.0, 90.0, 80.0, 70.0, 60.0, 50.0, 40.0, 30.0, 20.0, 10.0};
        return res;
    }
    public static double[] createReverseOrder(int numPoints) {
        double[] res = new double[numPoints];
        for (int i = 0, num = numPoints; i < numPoints; i++, num--)
            res[i] = (int)(((double)(num))/numPoints*paneHeight/2);
        return res;
    }
    public static double[] createArrayCopy(double[] data){
        double[] copy = new double[data.length];
        for(int i = 0; i<copy.length; i++)
            copy[i] = data[i];
        return copy;
    }
    public void selectPoint(int index, Paint color)
    {
        getPane().selectPoint(index, color);

    }
    public void disselectPoint(int index)
    {
        if(getVisibility())
        getPane().disselectPoint(index);
    }

}
