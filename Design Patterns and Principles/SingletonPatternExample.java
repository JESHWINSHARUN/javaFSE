class Logger {

    // Eager initialization
    private static Logger instance = new Logger();

    // Private constructor
    private Logger() {
    }

    // Public method to access the single instance
    public static Logger getInstance() {
        return instance;
    }
}

public class SingletonPatternExample {

    public static void main(String[] args) {

        Logger l1 = Logger.getInstance();
        Logger l2 = Logger.getInstance();

        System.out.println(l1 == l2);
    }
}