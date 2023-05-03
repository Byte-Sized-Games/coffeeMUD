package coffeemud;

public class ui {
    public static class stage {
        public void createMenu() {
            logger.debug("Creating menu");
        }
    }
    public stage createStage() {
        return new stage();
    }
}
