package SortingAlgorithms;

import javafx.scene.paint.Color;

public class CocktailShakerSort extends SortingAlgorithm {
    public CocktailShakerSort(){
        super("Cocktail Shaker Sort");
    }

    @Override
    public void run(){
        for(int l = 0, b = getData().length-1, min, max, i; l<b;){
            for(i = l, max = l; i<=b; i++){
                if(compare(i,max)>0)
                    max = i;
            }
            swap(max, b);
            selectPoint(b--, Color.GREEN);
            for(i = b, min = b; i>=l; i--){
                if(compare(i, min)<0)
                    min = i;
            }
            swap(min, l);
            selectPoint(l++, Color.GREEN);
        }
        updateAndColorAllIndecies(Color.GREEN, 0);
    }
}
