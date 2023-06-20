package coffeemud;

import java.io.IOException;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.Arrays;

public class game {
    public static item drop;
    public static boolean running = false;
    static Callable<Void> filler = () -> {
        return null;

    };
    static String name;
    public static battle roomBattle;
    static player protag;
    static dungeons gameDungeon;
    public static int pX = 9;
    public static int pY = 9;

    public static void start() throws IOException, Exception {
        // Load dungeon & player
        logger.debug("Starting game");
        running = true;
        player.spellList.add(spellbook.spells[dungeons.randRange(0, 5)]);
        player.spellList.add(spellbook.spells[dungeons.randRange(0, 5)]);
        player.spellList.add(spellbook.spells[6]);
        logger.debug("Generating dungeons");
        gameDungeon = new dungeons(pX, pY);
        logger.debug("Dungeons generated");
        logger.debug("Loading Player");
        protag = new player('f');
        logger.debug("Player loaded");
        // Init graphics
        update(mainMenu());
    }

    public static void update(TreeMap<String, Callable<Void>> menu) throws IOException, Exception {
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
            if (gameDungeon.currentRoom.complete || logger.sneaky || player.skips-- >= 0) {
                if (player.skips < 0) player.skips = 0;
                player.gold += gameDungeon.currentRoom.gold;
                gameDungeon.currentRoom.gold = 0;
                if (player.gold >= 100) {
                    player.levelUP(2);
                } else if (player.gold >= 200) {
                    player.levelUP(3);
                } else if (player.gold >= 400) {
                    player.levelUP(4);
                } else if (player.gold >= 1000) {
                    logger.error("You have won");
                    System.exit(0);
                }
                update(moveMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            } else {
                logger.error("Room not complete!");
            }
            return null;
        },
                () -> {
                    logger.error("In progress. Expect Bugs");
                    if (gameDungeon.currentRoom.complete) {
                        logger.info("All traps complete!");
                        update(mainMenu());
                    } else {
                        roomBattle = new battle(
                                new ArrayList<entity>(Arrays.asList(gameDungeon.currentRoom.monsters)));
                        gameDungeon.currentRoom.complete = roomBattle.fight();
                    }
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
    public static void pickupMenu() {
        ui.stage.headsUp("You received " + drop.name);
        try {
            ui.currentStage.updateMenu(new ui.menu(ui.createMap(new String[] { "Receive","Ignore" },
                    new Callable[] { () -> {
                        for(item slot : player.inventory) {
                            if(Objects.isNull(slot)) {
                                slot = drop;
                                drop.get();
                                drop = null;
                                update(mainMenu());
                                return null;
                            }
                        }
                        game.itemDeleteMenu();
                        return null;
                    }, () -> {
                        update(mainMenu());
                        return null;
                    } })));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void itemUseMenu() {
        logger.debug("menuballs");
        ui.stage.headsUp("Select an item to use");
        String[] itemNames = new String[player.inventory.length];
        for (byte i = 0; i < player.inventory.length; i++) {
            if(!Objects.isNull(player.inventory[i])){
                itemNames[i] = player.inventory[i].name;
            } else {
                itemNames[i] = "Empty";
            }
        }
        try {
            ui.currentStage.updateMenu(new ui.menu(ui.createMap(itemNames, new Callable[] {
                    () -> {
                        if(Objects.isNull(player.inventory[0])) {
                            update(roomBattle.battleMenu());
                            return null;
                        }
                        item.use(player.inventory[0]);
                        update(roomBattle.battleMenu());
                        return null;
                    }, () -> {
                if(Objects.isNull(player.inventory[1])) {
                    update(roomBattle.battleMenu());
                    return null;
                }
                item.use(player.inventory[1]);
                update(roomBattle.battleMenu());

                return null;
            }, () -> {
                if(Objects.isNull(player.inventory[2])) {
                    update(roomBattle.battleMenu());
                    return null;
                }
                item.use(player.inventory[2]);
                update(roomBattle.battleMenu());

                return null;
            }, () -> {
                if(Objects.isNull(player.inventory[3])) {
                    update(roomBattle.battleMenu());
                    return null;
                }
                item.use(player.inventory[3]);
                update(roomBattle.battleMenu());

                return null;
            }, () -> {
                if(Objects.isNull(player.inventory[4])) {
                    update(roomBattle.battleMenu());
                    return null;
                }
                item.use(player.inventory[4]);
                update(roomBattle.battleMenu());

                return null;
            }
            })));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void itemDeleteMenu() {
        ui.stage.headsUp("Select an item to replace");
        String[] itemNames = new String[player.inventory.length];
        for (byte i = 0; i < player.inventory.length; i++) {
            itemNames[i] = player.inventory[i].name;
        }
        try {
            ui.currentStage.updateMenu(new ui.menu(ui.createMap(itemNames, new Callable[] {
                () -> {
                    player.inventory[0].safeRemove();
    //                update(mainMenu());
                    return null;
                }, () -> {
                    player.inventory[1].safeRemove();
                    return null;
                }, () -> {
                    player.inventory[2].safeRemove();
                    return null;
                }, () -> {
                    player.inventory[3].safeRemove();
                    return null;
                }, () -> {
                    player.inventory[4].safeRemove();
                    return null;
                }
            })));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static TreeMap<String, Callable<Void>> moveMenu() {
        TreeMap<String, Callable<Void>> menu;
        String[] directions = { "North", "East", "South", "West", "Back" };
        Callable[] callables = { () -> {
            player.health += gameDungeon.currentRoom.heal;
            gameDungeon.moveRooms('n');
            update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
            Thread.sleep(2 * 1000);
            update(mainMenu());
            return null;
        },
                () -> {
                    player.health += gameDungeon.currentRoom.heal;
                    gameDungeon.moveRooms('e');
                    update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
                    Thread.sleep(2 * 1000);
                    update(mainMenu());
                    return null;
                },
                () -> {
                    player.health += gameDungeon.currentRoom.heal;
                    gameDungeon.moveRooms('s');
                    update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
                    Thread.sleep(2 * 1000);
                    update(mainMenu());
                    return null;
                },
                () -> {
                    player.health += gameDungeon.currentRoom.heal;
                    gameDungeon.moveRooms('w');
                    update(blankMenu(), "X: " + gameDungeon.x + ", Y: " + gameDungeon.y);
                    Thread.sleep(2 * 1000);
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
        return new TreeMap<>();
    }
}
