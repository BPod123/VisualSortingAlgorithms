package SortPane;

import SortPane.SortPane;

/** Class for storing events (comparisons of 2 indices and swaps of indices*/
public class PaneEvent {
    private int index1;
    private double index1Height;
    private int index2;
    private double index2Height;
    private boolean isSwap;
    private int operation;
    public PaneEvent(boolean isSwap){
        this.isSwap = isSwap;
    }
    public PaneEvent(int i1, int i2, boolean isSwap){
        index1 = i1;
        this.index1Height = index1Height;
        index2 = i2;
        this.index2Height = index2Height;
        isSwap = isSwap;
    }
    public void setIsSwap(boolean trueIfSwap){
        isSwap = trueIfSwap;
    }
    public boolean isSwap(){
        return isSwap;
    }
    public boolean isComparison(){
        return !isSwap;
    }
    public int getIndex1(){
        return index1;
    }
    public int getIndex2(){
        return index2;
    }
    public double getIndex1Height(){
        return index1Height;
    }
    public double getIndex2Height(){
        return index2Height;
    }
    public void setIndex1Height(double height){
        index1Height = height;
    }
    public void setIndex2Height(double height){
        index2Height = height;
    }
    public void setIndex1(int index){
        index1 = index;
    }
    public void setIndex2(int index){
        index2 = index;
    }
}
