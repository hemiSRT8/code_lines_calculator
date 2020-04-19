package counter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Application {

    private Writer writer;
    private Reader reader;
    private Parser parser;
    private Counter counter;

    public Application(Writer writer, Reader reader, Parser parser, Counter counter) {
        this.writer = writer;
        this.reader = reader;
        this.parser = parser;
        this.counter = counter;
    }

    public void start() {
        try {
            writer.writePathMessage();
            String path = reader.readLine();

            while (!isPathValid(path)) {
                writer.writeNotReadablePathMessage();
                path = reader.readLine();

                if ("quit".equalsIgnoreCase(path)) {
                    quit();
                }
            }

            Stream<String> lines = parser.parse(path);
            long result = counter.countCode(lines);
            writer.writeResult(path, result);
            writer.writeGoodByeMessage();
        } catch (Exception e) {
            writer.writeExceptionMessage(e.getMessage());
            quit();
        } finally {
            reader.close();
        }
    }

    private boolean isPathValid(String path) {
        return Files.isRegularFile(Paths.get(path));
    }

    private void quit() {
        writer.writeGoodByeMessage();
        System.exit(0);
    }
}
