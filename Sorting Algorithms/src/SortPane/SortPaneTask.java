package SortPane;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class SortPaneTask extends Task<Void> {
    private SortPane pane;

    public SortPaneTask(SortPane pane) {
        this.pane = pane;
    }
    public Void call(){
        pane.run();
        return null;
    }

}

