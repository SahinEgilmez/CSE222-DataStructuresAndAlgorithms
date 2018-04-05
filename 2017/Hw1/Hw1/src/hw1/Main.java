package hw1;

/**
 * main method and class perform Library system
 *
 * @author segilmez
 */
public class Main {

    public static void main(String[] args) throws Throwable {
        LibrarySystem libSystem = new LibrarySystem();
        try {
            libSystem.system();
        } catch (Throwable e) {
            libSystem.finalize();
        }

    }
}
