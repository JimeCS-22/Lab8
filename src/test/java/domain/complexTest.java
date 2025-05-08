package domain;

import org.junit.jupiter.api.Test;

class complexTest {

    @Test
    void test() {

        int[] a = {45, 17, 23, 55, 7};
        System.out.println("The max number: " + util.Utility.maxArray(a));

    }

    @Test
    void complexTest() {

        int[] a = util.Utility.getIntegerArray(10000);
        int[] b = util.Utility.copyArray(a);  //no utilice Arrays.copyOf(a, a.length)
        System.out.println(elementarySorting("bubbleSort", a, 50));
        System.out.println(elementarySorting("improvedBubbleSort", b, 100));

        elementarySorting("countingSort", util.Utility.getIntegerArray(10000), 200));
        System.out.println(
                elementarySorting("selectionSort", util.Utility.getIntegerArray(10000), 150));
        System.out.println(
                util.Utility.getIntegerArray( int n);// retorna un nuevo arreglo de números enteros de
        // tamaño “n”, debidamente lleno, con valores entre 0 y 9999.
        util.Utility.copyArray( int[] a);// copia el arreglo en uno nuevo y lo retorna


    }

    private String elementarySorting(String algorithm, int[] array, int n) {

        switch (algorithm) {
            case "quickSort":

                complex.quickSort(array);
                break;

            case "radixSort":
                complex.radixSort(array, n);
                break;

            case "mergeSort":
                complex.mergeSort();
                break;

            case "shellSort":
                complex.shellSort();
                break;


        }
        return "";

    }

}