package coffeemud;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;

public class game {
    static Callable<Void> filler = () -> {
        return null;
    };
    static String name;
    static player protag;
    static dungeons gameDungeon;
    public static int pX = 9;
    public static int pY = 9;

    public static void start() throws IOException{
        // Load dungeon & player
        logger.debug("Starting game");
        gameDungeon = new dungeons(pX, pY);
        logger.debug("Generating dungeons");
        for (int i = 0; i < gameDungeon.dungeonRooms.length ; i++) {
            for (int x = 0; x < gameDungeon.dungeonRooms[i].length; x++) {
                logger.debug("x:"+ i + ", y:" + x + " | " + gameDungeon.dungeonRooms[i][x].description);
            }
        }
        logger.debug("Dungeons generated");
        logger.debug("Loading Player");
        protag = new player('f');
        logger.debug("Player loaded");
        // Init graphics
        update(initMenu());
    }

    public static void update(HashMap<String, Callable<Void>> menu) throws IOException{
        TreeMap<String, Callable<Void>> sortedMenu = new TreeMap<>(initMenu());
        ui.stage.currentMessage = gameDungeon.currentRoom.description;
        (ui.currentStage = new ui.stage(new ui.status(), sortedMenu)).draw((short) ui.terminal.getTerminalSize().getRows(),(short) ui.terminal.getTerminalSize().getColumns());

    }
    public static HashMap<String, Callable<Void>> initMenu() {
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
        dungeonMenu.put("Quit", () -> {
            main.titleScreen();
            ui.currentStage.draw((short) ui.terminal.getTerminalSize().getRows(), (short) ui.terminal.getTerminalSize().getColumns());
            return null;
        });
        return dungeonMenu;
    }
}
