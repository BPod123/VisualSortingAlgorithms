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
    private TextArea textArea = new TextArea();
    private Scene scene;
    private ArrayList<String> selectedAlgorithms = new ArrayList<>();
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
            for(String title : ALL_ALGORITHMS.keySet()) {
                selectedAlgorithms.add(title);
                textArea.setText(textArea.getText().concat("\n".concat(title)));
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
            WriteFile(primaryStage);
        });
        rightSide.getChildren().add(startButton);

// Left Side
        // HBoxes
/*        HBox paneHeightBox = new HBox();
        HBox paneWidthBox = new HBox();
        HBox numPointsBox = new HBox();
        HBox panesPerLineBox = new HBox();*/
        for(String title: ALL_ALGORITHMS.keySet())
            options.getChildren().add(new AlgorithmHBox<>(title));
        scene = new Scene(everythihngBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Settings");
        primaryStage.show();
    }
    @Override
    public void run(){
        Application.launch();
    }
    public synchronized void WriteFile(Stage primaryStage){
        primaryStage.hide();
        File file = new File("options.txt");
        try{
            if(file.exists())
                file.delete();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            for(String algorithm: selectedAlgorithms){
                writer.write("\n".concat(algorithm));
            }
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        try {
            super.stop();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


private class AlgorithmHBox<SortingAlgorithm> extends HBox { // Makes adding sorting algorithms easier
    private String algorithm;
    private ButtonBar buttonBar = new ButtonBar();
    private Button addAlgorithmButton = new Button("Add");
    private Button removeAlgorithmButton = new Button("Remove");
    private Label sortLabel;
    public AlgorithmHBox(String algorithm){
        this.setAlignment(Pos.CENTER_RIGHT);
        this.algorithm = algorithm;
        sortLabel = new Label(algorithm);
        buttonBar.getButtons().addAll(addAlgorithmButton, removeAlgorithmButton);
        this.getChildren().addAll(sortLabel, buttonBar);
        addAlgorithmButton.setOnAction(e->{
            int size = selectedAlgorithms.size();
            selectedAlgorithms.add(algorithm);
            if(size!=selectedAlgorithms.size())
            textArea.setText(textArea.getText().concat("\n".concat(algorithm)));
        });
        removeAlgorithmButton.setOnAction(e->{
            if(selectedAlgorithms.contains(algorithm)) {
                textArea.setText(textArea.getText().replaceAll("\n"+algorithm, ""));
                selectedAlgorithms.remove(algorithm);
            }
        });
    }
}
public static void main(String[] args){
        launch(args);
}
}
