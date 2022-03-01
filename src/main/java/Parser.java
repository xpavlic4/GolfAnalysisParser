import analysis.CourseByRounds;
import analysis.FilesProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Parser {
    static Logger log = LoggerFactory.getLogger(Parser.class);
    public  String path;

    public Parser(String p) {
        path = p;
    }

    public static void main(String[] args) throws IOException {
    String path = null;
        try (InputStream input = Parser.class.getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            path = prop.getProperty("path");

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == path)
            System.exit(1);
        log.info("path: " + path);

        File[] values = new File(path).listFiles();

        Set<String> files = Stream.of(values)
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
        
        new Parser(path).parse(files);

    }

    public void parse(Set<String> files) throws IOException {
        new CourseByRounds(files, path).invoke();
    }

}
