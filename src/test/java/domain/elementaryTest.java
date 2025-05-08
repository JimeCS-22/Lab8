package domain;

import org.junit.jupiter.api.Test;

import static domain.elementary.*;
import static org.junit.jupiter.api.Assertions.*;

class elementaryTest {

    @Test
    void elementarytest(){
        int[] a = util.Utility.getIntegerArray(10000);
        int[] b = util.Utility.copyArray(a); //no utilice Arrays.copyOf(a, a.length)
        System.out.println(elementarySorting("bubblesort", a, 50));
        System.out.println(elementarySorting("improvedBubbleSort", b, 100));
        System.out.println(elementarySorting("selectionSort", util.Utility.getIntegerArray(10000), 150));
        System.out.println(elementarySorting("countingSort", util.Utility.getIntegerArray(10000), 200));
    }

    public static String elementarySorting(String algorithm, int[] a, int n) {
        switch (algorithm.toLowerCase()) {
            case "bubblesort":
                bubbleSort(a);
                break;
            case "improvedBubbleSort":
                improvedBubbleSort(a);
                break;
            case "selectionSort":
                selectionSort(a);
                break;
            case "countingSort":
                countingSort(a);
                break;
            default:
                return "Algoritmo no reconocido. Use BubbleSort, ImprovedBubbleSort, SelectionSort o CountingSort.";
        }

        String result = "";
        return result;
    }

}