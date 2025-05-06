package Elementary;

public class elementary {

    //Algoritmo de burbuja
    public void BubleSort(int [] a){

        for (int i = 1; i < a.length-i; i++){

            for (int j = 0; j<a.length-i; j++){

                if (a[j] > a [j+1]){

                    int aux = a[j];
                    a[j]= a[j+1];
                    a[j+1]= aux ;

                }//if
            }//for
        }//for

    }

    //Algoritmo burbuja mejorada
    public void improvebubleSort(int[] a){

        boolean swapped = true; //Intercambios

        for(int i = 1 ; swapped; i++){

            swapped = false ;

            for(int j = 0; j<a.length-i ; j++){

                if (a[j] > a[j+1]){

                    int aux = a[j];

                    a[j] = a[j+1];

                    a[j+1] = aux ;

                    swapped = true ;
                }//if

            }//for

        }//for

    }

    //Algoritmo de seleccion
    public void selectionSor(int[] a){

        for (int i = 0; i<a.length-1; i++){

            int min = a[i];
            int minIndex = i ;

            for (int j = i+1; j<a.length; j++){

                if (a[j]<min){

                    min = a[j];
                    minIndex = j ;

                }//if
            }//for j

            a[minIndex] = a[i];
            a[i] = min ;
        }//For

    }

    //Algoritmo de seleccion con contador
    public void countingSort(int [] a){

        int max = maxArray(a);
        int[] counter = new int [max + 1];

        for (int element : a ) {

            counter[element]++;

        }//for

        int index = 0;

        for (int i = 0; i<counter.length; i++){

            while(counter[i]>0){

                a[index ++] = i ;
                counter[i] --;

            }//while

        }//for

    }

    //Algoritmo para encontrar el maximo
    private int maxArray(int[] a) {
        return 0;
    }


}
