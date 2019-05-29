package SortPane.HorizontalSortPane.BarSortPane;

import SortPane.SortPane;
import SortingAlgorithms.SortingAlgorithm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.util.Collections;
import SortPane.PaneEvent;
import javafx.scene.shape.Shape;

import static Application.ApplicationPresets.paneWidth;
import static Application.ApplicationPresets.waitTime;

public abstract class HorizontalSortPane<T extends SortingAlgorithm> extends SortPane {
    private HBox pane;
    private Shape[] points;
    public HorizontalSortPane(T algorithm, double[] data, Shape[] points){
        super(algorithm, data);
        pane.setSpacing(1); // Add a little space between points
        pane.setRotate(180); // Rotate Pane
        pane.setScaleX(-1.0); // Invert Pane
        super.getSortPane().getChildren().add(pane);
        pane.setMaxWidth(paneWidth + getData().length);
        pane.setMaxHeight(getMaxHeight());
        this.points = points;
        pane.getChildren().addAll(this.points);
    }
    /** @return returns the pane that has the visual points being manipulated*/
    public HBox getActionPane(){
        return pane;
    }
    public double getPointWidth(){
        return paneWidth / ((double) (super.getData().length)) - 1;
    }
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
            ObservableList<Node> children = FXCollections.observableArrayList(pane.getChildren());
            Collections.swap(children, index1, index2);
            pane.getChildren().setAll(children);
            Thread.yield();
            Thread.sleep(waitTime);

            disselectPoint(index1);
            disselectPoint(index2);
        }
        catch(InterruptedException ex){
            ex.printStackTrace();
        }

    }
}
