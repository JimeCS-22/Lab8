package domain;

public class elementary {
    private static int totalIteractions;
    private static int totalChanges;

    public static int getTotalIteractions() {
        return totalIteractions;
    }

    public static void setTotalIteractions(int totalIteractions) {
        elementary.totalIteractions = totalIteractions;
    }

    public static int getTotalChanges() {
        return totalChanges;
    }

    public static void setTotalChanges(int totalChanges) {
        elementary.totalChanges = totalChanges;
    }

    public static void bubbleSort(int a[]){
        totalIteractions = 0;
        totalChanges = 0;
        for(int i=1;i<a.length;i++) {
            totalIteractions++;
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    int aux = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = aux;
                    totalChanges++;
                }//if
            }//for j
        }
    }

    public static void improvedBubbleSort(int a[]){
        totalIteractions = 0;
        totalChanges = 0;
        boolean swapped = true; //intercambiado
        for(int i=1;swapped;i++){
            swapped = false;
            totalIteractions++;
            for(int j=0;j<a.length-i;j++){
                if(a[j]>a[j+1]){
                    int aux=a[j];
                    a[j]=a[j+1];
                    a[j+1]=aux;
                    swapped = true;
                    totalChanges++;
                }//if
            }//for j
        }//for i
    }

    public static void selectionSort(int a[]){
        totalIteractions = 0;
        totalChanges = 0;
        for(int i=0;i<a.length-1;i++){
            int min=a[i];
            int minIndex=i;
            totalIteractions++;
            for(int j=i+1;j<a.length;j++){
                if(a[j]<min){
                    min=a[j];
                    minIndex=j;
                    totalChanges++;
                }//if
            }//for j
            a[minIndex]=a[i];
            a[i]=min;
        }//for i
    }

    public static void countingSort(int a[]) {
        int max = util.Utility.maxArray(a); //va de 0 hasta el elemento maximo

        // create buckets
        int[] counter = new int[max + 1];
        // fill buckets
        for (int element : a) {
            counter[element]++; //incrementa el num de ocurrencias del elemento
        }
        int[] counterCopy = counter.clone();

        // sort array
        int index = 0;
        for (int i = 0; i < counter.length; i++) {
            while (counter[i]>0) { //significa q al menos hay un elemento (q existe)
                a[index++] = i;
                counter[i]--;
            }
        }//for i
    }
}
