package SortPane.BarSortPane;

import SortingAlgorithms.BubbleSort;
import SortingAlgorithms.QuickSort;
import SortingAlgorithms.SelectionSort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;

import static Application.SortApplication.numDataPoints;
import static Application.SortApplication.paneHeight;
import static SortingAlgorithms.SortingAlgorithm.createDataSet;
import static SortingAlgorithms.SortingAlgorithm.createWorstCase;

public class TestBarSortPane extends Application {
    public static BarSortPane bsp;
    @Override
    public void start(Stage primaryStage) {
        bsp = new BarSortPane(new BubbleSort(), createDataSet(numDataPoints));

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        // VBox Stuff
        Label label = new Label("There are " + bsp.getData().length + " Indices.");
        vBox.getChildren().addAll(label, hBox, bsp);
        // HBox stuff
        Button sortButton = new Button("Sort");
        Button resetButton = new Button("Reset");
        Button toggleSelect = new Button("Select");
        Button swapButton = new Button("Swap");
        TextField tf1 = new TextField("1");
        TextField tf2 = new TextField("2");
        tf1.setMaxWidth(50);
        tf2.setMaxWidth(50);
        hBox.getChildren().addAll(sortButton, resetButton, toggleSelect, swapButton, tf1, tf2);

        sortButton.setOnAction(e -> {
            bsp.run();
        });
        resetButton.setOnAction(e -> {
            bsp.reset();
        });
        toggleSelect.setOnAction(e -> {
            if (tf1.getText().length() > 0) {
                int t1 = Integer.parseInt(tf1.getText());
                bsp.rectangles[t1].setFill(bsp.rectangles[t1].getFill() == Color.WHITE ? Color.GREEN : Color.WHITE);
            }
            if (tf2.getText().length() > 0) {
                int t2 = Integer.parseInt(tf2.getText());
                bsp.rectangles[t2].setFill(bsp.rectangles[t2].getFill() == Color.WHITE ? Color.GREEN : Color.WHITE);
            }
        });
        swapButton.setOnAction(e -> {
            int index1 = Integer.parseInt(tf1.getText().toString());
            int index2 = Integer.parseInt(tf2.getText().toString());
            bsp.swap(index1, index2);
        });
        tf1.setOnAction(toggleSelect.getOnAction());
        tf2.setOnAction(tf1.getOnAction());
        primaryStage.setScene(new Scene(vBox));
        primaryStage.setTitle("BarSortPane Test");
        primaryStage.show();
    }
    public static void main(String[] args){
        //bsp.setAlgorithm(new SelectionSort());
        launch(args);
    }

}
