package Application;

import SortPane.HorizontalSortPane.BarSortPane.BarSortPane;
import SortPane.SortPane;
import SortPane.SortPaneGrid.SortPaneGrid;
import SortingAlgorithms.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import static Application.ApplicationPresets.ALL_ALGORITHMS;
import static Application.ApplicationPresets.numDataPoints;
import static SortingAlgorithms.SortingAlgorithm.createArrayCopy;
import static SortingAlgorithms.SortingAlgorithm.createDataSet;


public class PreRunOptionsApp extends Application implements Runnable {
    /*public static final SortingAlgorithm[] ALL_ALGORITHMS = {new QuickSort(), new ParallelQuickSort(), new MergeSort(), new ParallelMergeSort(),
            new DoubleSelectionSort(), new CocktailShakerSort(), new SelectionSort(), new MaxHeapSort(), new MinHeapSort(), new InsertionSort(), new ShellSort(),
            new BubbleSort()};*/
    private TextArea textArea = new TextArea();
    private Map<String, SortingAlgorithm> map;
    private ArrayList<SortingAlgorithm> selectedAlgorithms = new ArrayList<>();
    @Override
    public void start(Stage primaryStage){
        //textArea.setDisable(true);
        textArea.setStyle("-fx-background-color: black;-fx-fill-color: white;");

        HBox everythihngBox = new HBox();
        VBox options = new VBox();
        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER);
        HBox addOrRemoveAll = new HBox();
        everythihngBox.getChildren().addAll(options, rightSide);
        rightSide.getChildren().addAll(textArea, addOrRemoveAll);
// Right Side
        Button addAll = new Button("Add All");
        addAll.setOnAction(e->{
            textArea.setText("");
            for(int i = 0; i<ALL_ALGORITHMS.length; i++) {
                selectedAlgorithms.add(ALL_ALGORITHMS[i]);
                textArea.setText(textArea.getText().concat("\n".concat(ALL_ALGORITHMS[i].getTitle())));
            }
        });
        Button removeAll = new Button("Clear All");
        removeAll.setOnAction(e->{
            selectedAlgorithms.clear();
            textArea.setText("");
        });
      //  addOrRemoveAll.getButtons().add(addAll,ButtonBar.buttonD removeAll);
        addOrRemoveAll.getChildren().addAll(addAll, removeAll);
        addOrRemoveAll.setAlignment(Pos.CENTER);

        Button startButton = new Button("Run");
        startButton.setOnAction(e->{
            writeFileAndLaunch(primaryStage);
        });
        rightSide.getChildren().add(startButton);

// Left Side
        // HBoxes
/*        HBox paneHeightBox = new HBox();
        HBox paneWidthBox = new HBox();
        HBox numPointsBox = new HBox();
        HBox panesPerLineBox = new HBox();*/
        for(SortingAlgorithm s: ALL_ALGORITHMS)
            options.getChildren().add(new AlgorithmHBox<>(s));
        primaryStage.setScene(new Scene(everythihngBox));
        primaryStage.setTitle("Settings");
        primaryStage.show();
    }
    @Override
    public void run(){
        Application.launch();
    }
    public void writeFileAndLaunch(Stage primaryStage){
        primaryStage.hide();
        /*try{
            super.stop();
        }
        catch(Exception ex){}*/


        new SortingApplication(createNewPane()).start(primaryStage);

    }
    public SortPaneGrid createNewPane(){
            SortPane[] panes = new SortPane[selectedAlgorithms.size()];
            double[] data = createDataSet(numDataPoints);
            for(int i = 0; i<selectedAlgorithms.size(); i++)
                panes[i] = new BarSortPane(selectedAlgorithms.get(i), createArrayCopy(data));
            SortPaneGrid grid = new SortPaneGrid(panes);
            return grid;
    }
private class AlgorithmHBox<Sort extends SortingAlgorithm> extends HBox { // Makes adding sorting algorithms easier
    private Sort algorithm;
    private ButtonBar buttonBar = new ButtonBar();
    private Button addAlgorithmButton = new Button("Add");
    private Button removeAlgorithmButton = new Button("Remove");
    private Label sortLabel;
    public AlgorithmHBox(Sort algorithm){
        this.setAlignment(Pos.CENTER_RIGHT);
        this.algorithm = algorithm;
        sortLabel = new Label(algorithm.getTitle());
        buttonBar.getButtons().addAll(addAlgorithmButton, removeAlgorithmButton);
        this.getChildren().addAll(sortLabel, buttonBar);
        addAlgorithmButton.setOnAction(e->{
            int size = selectedAlgorithms.size();
            selectedAlgorithms.add(algorithm);
            if(size!=selectedAlgorithms.size())
            textArea.setText(textArea.getText().concat("\n".concat(algorithm.getTitle())));
        });
        removeAlgorithmButton.setOnAction(e->{
            if(selectedAlgorithms.contains(algorithm)) {
                textArea.setText(textArea.getText().replaceAll("\n"+algorithm.getTitle(), ""));
                selectedAlgorithms.remove(algorithm);
            }
        });
    }
}
public static void main(String[] args){
        launch(args);
}
}
