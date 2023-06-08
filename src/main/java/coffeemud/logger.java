package coffeemud;

public class logger {
    public static void info(String message) {
        System.out.println(colours.magenta + "[INFO]\t" + message + colours.reset);
    }
    public static void debug(String message) {
        if(true /*implement options check*/) {
            info(colours.blue + message);
        }
    }
}
