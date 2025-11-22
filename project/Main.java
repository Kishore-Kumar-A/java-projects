public class Main {
    public static void main(String[] args) {
        System.out.println("--- SORTING TESTS ---");
        SortingUtil.test();

        System.out.println("\n--- SEARCH TESTS ---");
        SearchUtil.test();

        System.out.println("\n--- COLLECTION TESTS ---");
        CollectionUtil.test();

        System.out.println("\n--- BENCHMARK TESTS ---");
        AnalysisUtil.test();
    }
}
