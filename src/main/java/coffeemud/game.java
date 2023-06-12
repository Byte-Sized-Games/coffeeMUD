package coffeemud;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class game {
    static Callable<Void> filler = () -> {
        return null;
    };
    static String name;
    static player protag;
    static dungeons gameDungeon;
    public static void start() {
        logger.info("Starting game");
        gameDungeon = new dungeons();
        logger.info("Generating dungeons");
        for (int i = 0; i < gameDungeon.dungeonRooms.length - 1; i++) {
            for (int x = 0; x < gameDungeon.dungeonRooms[i].length - 1; x++) {
                logger.info(gameDungeon.dungeonRooms[i][x].description);
            }
        }
        logger.info("Dungeons generated");
        logger.info("Loading Player");
        protag = new player('f');
        logger.info("Player loaded");
        
        

    }
}
