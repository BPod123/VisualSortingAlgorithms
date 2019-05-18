package Application;

import SortPane.SortPaneGrid.SortPaneGrid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SortingApplication extends Application{
    private SortPaneGrid grid;
    public SortingApplication(SortPaneGrid SPG){
        grid = SPG;
    }
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Visual Sorting Algorithms");
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();

    }
    public void main(){
        start(new Stage());
    }
}
