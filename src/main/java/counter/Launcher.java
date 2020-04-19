package counter;

public class Launcher {

    public static void main(String[] args) {
        new Application(new Writer(), new Reader(System.in), new Parser(), new Counter()).start();
    }
}
