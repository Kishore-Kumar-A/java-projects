public class AnalysisUtil {

    public static void benchmark(String taskName, Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        System.out.println(taskName + " took: " + (end - start) + " ns");
    }


    public static void test() {
        benchmark("Test Bubble Sort", () -> {
            int[] arr = {5,1,4,2};
            SortingUtil.bubbleSort(arr);
        });

        benchmark("Test Binary Search", () -> {
            int[] arr = {1,2,3,4,5};
            SearchUtil.binarySearch(arr, 4);
        });
    }
}
