package counter;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReaderTest {

    private Reader reader;

    @Before
    public void before() throws Exception {
        File file = Paths.get("src", "test", "resources", "Java.java").toFile();
        InputStream in = new FileInputStream(file);
        reader = new Reader(in);
    }

    @After
    public void after() {
        reader.close();
    }

    @Test
    public void givenInputStreamWhenReadLineThenFirstLineShouldBeReturned() {
        String result = reader.readLine();
        assertThat(result.trim(), is("import counter.*;\t\t\t\t\t\t\t\t\t\t\t\t\t\t//1"));
    }

    @Test(expected = NullPointerException.class)
    public void givenNullInputStreamThenReaderShouldRaiseNullPointerException() {
        new Reader(null);
    }
}
