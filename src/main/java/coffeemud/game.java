package coffeemud;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class game {
    static Callable<Void> filler = () -> {
        return null;
    };
    static String name;
    static player protag;
    static dungeons gameDungeon;

    public static void start() throws IOException{
        // Load dungeon & player
        logger.info("Starting game");
        gameDungeon = new dungeons();
        logger.info("Generating dungeons");
        for (int i = 0; i < gameDungeon.dungeonRooms.length ; i++) {
            for (int x = 0; x < gameDungeon.dungeonRooms[i].length; x++) {
                logger.info("x:"+ i + ", y:" + x + " | " + gameDungeon.dungeonRooms[i][x].description);
            }
        }
        logger.info("Dungeons generated");
        logger.info("Loading Player");
        protag = new player('f');
        logger.info("Player loaded");
        // Init graphics
        HashMap<String, Callable<Void>> dungeonMenu = new HashMap<>();
        dungeonMenu.put("North", () -> {
            gameDungeon.moveRooms(1);
            update(dungeonMenu);
            return null;
        });
        dungeonMenu.put("East", () -> {
            gameDungeon.moveRooms(2);
            update(dungeonMenu);
            return null;
        });
        dungeonMenu.put("West", () -> {
            gameDungeon.moveRooms(3);
            update(dungeonMenu);
            return null;
        });
        dungeonMenu.put("South", () -> {
            gameDungeon.moveRooms(4);
            update(dungeonMenu);
            return null;
        });
        update(dungeonMenu);
    }

    public static void update(HashMap<String, Callable<Void>> menu) throws IOException{
        ui.stage.currentMessage = gameDungeon.currentRoom.description;
        (ui.currentStage = new ui.stage(new ui.status(), menu)).draw((short) ui.terminal.getTerminalSize().getRows(),(short) ui.terminal.getTerminalSize().getColumns());

    }
}
