package Application;

import SortPane.HorizontalSortPane.BarSortPane.BarSortPane;
import SortPane.SortPane;
import SortPane.SortPaneGrid.SortPaneGrid;
import SortingAlgorithms.*;
import SortingAlgorithms.HeapSort.MaxHeapSort;
import SortingAlgorithms.HeapSort.MinHeapSort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static Application.ApplicationPresets.*;
import static SortingAlgorithms.SortingAlgorithm.createArrayCopy;
import static SortingAlgorithms.SortingAlgorithm.createDataSet;

public class SortingApplication extends Application{
     ArrayList<String> selectedAlgorithms = new ArrayList<>();
    @Override
    public void start(Stage primaryStage){

        readOptions();
        selectedAlgorithms.remove(0);
        double[] data = createDataSet(numDataPoints);
        BarSortPane[] panes = new BarSortPane[selectedAlgorithms.size()];
        for(int i = 0; i<selectedAlgorithms.size(); i++)
            panes[i] = new BarSortPane(ApplicationPresets.getSortingAlgorithm(selectedAlgorithms.get(i)), createArrayCopy(data));
        SortPaneGrid grid = new SortPaneGrid(panes);
        VBox frame = new VBox();
        Button sortAll = new Button("Sort all at once");
        Button sortOne = new Button("Sort one at a time");
        Button stop = new Button("Exit");
        Button refresh = new Button("Refresh");
        HBox buttonBar = new HBox();
        buttonBar.getChildren().addAll(stop, refresh, sortAll, sortOne);

        frame.getChildren().addAll(buttonBar, grid.getPane());
        stop.setOnAction(e->{
          primaryStage.hide();
          try {
              super.stop();
          }
          catch(Exception ex){}
        });
        refresh.setOnAction(e->{
          primaryStage.hide();
          primaryStage.show();
        });
        sortAll.setOnAction(event -> {
            SortPaneGrid.runAllAtOnce = true;
            Thread thread = new Thread(grid);
            System.out.println("Sorting");
            thread.start();
            sortAll.setOnAction(e ->{});
        });
        sortOne.setOnAction(event -> {
            SortPaneGrid.runAllAtOnce = false;
            //waitTime = Math.min(waitTime, 50);
            Thread thread = new Thread(grid);
            thread.start();
            System.out.println("Sorting");
            sortOne.setOnAction(e ->{});
        });
        Scene scene = new Scene(frame);
        //BarSortPane secondQuickSort = new BarSortPane(new QuickSort(), createArrayCopy(data));
        //((Pane)scene.getRoot()).getChildren().add((secondQuickSort));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Visual Sorting Algorithms");
        primaryStage.show();


    }
    public void readOptions(){
        File file = new File("options.txt");
        try{
                Scanner scan = new Scanner(file);
                while(scan.hasNext()){
                    String nextLine = scan.nextLine();
                    if(nextLine!="")
                        selectedAlgorithms.add(nextLine);
                }
                file.deleteOnExit();
                scan.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("options not found.");
        }
        finally{
            file.delete();
        }
    }
     SortPaneGrid createNewPane(){
         selectedAlgorithms.remove(0);
         SortPane[] panes = new SortPane[selectedAlgorithms.size()];
         double[] data = createDataSet(numDataPoints);
         for(int i = 0; i<selectedAlgorithms.size(); i++)
             panes[i] = new BarSortPane(ALL_ALGORITHMS.get(selectedAlgorithms.get(i)), createArrayCopy(data));
         SortPaneGrid grid = new SortPaneGrid(panes);
         return grid;
 }
    public static void main(String[] args){
        Application.launch(args);
    }
}
