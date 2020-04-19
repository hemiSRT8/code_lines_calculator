package counter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WriterTest {

    private final PrintStream outBackup = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void before() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void after() {
        System.setOut(outBackup);
    }

    @Test
    public void givenWriterWhenWritePathMessageIsCalledThenOutStreamShouldOutputExpectedString() {
        new Writer().writePathMessage();
        String result = out.toString();

        assertThat(result.trim(), is("Please enter path to file for code lines counting:"));
    }

    @Test
    public void givenWriterWhenWriteResultIsCalledThenOutStreamShouldOutputExpectedString() {
        new Writer().writeResult("test", 1);
        String result = out.toString();

        assertThat(result.trim(), is("test : 1"));
    }

    @Test
    public void givenWriterWhenWriteNotReadablePathMessageIsCalledThenOutStreamShouldOutputExpectedString() {
        new Writer().writeNotReadablePathMessage();
        String result = out.toString();

        assertThat(result.trim(), is("Not readable path. Type 'quit' to end or provide new path:"));
    }

    @Test
    public void givenWriterWhenWriteGoodbyeMessageIsCalledThenOutStreamShouldOutputExpectedString() {
        new Writer().writeGoodByeMessage();
        String result = out.toString();

        assertThat(result.trim(), is("Have a nice day"));
    }

    @Test
    public void givenWriterWhenWriteExceptionMessageIsCalledThenOutStreamShouldOutputExpectedString() {
        String exceptionReason = "internal";
        new Writer().writeExceptionMessage(exceptionReason);
        String result = out.toString();

        assertThat(result.trim(), is("Unfortunately some internal error occurred (internal)"));
    }
}
