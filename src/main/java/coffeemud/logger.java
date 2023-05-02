package coffeemud;

public class logger {
    public static void info(String message) {
        System.out.println("INFO\t" + message);
    }
    public static void debug(String message) {
        if(true /*implement options check*/) {
            System.out.println("DEBUG\t" + message);
        }
    }
}
