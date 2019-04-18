package SortPane;

import Application.SortApplication;
import SortingAlgorithms.SortingAlgorithm;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static Application.SortApplication.*;

public abstract class SortPane<T extends SortingAlgorithm> extends VBox implements Runnable{
    private double[] data;
    private double[] originalData;
    private VBox pane = new VBox();
    private Label title;
    private SortingAlgorithm algorithm;

    public SortPane(T algorithm, double[] data){
        this.title = new Label(algorithm.getTitle());
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(0, 0, 15, 0));
        this.getChildren().add(pane);
        pane.getChildren().add(title);
        pane.setMaxWidth(paneWidth);
        pane.setMaxHeight(paneHeight*1.1);
        this.algorithm = algorithm;
        this.data = data;
        originalData = data;
        this.setStyle("-fx-background-color: black;"); // Set a black background
        //this.getChildren().add(pane);
    }
    @Override
    public void run(){
        algorithm.setPane(this);
        algorithm.run();
    }
    public abstract void refresh();
    public abstract void selectPoint(int index);
    public abstract void disselectPoint(int index);
    public abstract void swap(int index1, int index2);
    public abstract SortPane getResetPane();
    public void reset(){
        SortPane newPane = getResetPane();
        getPane().getChildren().clear();
        getPane().getChildren().addAll(newPane.getChildren());
    }
    public int compare(int index1, int index2){
       /* System.out.print("\n");
        System.out.print("Comparing:\t"+getData()[index1]+"\t");
        System.out.println(getData()[index2]);*/
        selectPoint(index1);
        selectPoint(index2);
        try {
            Thread.sleep(waitTime);
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }

        int res =  new Double(getData()[index1]).compareTo(getData()[index2]);

        try {
            Thread.sleep(waitTime);
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }
        disselectPoint(index1);
        disselectPoint(index2);
        return res;
    }
    // Setters and Getters
    public double[] getOriginalData(){
        return originalData;
    }
    public double[] getData() {
        return data;
    }

    public double getPaneHeight() {
        return paneHeight;
    }

    public double getPaneWidth() {
        return paneWidth;
    }

    public Label getTitle() {
        return title;
    }

    public VBox getPane(){
        return pane;
    }
    public int getNumPoints(){
        return getData().length;
    }
    public SortingAlgorithm getAlgorithm(){
        return algorithm;
    }
    public void setAlgorithm(SortingAlgorithm algorithm){
        this.algorithm = algorithm;
    }
    public void setData(double[] newData){
        this.data = newData;
    }
}
