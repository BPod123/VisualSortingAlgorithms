package SortPane.SortPaneGrid;

import SortPane.BarSortPane.BarSortPane;
import SortingAlgorithms.BubbleSort;
import SortingAlgorithms.SelectionSort;
import SortingAlgorithms.SortingAlgorithm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashSet;

import static Application.SortApplication.numDataPoints;
import static Application.SortApplication.paneHeight;


public class TestSortPaneGrid extends Application {
    public double[] createDataSet(int numPoints){
        HashSet<Double> set = new HashSet<Double>();
        double[] res = new double[numPoints];
        while(set.size()<numPoints){
            set.add(new Double(Math.random()*paneHeight));
        }
        Object[] objs = set.toArray();
        for(int i = 0; i<res.length; i++)
            res[i] = Double.parseDouble(objs[i].toString());
        return res;
    }
    @Override
    public void start(Stage primaryStage){
        SortPaneGrid grid = new SortPaneGrid();
        double[] data = createDataSet(numDataPoints);
        BarSortPane bsp1 = new BarSortPane(new BubbleSort(), data);
        BarSortPane bsp2 = new BarSortPane(new SelectionSort(), data);
        grid.add(bsp1);
        grid.add(bsp2);

        primaryStage.setScene(new Scene(grid));
        primaryStage.setTitle("Test SortPaneGrid");
        primaryStage.show();
    }
}
