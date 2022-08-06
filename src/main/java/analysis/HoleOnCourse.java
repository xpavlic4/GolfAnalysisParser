package analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class HoleOnCourse implements  FilesProcessor {
    private Set<String> files;
    private final String path;

    public HoleOnCourse(Set<String> files, String path) {
        this.files = files;
        this.path = path;
    }
    @Override
    public void invoke() throws IOException {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Enter a golf course: ");
        String str= sc.nextLine();

        System.out.print("Enter a hole number: ");
        int nbr = sc.nextInt();


        boolean coursePrinted;
        files = new TreeSet<>(files);
        for (String f :
                files) {
            coursePrinted = false;
            Path path1 = Paths.get(path + "/" + f);
            List<String> lines = Files.readAllLines(path1);
            String course = lines.get(0);
            if (!course.startsWith("@")) {
                System.err.println(course + " " + path1);
            }
            if (!course.contains(str)) {
                continue;
            }
            Iterator<String> iterator = lines.iterator();
            boolean hole = false;
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (next.startsWith("#")) {
                    hole = false;
                }
                if (next.startsWith("#"+ nbr)) {
                    hole = true;
                    continue;
                }
                if (hole) {
                    if (!next.trim().isBlank()) {
                        if (!coursePrinted) {
                            System.out.println(f);
                            coursePrinted = true;
                        }

                        System.out.println(next);
                    }
                }
            }
        }
    }
}
