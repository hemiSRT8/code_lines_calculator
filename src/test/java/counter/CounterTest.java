package counter;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CounterTest {

    @Test
    public void givenEmptyStreamThenCounterShouldReturnZero() {
        long result = new Counter().countCode(Stream.empty());
        assertThat(result, is(0L));
    }

    @Test
    public void givenNotEmptyStreamThenCounterShouldReturnResult() throws Exception {
        Stream<String> stream = Files.lines(Paths.get("src", "test", "resources", "Java.java"));
        long result = new Counter().countCode(stream);
        assertThat(result, is(19L));
    }

    @Test(expected = NullPointerException.class)
    public void givenNullStreamThenCounterShouldRaiseNullPointerException() {
        new Counter().countCode(null);
    }
}
