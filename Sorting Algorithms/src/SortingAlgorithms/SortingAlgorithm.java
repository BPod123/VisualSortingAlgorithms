package SortingAlgorithms;

import SortPane.SortPane;

import java.util.HashSet;

import static Application.SortApplication.paneHeight;

public abstract class SortingAlgorithm implements Runnable {
    private SortPane pane;
    private int comparasons = 0;
    private int swaps = 0;
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
    public abstract String getTitle();
    public abstract void run();
    public void updateTitle(){
        getPane().getTitle().setText(getTitle()+"\n"+
                "\nComparisons:\t"+getComparasons()+ "\nSwaps:\t"+getSwaps());
    }
    /*
    public void pause(){
        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() < startTime + waitTime){}
    }*/
    public int getComparasons(){
        return comparasons;
    }
    public int getSwaps(){
        return swaps;
    }

    public void swap(int index1, int index2){
        getPane().swap(index1, index2);
        swaps++;
        updateTitle();
    }
    public int compare(int index1, int index2){
        comparasons++;
        int res = getPane().compare(index1, index2);
        updateTitle();
        return res;

    }
    public static double[] createDataSet(int numPoints){
        HashSet<Double> set = new HashSet<Double>();
        double[] res = new double[numPoints];
        while(set.size()<numPoints){
            set.add(new Double(Math.random()*paneHeight/2));
        }
        Object[] objs = set.toArray();
        for(int i = 0; i<res.length; i++)
            res[i] = Double.parseDouble(objs[i].toString());
        //double[] tests = {100.0, 90.0, 80.0, 70.0, 60.0, 50.0, 40.0, 30.0, 20.0, 10.0};
        return res;
    }
    public static double[] createWorstCase(int numPoints) {
        double[] res = new double[numPoints];
        for (int i = 0, num = numPoints; i < numPoints; i++, num--)
            res[i] = ((double)(num))/numPoints*paneHeight;
        return res;
    }
    public void selectPoint(int index){
        getPane().selectPoint(index);
    }
    public void diselectPoint(int index){
        getPane().disselectPoint(index);
    }
}
