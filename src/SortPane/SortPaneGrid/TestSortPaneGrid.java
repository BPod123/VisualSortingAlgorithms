package SortPane.SortPaneGrid;

import SortPane.HorizontalSortPane.BarSortPane.BarSortPane;
import SortingAlgorithms.*;
import SortingAlgorithms.HeapSort.MaxHeapSort;
import SortingAlgorithms.HeapSort.MinHeapSort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static Application.ApplicationPresets.*;
import static SortingAlgorithms.SortingAlgorithm.*;


public class TestSortPaneGrid extends Application { //https://www.developer.com/java/data/multithreading-in-javafx.html

    @Override
    public void start(Stage primaryStage){
        SortPaneGrid grid;// = new SortPaneGrid();
        double[] data = createDataSet(numDataPoints);
        BarSortPane[] panes = {
                new BarSortPane(new QuickSort(), createArrayCopy(data)),
                new BarSortPane(new ParallelQuickSort(), createArrayCopy(data)),
                new BarSortPane(new MergeSort(), createArrayCopy(data)),
                new BarSortPane(new ParallelMergeSort(), createArrayCopy(data)),
                new BarSortPane(new SelectionSort(), createArrayCopy(data)),
                new BarSortPane(new MaxHeapSort(), createArrayCopy(data)),
                new BarSortPane(new MinHeapSort(), createArrayCopy(data)),
                new BarSortPane(new InsertionSort(), createArrayCopy(data)),
                new BarSortPane(new CocktailShakerSort(), createArrayCopy(data)),
                new BarSortPane(new DoubleSelectionSort(), createArrayCopy(data)),
                new BarSortPane(new ShellSort(), createArrayCopy(data)),
                new BarSortPane(new BubbleSort(), createArrayCopy(data))

        };
        grid = new SortPaneGrid(panes);
        VBox frame = new VBox();
        Button sortAll = new Button("Sort all at once");
        Button sortOne = new Button("Sort one at a time");
        frame.getChildren().addAll(sortAll, sortOne, grid.getPane());
        sortAll.setOnAction(event -> {
            SortPaneGrid.runAllAtOnce = true;
           Thread thread = new Thread(grid);
            System.out.println("Sorting");
            thread.start();
            sortAll.setOnAction(e ->{});
        });
        sortOne.setOnAction(event -> {
            SortPaneGrid.runAllAtOnce = false;
            waitTime = Math.min(waitTime, 50);
            Thread thread = new Thread(grid);
            thread.start();
            System.out.println("Sorting");
            sortOne.setOnAction(e ->{});
        });
        Scene scene = new Scene(frame);
        //BarSortPane secondQuickSort = new BarSortPane(new QuickSort(), createArrayCopy(data));
        //((Pane)scene.getRoot()).getChildren().add((secondQuickSort));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Test SortPaneGrid");
        primaryStage.show();


    }
    public static void main(String[]args){
        launch(args);
    }
}
