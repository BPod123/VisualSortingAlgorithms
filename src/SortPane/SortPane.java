package SortPane;

import SortingAlgorithms.SortingAlgorithm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;

import static Application.ApplicationPresets.*;

public abstract class SortPane<T extends SortingAlgorithm> extends Pane implements Runnable{
    private double[] data;
    private double[] originalData;
    private Label title;
    private VBox pane = new VBox();
    private Label statisticsTitleLabel = new Label("Comparisons:\t\nSwaps:\t");
    private Label comparisonsLabel = new Label();
    private Label swapsLabel = new Label();
    private T algorithm;
    private ArrayList<Integer> currentlySelectedInecies = new ArrayList<Integer>();
    public SortPane(T algorithm, double[] data){
        pane.setPadding(new Insets(10, 0, 0, 0));
        this.algorithm = algorithm;
        this.data = data;
        originalData = data;
        // Set Labels
        this.title = new Label(algorithm.getTitle());
        title.setTextFill(Color.WHITE);
        title.setAlignment(Pos.CENTER);
      pane.getChildren().add(title);
      //Statistics stuff
        comparisonsLabel.setTextFill(Color.WHITE);
        swapsLabel.setTextFill(Color.WHITE);
        //comparisonsLabel.textProperty().bind(algorithm.getComparisons().asString());
        //swapsLabel.textProperty().bind(algorithm.getSwaps().asString());
        statisticsTitleLabel.setTextFill(Color.WHITE);
        HBox statisticsInfoHBox = new HBox();
        statisticsInfoHBox.setStyle("-fx-background-color: black;");
        VBox statisticsVBox = new VBox();
        statisticsVBox.getChildren().addAll(comparisonsLabel,swapsLabel);
        //statisticsInfoHBox.getChildren().addAll(statisticsTitleLabel,statisticsVBox);
        pane.getChildren().add(statisticsInfoHBox);
        //

        this.setMaxWidth(Math.max(paneWidth, data.length));
        this.setHeight(paneHeight+title.getHeight()+statisticsVBox.getHeight());
        this.setStyle("-fx-background-color: black;"); // Set a black background
        this.getChildren().add(pane);
    }
    public double getPaneHeight(){
        return paneHeight-title.getHeight()-Math.max(comparisonsLabel.getHeight()+swapsLabel.getHeight(), statisticsTitleLabel.getHeight());
    }
    @Override
    public void run(){
        // Set up for run
        this.data = this.originalData;
        algorithm.clear();
        algorithm.setPane(this);
         algorithm.run();
       /* Thread thread = new Thread(algorithm);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        while(thread.isAlive())
            Thread.yield();*/

    }

    public abstract void selectPoint(int index, Paint color);
    public abstract void disselectPoint(int index);
    public abstract void swap(PaneEvent event);
    public abstract SortPane getResetPane();
    public abstract Paint getPointColor(int index);
    public abstract void updatePoint(int index);
    public void processEvent(PaneEvent event) {
        Thread.yield();
        try {
            semaphore.acquire(algorithm.getNumPermits());
            if (event.isSwap())
                swap(event);
            else
                compare(event);
            updateStatistics();
        }
        catch(InterruptedException ex){}
        finally{
            semaphore.release(algorithm.getNumPermits());
        }
    }
    public void reset(){
        SortPane newPane = getResetPane();
        getSortPane().getChildren().clear();
        getSortPane().getChildren().addAll(newPane.getChildren());
    }
    public void updateStatistics(){
        //comparisonsLabel.textProperty().bind(algorithm.getComparisons().asString());
        //swapsLabel.textProperty().bind(algorithm.getComparisons().asString());
        //comparisonsLabel.setText(algorithm.getComparisons().getValue().toString());
        //swapsLabel.setText(algorithm.getSwaps().getValue().toString());
    }
    public void compare(PaneEvent event) {
        try {
            selectPoint(event.getIndex1(),Color.RED);
            selectPoint(event.getIndex2(), Color.RED);
            if(algorithm.getVisibility())
            Thread.sleep(algorithm.getFrameRateMillis());
            disselectPoint(event.getIndex1());
            disselectPoint(event.getIndex2());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
    // Setters and Getters
    public double[] getOriginalData(){
        return originalData;
    }
    public ArrayList<Integer> getCurrentlySelectedInecies(){
        return currentlySelectedInecies;
    }
    public double[] getData() {
        return data;
    }
    public Label getTitle() {
        return title;
    }

    public VBox getSortPane(){
        return pane;
    }
    public int getNumPoints(){
        return getData().length;
    }
    public SortingAlgorithm getAlgorithm(){
        return algorithm;
    }
    public void setAlgorithm(T algorithm){
        this.algorithm = algorithm;
    }
    public void setData(double[] newData){
        this.data = newData;
    }
}
