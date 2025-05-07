package domain;

public class complex {

    public void quickSort(int [] arr , int low , int high){

        int i = low ;
        int j = high ;
        int pivot = arr[(low + high) / 2];

        do {

            while (arr[i]<pivot) i++;
            while (arr[j]>pivot)j--;

            if (i<=j) {

                int aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux;

                i++;
                j++;
            }//if

        }while (i<=j);//do

        if(low<j) quickSort(arr,low,j);
        if(i<high) quickSort(arr,i,high);
    }

    //Algoritmo radix
    public void radixSort(int[] a, int n){

        int m = util.Utility.maxArray(a);

        for (int exp = 1; m/exp > 0; exp *= 10){

            countSort(a, n, exp);

        }//for

    }

    private void countSort(int a[], int n, int exp){

    }

    //Algoritmo mergeSort
    public void mergeSort(int a[], int tmp[], int low, int high){

        if(low<high){

            int center = (low+high)/2;
            mergeSort(a,tmp,low,center );
            mergeSort(a,tmp,center+1,high);
            merge(a,tmp,low,center+1,high);

        }//if

    }
    private void merge(int a[], int tmp[], int lowIndex, int highIndex, int
            endIndex) {

        int leftEnd = highIndex - 1;
        int tmpPos = lowIndex;
        int numElements = endIndex - lowIndex + 1;

        while (lowIndex <= leftEnd && highIndex <= endIndex){

            if (a[lowIndex] <= a[highIndex]) {

                tmp[tmpPos++] = a[lowIndex++];

            }else tmp[tmpPos++] = a[highIndex++];

            while (lowIndex <= leftEnd) {

            tmp[tmpPos++] = a[lowIndex++];

            }
           while (highIndex <= endIndex) {

            tmp[tmpPos++] = a[highIndex++];

           }
           for (int i = 0; i < numElements; i++, endIndex--){

            a[endIndex] = tmp[endIndex];
           }

        }

    }

    //Algoritmo shell
    public int shellSort(int [] a ){

        int n = a.length;

        for (int gap = n/2; gap>0; gap/=2){

            for (int i = gap; i < n ; i++) {

                int temp = a[i];

                int j;

                for (j = i; j>= gap && a[j-gap]> temp ; j--){

                    a[j]= a[j-gap];
                }

                a[j]=temp;

            }

        }

        return 0;
    }


    }
