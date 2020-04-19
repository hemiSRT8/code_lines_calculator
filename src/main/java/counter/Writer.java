package counter;

public class Writer {

    public void writePathMessage() {
        System.out.println("Please enter path to file for code lines counting:");
    }

    public void writeResult(String path, long lines) {
        System.out.println(String.format("%s : %s", path, lines));
    }

    public void writeNotReadablePathMessage() {
        System.out.println("Not readable path. Type 'quit' to end or provide new path:");
    }

    public void writeGoodByeMessage() {
        System.out.println("Have a nice day");
    }

    public void writeExceptionMessage(String message) {
        System.out.println(String.format("Unfortunately some internal error occurred (%s)", message));
    }
}
