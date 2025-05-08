package domain;

import org.junit.jupiter.api.Test;

import static domain.elementary.*;
import static org.junit.jupiter.api.Assertions.*;

class elementaryTest {

    @Test
    void elementaryTest(){
    }

    public String elementarySorting(String algorithm, int[] a, int n) {
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