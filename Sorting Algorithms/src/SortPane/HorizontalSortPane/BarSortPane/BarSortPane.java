package SortPane.HorizontalSortPane.BarSortPane;

import SortPane.SortPane;
import SortPane.PaneEvent;
import SortingAlgorithms.SortingAlgorithm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Collections;

import static Application.ApplicationPresets.*;

/** This class is an extension of HBox and will provide an easy way to visualize sorting algorithms.
 * The data will be viewed as rectangles of different heights.
 * The user will just have to send in the data and the size of the pane.
 * There are methods for highlighting and moving bars.*/
public class BarSortPane<T extends SortingAlgorithm> extends SortPane {
    /** The data will be in the range of 0 - height before being passed in */
    protected Rectangle[] rectangles;
    protected HBox hBox = new HBox();

    /** Create a BarSortPane with a title, and the data represented by rectangles */
    public BarSortPane(T algorithm, double[] data) {
        super(algorithm, data);
        //**************************************
        hBox.setSpacing(1);
        hBox.setRotate(180); // Rotate Pane
        hBox.setScaleX(-1.0); // Invert Pane

        getSortPane().getChildren().add(hBox);
        hBox.setMaxWidth(paneWidth + getData().length);
        hBox.setMaxHeight(getMaxHeight());
        // *************************************
        rectangles = new Rectangle[data.length];
        double rectangleWidth = paneWidth / ((double) (data.length)) - 1;

        for (int i = 0; i < data.length; i++) {
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
    public void selectPoint(int index, Paint color){
        rectangles[index].setFill(color);
        Thread.yield();
    }
    @Override
    public void updatePoint(int index){
        rectangles[index].setHeight(getData()[index]);
    }
    @Override
    public Paint getPointColor(int index){
        return rectangles[index].getFill();
    }
    @Override
    public void disselectPoint(int index){
        rectangles[index].setHeight(getData()[index]);
        rectangles[index].setFill(Color.WHITE);
        Thread.yield();

    }
    //******************************************************************
    @Override
    public void swap(PaneEvent event){
        try {
            int index1 = event.getIndex1();
            int index2 = event.getIndex2();
            //System.out.println("Swapping:\t"+getData()[index1]+"\t"+getData()[index2]);
            selectPoint(index1, Color.RED);
            selectPoint(index2, Color.RED);


            //
            //rectangles[index2].setHeight(getData()[index2]);
            ObservableList<Node> children = FXCollections.observableArrayList(hBox.getChildren());
            Collections.swap(children, index1, index2);
            hBox.getChildren().setAll(children);
            Thread.yield();
            if(getAlgorithm().getVisibility())
            Thread.sleep(getAlgorithm().getFrameRateMillis());

            disselectPoint(index1);
            disselectPoint(index2);
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
    //******************************************************************
}
