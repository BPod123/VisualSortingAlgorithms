package SortPane.SortPaneGrid;

import SortPane.SortPane;
import SortPane.SortPaneTask;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import SortPane.SortPaneTaskService;
/** Grid for multiple SortPanes. Will sort them in parallel*/
public class SortPaneGrid extends GridPane implements Runnable {
    public static boolean runAllAtOnce = false;
    private SortPane[] panes;
    private GridPane gridPane = new GridPane();
    private int panesPerLine;
    // Coordinates for next pane to be added
    private int nextX = 0;
    private int nextY = 0;
    public SortPaneGrid(SortPane[] panes){
        this.panes = panes;
        gridPane.setHgap(5);
        this.panesPerLine = findPanesPerLine(panes.length);
        addAll(this.panes);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
    }
    /*public SortPaneGrid(ArrayList<SortPane> list){
        panes = new SortPane[list.size()];
        for(int i = 0; i<panes.length; i++)
            panes[i] = list.get(i);
        this.panesPerLine = findPanesPerLine(panes.length);
        addAll(this.panes);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
    }*/
    public SortPaneGrid(SortPane[] panes, int panesPerLine) {
        this.panes = panes;
        gridPane.setHgap(5);
        this.panesPerLine = panesPerLine;
        addAll(this.panes);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
    }
    public int findPanesPerLine(int numPanes){

        if(numPanes<3)
            return 1;
        if(numPanes%3==0)
            return 3;
        return 4;
    }

    public void add(SortPane newPane) {
        if (nextX >= this.panesPerLine) {
            nextX = 0;
            nextY++;
        }
        this.gridPane.add(newPane, nextX++, nextY);
        SortPane[] newArray = new SortPane[panes.length + 1];
        for (int i = 0; i < panes.length; i++)
            newArray[i] = panes[i];
    }

    public void addAll(SortPane[] newPanes) {
        for (int i = 0; i < newPanes.length; i++) {
            add(newPanes[i]);
        }
    }

    public GridPane getPane() {
        return gridPane;
    }

    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        if (runAllAtOnce)
            executor = Executors.newCachedThreadPool();
        for (int i = 0; i<panes.length; i++) {
            //executor.execute(new SortPaneTask(p));
            Thread thread = new Thread(new SortPaneTaskService(new SortPaneTask(panes[i])).createTask());
            //thread.setPriority(Math.max(Thread.NORM_PRIORITY-i, Thread.MIN_PRIORITY));
            executor.execute(thread);
        }
    }

    private class SortTask extends Task<Void> {
        private SortPane[] pane;

        public SortTask(SortPane[] pane) {
            this.pane = pane;
        }

        public Void call() {
            if (pane.length == 1) {

                new Thread(pane[0]).start();
            } else {
                // Divide into 2 arrays and sort
                SortPane[] firstHalf = new SortPane[pane.length / 2];
                SortPane[] secondHalf = new SortPane[pane.length - pane.length / 2];

                System.arraycopy(pane, 0, firstHalf, 0, pane.length / 2);
                System.arraycopy(pane, pane.length - pane.length / 2, secondHalf, 0,
                        pane.length - pane.length / 2);
                SortTask firstTask = new SortTask(firstHalf);
                SortTask secondTask = new SortTask(secondHalf);

                firstTask.run();
                secondTask.run();
            }
            return null;
        }
    }
}