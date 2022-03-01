package analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseByRounds implements FilesProcessor {
    private Set<String> files;
    private String path;

    public CourseByRounds(Set<String> files, String path) {
        this.files = files;
        this.path = path;
    }

    public void invoke() throws IOException {
        HashMap<String, Integer> courseCount = new HashMap<>();
        for (String f :
                files) {
            Path path1 = Paths.get(path + "/" + f);
            List<String> lines = Files.readAllLines(path1);
            String course = lines.get(0);
            if (!course.startsWith("@")) {
                System.err.println(course + " " + path1);
            }
            String s = course.split(" ")[0].toLowerCase();
            if (!courseCount.containsKey(s)) {
                courseCount.put(s, 1);
            } else {
                int i = courseCount.get(s);
                courseCount.put(s, i + 1);
            }
        }
        courseCount.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
    }
}
