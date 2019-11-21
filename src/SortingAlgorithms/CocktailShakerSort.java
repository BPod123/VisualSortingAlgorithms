package SortingAlgorithms;

import javafx.scene.paint.Color;

public class CocktailShakerSort extends SortingAlgorithm {
    public CocktailShakerSort(){
        super("Cocktail Shaker Sort");
    }

    @Override
    public void run(){
        int l = 0, r = getData().length - 1;
        while (l < r) {
            for (int i = l; i < r; i++) {
                int n = compare(i, i + 1);
                if (n > 0) {
                    swap(i, i + 1);
                }
            }
            selectPoint(r, Color.GREEN);
            r--;
            for (int i = r; i > l; i--) {
                int n = compare(i, i - 1);
                if (n < 0) {
                    swap(i, i - 1);
                }
            }
            selectPoint(l, Color.GREEN);
            l++;
        }
        updateAndColorAllIndecies(Color.GREEN, 0);
    }
}
