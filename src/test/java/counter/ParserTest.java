package counter;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParserTest {

    @Test
    public void givenValidPathThenParserShouldReturnStringStreamRespectivelyToContent() {
        Stream<String> stream = new Parser().parse("src/test/resources/Java.java");
        assertThat(stream.count(), is(41L));
    }

    @Test(expected = RuntimeException.class)
    public void givenNullPathThenParserShouldRaiseRuntimeException() {
        new Parser().parse(null);
    }

    @Test(expected = RuntimeException.class)
    public void givenNotExistingPathThenParserShouldRaiseRuntimeException() {
        new Parser().parse("not existing path");
    }
}
