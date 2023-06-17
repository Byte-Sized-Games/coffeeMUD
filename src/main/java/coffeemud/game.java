package coffeemud;

import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.Arrays;

public class game {
    static Callable<Void> filler = () -> {
        return null;
    };
    static String name;
    static player protag;
    static dungeons gameDungeon;
    public static int pX = 9;
    public static int pY = 9;

    public static void start() throws IOException {
        // Load dungeon & player
        logger.debug("Starting game");
        gameDungeon = new dungeons(pX, pY);
        logger.debug("Generating dungeons");
        for (int i = 0; i < gameDungeon.dungeonRooms.length; i++) {
            for (int x = 0; x < gameDungeon.dungeonRooms[i].length; x++) {
                logger.debug("x:" + i + ", y:" + x + " | " + gameDungeon.dungeonRooms[i][x].description);
            }
        }
        logger.debug("Dungeons generated");
        logger.debug("Loading Player");
        protag = new player('f');
        logger.debug("Player loaded");
        // Init graphics
        update(mainMenu());
    }

    public static void update(TreeMap<String, Callable<Void>> menu) throws IOException {

        ui.stage.currentMessage = gameDungeon.currentRoom.description;
        (ui.currentStage = new ui.stage(new ui.status(), menu)).draw((short) ui.terminal.getTerminalSize().getRows(),
                (short) ui.terminal.getTerminalSize().getColumns());

    }

    public static void update(TreeMap<String, Callable<Void>> menu, String message) throws IOException {
        ui.stage.currentMessage = message;
        (ui.currentStage = new ui.stage(new ui.status(), menu)).draw((short) ui.terminal.getTerminalSize().getRows(),
                (short) ui.terminal.getTerminalSize().getColumns());

    }

    public static TreeMap<String, Callable<Void>> mainMenu() {
        TreeMap<String, Callable<Void>> menu;
        String[] commands = { "Move", "Fight", "Solve", "Quit" };
        Callable[] callables = { () -> {
            if (gameDungeon.currentRoom.complete || logger.sneaky) {
                update(moveMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            } else {
                logger.error("Room not complete!");
            }
            return null;
        },
                () -> {
                    logger.error("In progress. Expect Bugs");
                    battle roomBattle = new battle(
                            new ArrayList<entities>(Arrays.asList(gameDungeon.currentRoom.monsters)));
                    gameDungeon.currentRoom.complete = roomBattle.fight();
                    return null;
                },
                () -> {
                    logger.error("In progress. Expect Bugs");
                    if (gameDungeon.currentRoom.complete) {
                        logger.error("All traps complete!");
                        update(mainMenu());
                    } else {
                        gameDungeon.currentRoom.complete = solver.puzzle(gameDungeon.currentRoom.traps);
                    }
                    return null;
                },
                () -> {
                    main.titleScreen();
                    ui.currentStage.draw((short) ui.terminal.getTerminalSize().getRows(),
                            (short) ui.terminal.getTerminalSize().getColumns());
                    return null;
                }

        };
        menu = ui.createMap(commands, callables);
        return menu;
    }

    public static TreeMap<String, Callable<Void>> moveMenu() {
        TreeMap<String, Callable<Void>> menu;
        String[] directions = { "North", "East", "South", "West", "Back" };
        Callable[] callables = { () -> {
            gameDungeon.moveRooms('n');
            update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            Thread.sleep(1 * 1000);
            update(mainMenu());
            return null;
        },
                () -> {
                    gameDungeon.moveRooms('e');
                    update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
                    Thread.sleep(1 * 1000);
                    update(mainMenu());
                    return null;
                },
                () -> {
                    gameDungeon.moveRooms('s');
                    update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
                    Thread.sleep(1 * 1000);
                    update(mainMenu());
                    return null;
                },
                () -> {
                    gameDungeon.moveRooms('w');
                    update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
                    Thread.sleep(1 * 1000);
                    update(mainMenu());
                    return null;
                },
                () -> {
                    update(mainMenu());
                    return null;
                }
        };
        menu = ui.createMap(directions, callables);
        return menu;
    }

    public static TreeMap<String, Callable<Void>> blankMenu() {
        TreeMap<String, Callable<Void>> menu = new TreeMap<>();
        String[] blank = { " ", " " };
        Callable[] empty = { () -> {
            return null;
        },
                () -> {
                    return null;
                }
        };
        return menu;
    }
}
