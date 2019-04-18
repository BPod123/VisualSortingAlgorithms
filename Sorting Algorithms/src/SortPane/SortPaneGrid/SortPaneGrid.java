package SortPane.SortPaneGrid;

import SortPane.SortPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/** Grid for multiple SortPanes. Will sort them in parallel*/
public class SortPaneGrid extends GridPane {
    private SortPane[] panes;
    private GridPane gridPane = new GridPane();
    private int panesPerLine;
    // Coordinates for next pane to be added
    private int nextX = 0;
    private int nextY = 0;
    public SortPaneGrid(){}
    public SortPaneGrid(SortPane[] panes, int panesPerLine){
        this.panes = panes;
        this.panesPerLine = panesPerLine;
        this.gridPane = new GridPane();
        addAll(this.panes);
    }
    public void add(SortPane newPane){
        this.gridPane.add(newPane, nextX++, nextY);
        if(nextX>=this.panesPerLine){
            nextX = 0;
            nextY++;
        }

    }
    public void addAll(SortPane[] newPanes){
        for(int i = 0; i<newPanes.length; i++){
            if(nextX>=this.panesPerLine) {
                nextX = 0;
                nextY++;
            }
            gridPane.add(panes[i], nextX++, nextY++);
        }
    }
    public void sort(){
        RecursiveAction mainTask = new SortTask(panes);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }
    private class SortTask extends RecursiveAction {
        private SortPane[] pane;

        public SortTask(SortPane[] pane) {
            this.pane = pane;
        }

        public void compute() {
            if (pane.length == 1) {
                pane[0].run();
            } else {
                // Divide into 2 arrays and sort
                SortPane[] firstHalf = new SortPane[pane.length / 2];
                SortPane[] secondHalf = new SortPane[pane.length - pane.length / 2];

                System.arraycopy(pane, 0, firstHalf, 0, pane.length / 2);
                System.arraycopy(pane, pane.length - pane.length / 2, secondHalf, 0,
                        pane.length - pane.length / 2);
                // Make new SortTasks with the half lists
                invokeAll(new SortTask(firstHalf), new SortTask(secondHalf));
            }

        }

    }
}
