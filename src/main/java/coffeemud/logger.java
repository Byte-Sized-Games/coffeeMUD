package coffeemud;

public class logger {
    public static boolean debuggable = true;
    public static boolean sneaky = false;
    public static void info(String message) {
        System.out.println(colours.magenta + "[INFO]\t" + message + colours.reset);
    }
    public static void debug(String message) {
        if(debuggable /*implement options check*/) {
            info(colours.blue + message);
        }
    }
    public static void error(String message) {
        System.out.println(colours.red + "[ERROR]\t" + message + colours.reset);
    }
}
