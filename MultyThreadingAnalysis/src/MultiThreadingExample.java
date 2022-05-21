import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MultiThreadingExample {
    public static void main(String[] args) {
        File path = new File("E:\\repos\\Java-Spring-2022\\MultyThreadingAnalysis\\directory");
        MultiThreadingExample test = new MultiThreadingExample(6, path);
        test.start();
    }

    private int numThreads;
    private File directory;
    List<File> filesInsideDirectory;

    public MultiThreadingExample(int numThreads, File directory) {
        this.numThreads = numThreads;
        this.directory = directory;
        filesInsideDirectory = listFilesForFolder(directory);
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

    public void start() {
        int n = filesInsideDirectory.size();
        System.out.println(n + " files inside the directory");
        int filesInThread = n / numThreads;
        int start, end;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            start = i * filesInThread;
            // TODO: fix a bug
            end = Math.min((i + 1) * filesInThread, n);

            int finalStart = start;
            int finalEnd = end;
            System.out.println("Start processing files from " + start + " to " + end);
            //Thread newThread = new Thread( () -> processListOfFiles(filesInsideDirectory.subList(finalStart, finalEnd)));
            
            newThread.start();

        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println(estimatedTime);
    }

    private void processListOfFiles(List<File> files) {
        long res = 0;
        for (File file : files) {
            System.out.println(file);
            res += calculateHashForFile(file);
        }
    }

    private void zipFile(File textFile) {
        try {
            String name = textFile.getName(); // get it
            StringBuilder sb = new StringBuilder();
            sb.append("Test String");

            File f = new File(this.directory + "\\" + name + ".zip");
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry(textFile.toString());
            out.putNextEntry(e);

            byte[] data = sb.toString().getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();
        } catch (Throwable thr) {
            System.out.println(thr.toString());
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
