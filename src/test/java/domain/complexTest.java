package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

class complexTest {

    @Test
    void test() {

        int[] a = {45, 17, 23, 55, 7};
        System.out.println("The max number: " + Utility.maxArray(a));

    }

    @Test
    void complexTest() {

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

        int low = 0;//Poner el valor correcto
        int high = array.length - 1; //Poner el valor correcto
        int[] array2 = new int[array.length];
        int[] originalArray = util.Utility.copyArray(array);

        switch (algorithm) {

            case "quickSort":

                complex.quickSort(array, low, high); //falta low y high
                break;

            case "radixSort":
                complex.radixSort(array, n);
                break;

            case "mergeSort":
                complex.mergeSort(array, array2, low, high);
                break;

            case "shellSort":
                complex.shellSort(array);
                break;


        }


        String result = "";


        result += "\n" + algorithm + "-Test"
                + "\n Algorithm: " + algorithm
                + "\n Original array " + util.Utility.show(originalArray, n)
                + "\n Sorted array " + util.Utility.show(array, n)
                + "\n Low " + util.Utility.show(array, n)
                + "\n High " + util.Utility.show(array, n)
                + "\n Pivot " + util.Utility.show(array, n)

        ;

        return result;

    }

}