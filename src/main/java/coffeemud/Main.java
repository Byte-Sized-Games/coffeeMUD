package coffeemud;

public class Main {
    public static void main(String[] args) {
        for(String arg : args) {
            switch(arg) {
                case "--debug":
                    logger.debug("Debug mode enabled");
                    break;
                default:
                    logger.info("Unknown argument: " + arg);
                    break;
            }
        }
        System.out.println("Hello, World!");
    }
}