package SortPane;

import javafx.concurrent.Service;

public class  SortPaneTaskService extends Service<Void> {
    private SortPaneTask task;

    public SortPaneTaskService(SortPaneTask task) {
        this.task = task;
    }

    @Override
    public SortPaneTask createTask() {
        return task;
    }
}

