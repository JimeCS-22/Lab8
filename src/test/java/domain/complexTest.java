package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

import java.util.Arrays;

class complexTest {

    @Test
    void complexSortingTest() {
        System.out.println(complexSorting("quickSort",
                util.Utility.getIntegerArray(80000), 30));
        System.out.println(complexSorting("radixSort",
                util.Utility.getIntegerArray(40000), 50));
        System.out.println(complexSorting("mergeSort",
                util.Utility.getIntegerArray(15000), 100));
        System.out.println(complexSorting("shellSort",
                util.Utility.getIntegerArray(5000), 150));
    }

    private String complexSorting(String algorithm, int[] array, int n) {
        int[] originalArray = Arrays.copyOf(array, array.length);
        int[] array2 = new int[array.length];
        String result = "";
        int limitLowHigh = 30;

        switch (algorithm) {
            case "quickSort":
                complex.resetQuickSortCounters();
                int[] quickSortArray = Arrays.copyOf(array, array.length);
                complex.quickSort(quickSortArray, 0, quickSortArray.length - 1);
                result = String.format("%s - Test\nAlgorithm: %s\nOriginal Array: %s\nSorted Array: %s\nLow: %s\nHigh: %s\nRecursive calls: %d\n___________________________________________________\n",
                        algorithm, algorithm, Utility.show(originalArray, n), Utility.show(quickSortArray, n),
                        Utility.show(Arrays.copyOfRange(quickSortArray, 0, Math.min(quickSortArray.length, limitLowHigh)), limitLowHigh),
                        Utility.show(Arrays.copyOfRange(quickSortArray, Math.max(0, quickSortArray.length - limitLowHigh), quickSortArray.length), limitLowHigh),
                        complex.quickSortRecursiveCalls);
                break;

            case "radixSort":
                int[] radixSortArray = Arrays.copyOf(array, array.length);
                Object[] radixSortResult = complex.radixSort(radixSortArray, radixSortArray.length);
                int[] counterRadixResult = (int[]) radixSortResult[0];
                result = String.format("%s - Test\nAlgorithm: %s\nOriginal Array: %s\nSorted Array: %s\nCounter Array: %s\n___________________________________________________\n",
                        algorithm, algorithm, Utility.show(originalArray, n), Utility.show(radixSortArray, n), Arrays.toString(counterRadixResult));
                break;

            case "mergeSort":
                complex.resetMergeSortCounters();
                int[] mergeSortArray = Arrays.copyOf(array, array.length);
                complex.mergeSort(mergeSortArray, array2, 0, mergeSortArray.length - 1);
                result = String.format("%s - Test\nAlgorithm: %s\nOriginal Array: %s\nSorted Array: %s\nLow: %s\nHigh: %s\nRecursive calls: %d\n___________________________________________________\n",
                        algorithm, algorithm, Utility.show(originalArray, n), Utility.show(mergeSortArray, n),
                        Utility.show(Arrays.copyOfRange(mergeSortArray, 0, Math.min(mergeSortArray.length, limitLowHigh)), limitLowHigh),
                        Utility.show(Arrays.copyOfRange(mergeSortArray, Math.max(0, mergeSortArray.length - limitLowHigh), mergeSortArray.length), limitLowHigh),
                        complex.mergeSortRecursiveCalls);
                break;

            case "shellSort":
                int[] shellSortArray = Arrays.copyOf(array, array.length);
                System.out.println(algorithm + " - Test");
                System.out.println("Algorithm: " + algorithm);
                System.out.println("Original Array: " + Utility.show(originalArray, n));
                complex.shellSort(shellSortArray); // Ensure this line is called to perform the sort
                System.out.println("Sorted Array: " + Utility.show(shellSortArray, Math.min(shellSortArray.length, n)));


                int nShell = shellSortArray.length;
                int gap1 = nShell / 12;
                int gap2 = gap1 / 12;
                int gap3 = gap2 / 12;

                System.out.print("Gap (n/2) subArray1: ");
                for (int i = gap1, count = 0; i < nShell && count < 12; i += gap1, count++) {
                    System.out.print(shellSortArray[i] + " ");
                }
                System.out.println();

                System.out.print("Gap (n/2) subArray2: ");
                for (int i = gap2, count = 0; i < nShell && count < 12; i += gap2, count++) {
                    System.out.print(shellSortArray[i] + " ");
                }
                System.out.println();

                System.out.print("Gap (n/2) subArray3: ");
                for (int i = gap3, count = 0; i < nShell && count < 12; i += gap3, count++) {
                    System.out.print(shellSortArray[i] + " ");
                }

                System.out.println();

                System.out.println("___________________________________________________");
                return "";
        }
        return result;
    }
}