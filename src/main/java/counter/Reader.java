package counter;

import com.google.common.base.Preconditions;

import java.io.InputStream;
import java.util.Scanner;

public class Reader {

    private Scanner scanner;

    public Reader(InputStream source) {
        Preconditions.checkNotNull(source);
        this.scanner = new Scanner(source);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
