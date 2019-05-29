package Application;

import SortPane.HorizontalSortPane.BarSortPane.BarSortPane;
import SortPane.SortPane;
import SortPane.SortPaneGrid.SortPaneGrid;
import SortingAlgorithms.*;
import SortingAlgorithms.HeapSort.MaxHeapSort;
import SortingAlgorithms.HeapSort.MinHeapSort;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import static SortingAlgorithms.SortingAlgorithm.createDataSet;

public class ApplicationPresets extends Application {
    public static Map<String, SortingAlgorithm> ALL_ALGORITHMS = Map.ofEntries(
            Map.entry(new QuickSort().getTitle(), new QuickSort()),
            Map.entry(new ParallelQuickSort().getTitle(), new ParallelQuickSort()),
            Map.entry(new MergeSort().getTitle(), new MergeSort()),
            Map.entry(new ParallelMergeSort().getTitle(), new ParallelMergeSort()),
            Map.entry(new SelectionSort().getTitle(), new SelectionSort()),
            Map.entry(new MaxHeapSort().getTitle(), new MaxHeapSort()),
            Map.entry(new MinHeapSort().getTitle(), new MinHeapSort()),
            Map.entry(new InsertionSort().getTitle(), new InsertionSort()),
            Map.entry(new CocktailShakerSort().getTitle(), new CocktailShakerSort()),
            Map.entry(new DoubleSelectionSort().getTitle(), new DoubleSelectionSort()),
            Map.entry(new ShellSort().getTitle(), new ShellSort()),
            Map.entry(new BubbleSort().getTitle(), new BubbleSort())
    );
    public final static int numOptions = 4; // Options appear in the order that follows
    public static Semaphore semaphore = new Semaphore(1);
    public static int numDataPoints = 25; // 200 is a good amount
    public static double paneHeight = 400;// Normal 300 // Extra fast 50
    public static double paneWidth = 300;// Normal 300
    public static long waitTime = 50; // 25 is usually ok
    public static ApplicationPresets app;
    public static SortPane[] panes;
    private Button sortButton = new Button("Sort");

    public static  SortingAlgorithm getSortingAlgorithm(String title){
        try {
            return ALL_ALGORITHMS.get(title).getClass().newInstance();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return ALL_ALGORITHMS.get(title);
        }
        }

    @Override
    public void start(Stage primaryStage){
/*
        // Options
        setUpOptions();
        String[] options = getOptions();
        numDataPoints = Integer.parseInt(options[0].substring(options[0].indexOf("=")+1).trim());
        paneHeight = Double.parseDouble(options[1].substring(options[1].indexOf("=")+1).trim());
        paneWidth = Double.parseDouble(options[2].substring(options[2].indexOf("=")+1).trim());
        waitTime = Long.parseLong(options[3].substring(options[3].indexOf("=")+1).trim());
        */
        double[] data = createDataSet(numDataPoints);
        SortPaneGrid grid = new SortPaneGrid(panes, 4);
        primaryStage.setScene(new Scene(grid));
        primaryStage.setTitle("Sorting Algorithms");
        primaryStage.show();
    }
    /** Check to see if options already exist. If not, default options will be created.*/
    /*private void setUpOptions(){
        try {
            File config = new File("Config");
            File options = new File("Config/Options.txt");
            if (!config.exists())
                config.mkdir();
            if(!options.exists()) {
                options.createNewFile();
                PrintWriter writer = new PrintWriter(options);
                writer.write(
                        "Num Points= 500\n".concat(
                        "Pane Height= 300\n").concat(
                        "Pane Width= 300\n".concat(
                        "Pause Length= 200\n")));
                writer.close();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
     /**Get options from options file
    private String[] getOptions(){
        String[] res = new String[numOptions];
        try{
            File options = new File("Config/Options.txt");
            if(!options.exists()) {
                options.createNewFile();
                setUpOptions();
            }
            Scanner scan = new Scanner(options);
            int i = 0;
            while(scan.hasNext())
                res[i++] = scan.nextLine();
            scan.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
            String[] newStrings = {"Num Points= 500\n","Pane Height= 300\n","Pane Width= 300\n","Pause Length= 200\n"};
            return newStrings;
        }
        finally {
            for(String str: res)// Take out everything but the values from the Strings
                str = str.substring(str.indexOf("=")+1).trim();
            }
            return res;
        }
    public static void main(String[]args){
        BarSortPane bsp = new BarSortPane(new BubbleSort(),createDataSet(numDataPoints));
        panes = new SortPane[2];
        panes[0] = bsp;
        panes[1] = bsp;
        launch(args);
    }
    */
}
