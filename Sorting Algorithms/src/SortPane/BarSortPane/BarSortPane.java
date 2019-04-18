package SortPane.BarSortPane;

import SortPane.SortPane;
import SortingAlgorithms.SortingAlgorithm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Collections;

import static Application.SortApplication.*;

/** This class is an extension of HBox and will provide an easy way to visualize sorting algorithms.
 * The data will be viewed as rectangles of different heights.
 * The user will just have to send in the data and the size of the pane.
 * There are methods for highlighting and moving bars.*/
public class BarSortPane<T extends SortingAlgorithm> extends SortPane {
    /** The data will be in the range of 0 - height before being passed in */
    protected Rectangle[] rectangles;
    private HBox hBox = new HBox();
    /** Create a BarSortPane with a title, and the data represented by rectangles */
    public BarSortPane(T algorithm, double[] data){
        super(algorithm, data);
        hBox.setRotate(180); // Rotate Pane
        hBox.setScaleX(-1.0); // Invert Pane
        getPane().getChildren().add(hBox);
        hBox.setMaxWidth(paneWidth);
        hBox.setMaxHeight(paneHeight);

        rectangles = new Rectangle[data.length];
        double rectangleWidth = paneWidth/((double)(data.length));

        for(int i = 0; i<data.length; i++) {
            rectangles[i] = new Rectangle(rectangleWidth, data[i], Color.WHITE);
        }
        hBox.getChildren().addAll(rectangles);
    }
    @Override
    public BarSortPane getResetPane(){
     getAlgorithm().setData(getOriginalData());
      BarSortPane newPane = new BarSortPane(getAlgorithm(), getOriginalData());
     return newPane;
    }
    @Override
    public void selectPoint(int index){
        rectangles[index].setFill(Color.GREEN);
    }
    @Override
    public void disselectPoint(int index){
        rectangles[index].setFill(Color.WHITE);
    }
    @Override
    public void swap(int index1, int index2){
        //System.out.println("Swapping:\t"+getData()[index1]+"\t"+getData()[index2]);
        selectPoint(index1);
        selectPoint(index2);

        try {
            Thread.sleep(waitTime);
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }

        ObservableList<Node> children= FXCollections.observableArrayList(hBox.getChildren());
        Collections.swap(children, index1, index2);
        hBox.getChildren().setAll(children);
        double temp = getData()[index2];
        getData()[index2] = getData()[index1];
        getData()[index1] = temp;
        Rectangle tempRectangle = rectangles[index1];
        rectangles[index1] = rectangles[index2];
        rectangles[index2] = tempRectangle;
        //refresh();

        try {
            Thread.sleep(waitTime);
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }

        disselectPoint(index1);
        disselectPoint(index2);
    }
    @Override
    public void refresh(){
        Pane copy = new VBox();
        copy.getChildren().addAll(getPane().getChildren());
        getPane().getChildren().clear();
        getPane().getChildren().addAll(copy.getChildren());
    }
}
