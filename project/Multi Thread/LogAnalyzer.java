import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class LogAnalyzer {

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: java UnifiedTimeOnlyAnalyzer <folderPath> <poolSize>");
            System.out.println("Example: java UnifiedTimeOnlyAnalyzer C:\\logs 8");
            return;
        }

        String folderPath = args[0];
        int poolSize = Integer.parseInt(args[1]);

        Path folder = Paths.get(folderPath);

        // -------------------------------
        // SEQUENTIAL
        // -------------------------------
        long startSeq = System.currentTimeMillis();
        runSequential(folder);
        long seqTime = System.currentTimeMillis() - startSeq;

        // -------------------------------
        // CONCURRENT
        // -------------------------------
        long startConc = System.currentTimeMillis();
        runConcurrent(folder, poolSize);
        long concTime = System.currentTimeMillis() - startConc;

        // -------------------------------
        // OUTPUT ONLY TIME COMPARISON
        // -------------------------------
        System.out.println("=====================================");
        System.out.println("       LOG ANALYSIS TIME REPORT       ");
        System.out.println("=====================================");
        System.out.println("Sequential Processing : " + seqTime + " ms");
        System.out.println("Concurrent Processing : " + concTime + " ms");
        System.out.println("-------------------------------------");

        double speedup = (double) seqTime / concTime;
        System.out.println("Speedup Factor       : " + speedup);
        System.out.println("=====================================");
    }

    // --- Sequential File Traverse ---
    private static void runSequential(Path folder) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.log")) {
            for (Path file : stream) {
                processFile(file); // no counting
            }
        }
    }

    // --- Concurrent File Traverse ---
    private static void runConcurrent(Path folder, int poolSize) throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<Future<Void>> futures = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.log")) {
            for (Path file : stream) {
                futures.add(executor.submit(() -> {
                    processFile(file); // no counting
                    return null;
                }));
            }
        }

        for (Future<Void> f : futures) f.get();
        executor.shutdown();
    }

    // --- File Reader Only (no keyword search) ---
    private static void processFile(Path file) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(file)) {
            while (br.readLine() != null) {
                // just read to simulate workload
            }
        }
    }
}
