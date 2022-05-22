import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MultiThreadingExample {
    public static void main(String[] args) {
        File path = new File("E:\\repos\\Java-Spring-2022\\MultyThreadingAnalysis\\directory");

        List<Integer> numThreadVariants = Arrays.asList(1, 2, 4, 8);
        HashMap<Integer, Float> results = new HashMap<>();

        int numTests = 5;
        for (Integer numThreadVariant : numThreadVariants) {
            Float totalTime = 0.0f;
            int currNumThreads = numThreadVariant;
            for (int j = 0; j < numTests; j++) {
                MultiThreadingExample threadingExample = new MultiThreadingExample(currNumThreads, path);
                totalTime += threadingExample.start();
            }
            Float average = totalTime / numTests;
            results.put(currNumThreads, average);
        }

        System.out.println(results);
    }

    private int numThreads;
    private File directory;
    List<File> filesInsideDirectory;

    public MultiThreadingExample(int numThreads, File directory) {
        this.numThreads = numThreads;
        this.directory = directory;
        filesInsideDirectory = listFilesForFolder(directory);
        this.numThreads = numThreads < filesInsideDirectory.size() ? numThreads : 1;


    }

    public List<File> listFilesForFolder(final File folder) {
        List<File> listOfFiles = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                listOfFiles.add(fileEntry);
            }
        }
        return listOfFiles;
    }

    public long start() {
        int n = filesInsideDirectory.size();
        int filesInThread = n / numThreads;
        int start, end;
        List<Thread> threads = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            start = i * filesInThread;
            end = (i < (numThreads - 1)) ? (i + 1) * filesInThread : n;

            int finalStart = start;
            int finalEnd = end;

            Thread newThread = new Thread( () -> processListOfFiles(filesInsideDirectory.subList(finalStart, finalEnd)));
            threads.add(newThread);
            newThread.start();

        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Throwable throwable) {
                System.out.println(throwable.toString());
            }
        }
        long estimatedTime = System.currentTimeMillis() - startTime;
        return estimatedTime;
    }

    private void processListOfFiles(List<File> files) {
        for (File file : files) {
            zipFile(file);
        }
    }

    private void zipFile(File textFile) {
        try {
            String name = textFile.getName();
            String filePath = textFile.toString();
            String zipPath = this.directory + "\\zipped\\" + name + ".zip";

            try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipPath))) {
                File fileToZip = new File(filePath);
                zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
                Files.copy(fileToZip.toPath(), zipOut);
            }
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }


    }
    private long calculateHashForFile(File textFile) {
        long cnt = 1;
        for (int i = 1; i < 23; i++) {
            cnt *= i;
        }
        System.out.println(cnt);
        return cnt;
    }
}
