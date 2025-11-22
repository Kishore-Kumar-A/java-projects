import java.util.Arrays;

public class SortingUtil {

    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }


    public static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void mergeSort(int[] arr) {
        mergeSortRec(arr, 0, arr.length - 1);
    }

    private static void mergeSortRec(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSortRec(arr, l, mid);
            mergeSortRec(arr, mid+1, r);
            merge(arr, l, mid, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for(int i = 0; i < n1; i++) L[i] = arr[l + i];
        for(int i = 0; i < n2; i++) R[i] = arr[m + 1 + i];

        int i = 0, j = 0, k = l;
        while(i < n1 && j < n2)
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];

        while(i < n1) arr[k++] = L[i++];
        while(j < n2) arr[k++] = R[j++];
    }

    /**
     * Quick Sort
     * Time Complexity: O(n log n), worst O(n²)
     */
    public static void quickSort(int[] arr) {
        quickSortRec(arr, 0, arr.length - 1);
    }

    private static void quickSortRec(int[] arr, int low, int high) {
        if(low < high) {
            int pi = partition(arr, low, high);
            quickSortRec(arr, low, pi - 1);
            quickSortRec(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for(int j = low; j < high; j++) {
            if(arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }

        int temp = arr[i+1]; arr[i+1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    // Test cases
    public static void test() {
        int[] a = {5,2,9,1};
        bubbleSort(a);
        System.out.println("Bubble: " + Arrays.toString(a));

        int[] b = {8,3,7,4};
        insertionSort(b);
        System.out.println("Insertion: " + Arrays.toString(b));

        int[] c = {9,5,1,7,3};
        mergeSort(c);
        System.out.println("Merge: " + Arrays.toString(c));

        int[] d = {10,2,8,6,4};
        quickSort(d);
        System.out.println("Quick: " + Arrays.toString(d));
    }
}
