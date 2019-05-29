package SortPane.HorizontalSortPane.BarSortPane.PointSortPane;

import SortPane.HorizontalSortPane.BarSortPane.HorizontalSortPane;
import SortPane.SortPane;
import SortingAlgorithms.SortingAlgorithm;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public abstract class PointSortPane<T extends SortingAlgorithm> {//extends HorizontalSortPane { // Abstract to allow for compiling
    protected Rectangle[] points;
    public PointSortPane(T algorithm, double[] data){
       // super(algorithm, data);

    }
}
