package SortingAlgorithms;

import javafx.scene.paint.Color;

public class BubbleSort extends SortingAlgorithm {
    public BubbleSort() {
        super("Bubble Sort");
    }

    @Override
    public String getTitle() {
        return "Bubble Sort";
    }

    @Override
    public void run() {
        for (int k = getData().length-1; k > 0; k--) {
            for (int i = 0; i < k; i++) {
                if (compare(i, i + 1) > 0)
                    swap(i, i + 1);
            }
            selectPoint(k, Color.GREEN);
        }
        selectPoint(0, Color.GREEN);
    }
}