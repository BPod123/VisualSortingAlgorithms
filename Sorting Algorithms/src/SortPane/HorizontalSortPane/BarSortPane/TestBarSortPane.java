package SortPane.HorizontalSortPane.BarSortPane;

import SortingAlgorithms.*;
import SortingAlgorithms.HeapSort.MaxHeapSort;
import SortingAlgorithms.HeapSort.MinHeapSort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Application.ApplicationPresets.*;
import static SortingAlgorithms.SortingAlgorithm.createDataSet;

public class TestBarSortPane extends Application {
    public static BarSortPane bsp;
    public static ExecutorService executor = Executors.newFixedThreadPool(1);
    private Thread sortPaneThread = new Thread(bsp);
    @Override
    public void start(Stage primaryStage) {
        bsp = new BarSortPane(new MaxHeapSort(), createDataSet(numDataPoints));

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        // VBox Stuff
        Label label = new Label("There are " + bsp.getData().length + " Indices.");
        vBox.getChildren().addAll(label, hBox, bsp);
        // HBox stuff
        Button sortButton = new Button("Sort");

        Button refreshButton = new Button("Refresh");

        TextField tf1 = new TextField("");

        tf1.setMaxWidth(50);

        hBox.getChildren().addAll(sortButton, refreshButton, tf1);
        Thread updater = new Thread(()->{
            try {
                    primaryStage.hide();
                    Thread.sleep(500);
                    primaryStage.show();
                    this.stop();
            }
            catch(Exception ex){}
        });
        sortButton.setOnAction(e -> {
            //executor.shutdown();
            Thread sortPaneThread = new Thread(bsp);
            executor.execute(sortPaneThread);
            executor.execute(updater);

        });
        refreshButton.setOnAction(e-> {
           // VBox newVBox = new VBox();
            //newVBox.getChildren().addAll(vBox.getChildren());
            primaryStage.hide();
            //primaryStage.setScene(new Scene(newVBox));
            primaryStage.show();

        });

        tf1.setOnAction(refreshButton.getOnAction());
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BarSortPane Test");
        primaryStage.show();

    }
    public static void main(String[] args){
        //waitTime = 5;
        paneWidth = 1300;
        paneHeight = 500;
        numDataPoints = 50;
        launch(args);
    }

}
