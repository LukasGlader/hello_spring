package bla.bla.hello;

import java.io.File;
import java.io.IOException;

public class ProcessExecutor {
    public Process execute(String jarName) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", jarName);
        pb.directory(new File("target/dependency"));
        File log = new File("log");
        pb.redirectErrorStream(true);
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log));
        Process p = pb.start();
        return p;
    }
}