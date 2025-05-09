package domain;

import java.util.Arrays;

public class complex {
    static int quickSortRecursiveCalls = 0;
    static int mergeSortRecursiveCalls = 0;
    private static int[] counterRadix;

    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            quickSortRecursiveCalls++;
            int i = low;
            int j = high;
            int pivot = arr[(low + high) / 2];
            while (i <= j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;
                if (i <= j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }
            quickSort(arr, low, j);
            quickSort(arr, i, high);
        }
    }

    public static Object[] radixSort(int a[], int n) {
        int m = maxArray(a);
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(a, n, exp);
        return new Object[]{counterRadix};
    }

    private static void countSort(int a[], int n, int exp) {
        int output[] = new int[n];
        int count[] = new int[10];
        for (int i = 0; i < n; i++)
            count[(a[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            output[count[(a[i] / exp) % 10] - 1] = a[i];
            count[(a[i] / exp) % 10]--;
        }
        for (int i = 0; i < n; i++)
            a[i] = output[i];
        counterRadix = count;
    }

    public static void mergeSort(int a[], int tmp[], int low, int high) {
        if (low < high) {
            mergeSortRecursiveCalls++;
            int center = (low + high) / 2;
            mergeSort(a, tmp, low, center);
            mergeSort(a, tmp, center + 1, high);
            merge(a, tmp, low, center + 1, high);
        }//if
    }

    private static void merge(int a[], int tmp[], int lowIndex, int highIndex, int endIndex) {
        int leftEnd = highIndex - 1;
        int tmpPos = lowIndex;
        int numElements = endIndex - lowIndex + 1;
        while (lowIndex <= leftEnd && highIndex <= endIndex)
            if (a[lowIndex] <= a[highIndex]) {
                tmp[tmpPos++] = a[lowIndex++];
            } else {
                tmp[tmpPos++] = a[highIndex++];
            }
        while (lowIndex <= leftEnd) tmp[tmpPos++] = a[lowIndex++];
        while (highIndex <= endIndex) tmp[tmpPos++] = a[highIndex++];
        for (int i = 0; i < numElements; i++, endIndex--) a[endIndex] = tmp[endIndex];
    }

    public static void shellSort(int a[]) {
        int n = a.length;
        int gapCounter = 1;
        for (int gap = n / 2; gap > 0; gap /= 2) {
//
            for (int i = gap; i < n; i += 1) {
                int temp = a[i];
                int j;
                for (j = i; j >= gap && a[j - gap] > temp; j -= gap)
                    a[j] = a[j - gap];
                a[j] = temp;
            }
        }
    }

    private static int maxArray(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) if (arr[i] > max) max = arr[i];
        return max;
    }

    public static void resetQuickSortCounters() {
        quickSortRecursiveCalls = 0;
    }

    public static void resetMergeSortCounters() {
        mergeSortRecursiveCalls = 0;
    }
}