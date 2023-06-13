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
        update(mainMenu());
    }

    public static void update(HashMap<String, Callable<Void>> menu) throws IOException{
        TreeMap<String, Callable<Void>> sortedMenu = new TreeMap<>(menu);
        ui.stage.currentMessage = gameDungeon.currentRoom.description;
        (ui.currentStage = new ui.stage(new ui.status(), sortedMenu)).draw((short) ui.terminal.getTerminalSize().getRows(),(short) ui.terminal.getTerminalSize().getColumns());

    }
    public static void update(HashMap<String, Callable<Void>> menu, String message) throws IOException{
        TreeMap<String, Callable<Void>> sortedMenu = new TreeMap<>(menu);
        ui.stage.currentMessage = message;
        (ui.currentStage = new ui.stage(new ui.status(), sortedMenu)).draw((short) ui.terminal.getTerminalSize().getRows(),(short) ui.terminal.getTerminalSize().getColumns());

    }
    public static HashMap<String, Callable<Void>> mainMenu() {
        HashMap<String, Callable<Void>> menu = new HashMap<>();
        menu.put("Move", () -> {
            if (gameDungeon.currentRoom.complete || logger.sneaky) {
                update(moveMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            } else logger.error("Room not complete!");
            return null;
        });
        menu.put("Fight", () -> {
            logger.error("Nuh-uh-uh! It's not finished yet");
            return null;
        });
        menu.put("Solve", () -> {
            logger.error("Nuh-uh-uh! It's not finished yet");
            return null;
        });
        menu.put("Quit", () -> {
            main.titleScreen();
            ui.currentStage.draw((short) ui.terminal.getTerminalSize().getRows(), (short) ui.terminal.getTerminalSize().getColumns());
            return null;
        });
        return menu;
    }
    public static HashMap<String, Callable<Void>> moveMenu() {
        HashMap<String, Callable<Void>> dungeonMenu = new HashMap<>();
        dungeonMenu.put("North", () -> {
            gameDungeon.moveRooms(1);
            update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            Thread.sleep(2 * 1000);
            update(mainMenu());
            return null;
        });
        dungeonMenu.put("East", () -> {
            gameDungeon.moveRooms(2);
            update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            Thread.sleep(2 * 1000);
            update(mainMenu());
            return null;
        });
        dungeonMenu.put("West", () -> {
            gameDungeon.moveRooms(3);
            update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            Thread.sleep(2 * 1000);
            update(mainMenu());
            return null;
        });
        dungeonMenu.put("South", () -> {
            gameDungeon.moveRooms(4);
            update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            Thread.sleep(2 * 1000);
            update(mainMenu());
            return null;
        });
        dungeonMenu.put("Back", () -> {
            update(mainMenu());
            return null;
        });
        return dungeonMenu;
    }
    public static HashMap<String, Callable<Void>> blankMenu() {
        HashMap<String, Callable<Void>> dungeonMenu = new HashMap<>();
        dungeonMenu.put(" ", () -> {
            return null;
        });
        dungeonMenu.put(" ", () -> {

            return null;
        });
        return dungeonMenu;
    }
}
