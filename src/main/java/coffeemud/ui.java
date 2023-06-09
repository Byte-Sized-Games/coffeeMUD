package coffeemud;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.Callable;

public class ui {
    // Interface for UI methods
    interface uiMethods {
        public void draw(short rows, short columns) throws Exception;

        public void update();
    }
    public static TreeMap<String,Callable<Void>> createMap(String[] menuItems, Callable<Void>[] menuActions) {
        TreeMap<String,Callable<Void>> menu = new TreeMap<>();
        for(int i = 0; i < menuItems.length; i++) {
            menu.put(i + menuItems[i],menuActions[i]);
        }
        return menu;
    }
    public static TreeMap<String, Callable<Void>> createMonsterMap(entity[] monsters) {
        TreeMap<String, Callable<Void>> monsterMenu = new TreeMap<>();
        for(byte i = 0; i < monsters.length; i++) {
            monsterMenu.put(i + monsters[i].name + " ♥" + (monsters[i].health > 0 ? monsters[i].health : "DEAD") + " ", () -> {
                return null;
            });
        }
        return monsterMenu;
    }
    // Current stage of the UI
    public static stage currentStage;

    // Terminal screen and UI properties
    public static TerminalScreen terminal;
    public static boolean typing = false;
    public static TextGraphics textGraphics;

    // Stage class representing a UI stage
    public static class stage implements uiMethods {
        public menu menuItems = new menu();

        // Update the current menu
        public void updateMenu(menu menu) throws IOException {
            menuItems = menu;
            draw((short) terminal.getTerminalSize().getRows(), (short) terminal.getTerminalSize().getColumns());
        }
//        public void updateMonsters(monsterMenu menu) {
//
//        }
        // Draw the UI stage
        public void draw(short rows, short columns) throws IOException {
            logger.debug("Columns " + columns + " " + (columns < 60));
            if (columns < 60 || rows < 20) {
                logger.info("Terminal unusable due to size");
                return;
            }


            // Prepare data for drawing
//            ArrayList<entity> monsters = new ArrayList<>();
//            monsters.add(monsterbook.createGoblin());
//            monsters.add(monsterbook.createTroll());
//            monsters.add(monsterbook.createWitch());
//            monsters.add(monsterbook.createSkeleton())//


            // Clear the terminal screen
            terminal.clear();
            if(game.running && !Objects.isNull(game.gameDungeon.currentRoom.monsters)) new monsterMenu(createMonsterMap(game.gameDungeon.currentRoom.monsters)).draw((short) ((short) -12 + (2*game.gameDungeon.currentRoom.monsters.length)), columns);

            // Draw the heads-up display
            headsUp(currentMessage);

            byte day = 0;
            String days = "Day " + day + " of 7";
            textGraphics = terminal.newTextGraphics();
            textGraphics.putString(new TerminalPosition(0, 0), "Home");
            textGraphics.putString(new TerminalPosition(columns - days.length(), 0), days);

            // Draw the menu items
            menuItems.draw(rows,columns);


            // Draw the monster information
//            for(entity monster : monsters) {
//                textGraphics.putString(new TerminalPosition(columns - monster.name.length() - (Integer.toString(monster.health).length() + 2),2*iterator),monster.name + " ♥" + monster.health);
//                iterator++;
//            }


            // Draw player information
            textGraphics.putString(new TerminalPosition(0, 2), "You ♥ " + player.health);
            textGraphics.putString(new TerminalPosition(0,4), "⮹ " + player.level);
            textGraphics.putString(new TerminalPosition(0, 6), "₲: " + player.gold);

            // Refresh the terminal screen
            terminal.refresh();
        }

        @Override
        public void update() {
            // Update the stage
        }

        public String name;
        public static String currentMessage = "Best Dungeon System Movement";

        // Draw the heads-up display with a given message
        public static void headsUp(String message) {
            short rows = (short) terminal.getTerminalSize().getRows();
            short columns = (short) terminal.getTerminalSize().getColumns();
            short lines = (short) Math.ceil((double)message.length()/(double)columns);
            String pushMsg;
            for(byte i = 0; i < lines; i++) {
                pushMsg = message.substring(i*(message.length()/lines),(i + 1) * message.length()/lines);
                textGraphics.putString(new TerminalPosition(columns/2 - pushMsg.length()/2,rows - (10 - i)),pushMsg);

            }
        }
        public status status;

        void onSizeChange() {
            logger.debug("Size changed");
        }
        // constructor for stage creation
        public stage(status status, TreeMap<String, Callable<Void>> menuItems) {
            this.menuItems = new menu(menuItems);
            this.status = status;
        }

    }
    // scrapped fightscene template
    public static class fightScene extends stage {
        public fightScene(String name, ArrayList<entity> monsters, status status, TreeMap<String, Callable<Void>> menuItems) {
            super(status, menuItems);
            this.name = name;
        }
    }
    // class for multiple-choice menus
    public static class menu {
        public TreeMap<String, Callable<Void>> items;
        public byte selectedIndex = 0;
        public menu(TreeMap<String, Callable<Void>> items) {
            logger.debug("Creating menu");
            this.items = items;
        }
        public menu() {
            this.items = new TreeMap<>();
        }
        // draws menu items and places the cursor next to the selected item
        byte gap = 1;
        boolean alignment = false;
        TerminalPosition tpos;
        public void draw(short rows, short columns) {
            logger.debug("Drawing menu");
            for(byte i = 0; i < items.size(); i++) {
                tpos = new TerminalPosition((alignment ? (columns - (items.keySet().toArray()[i].toString().length() +1)) : 0), rows - 6*gap + i*gap);
                logger.debug("Drawing menu item "  + i);
                textGraphics.putString(tpos, (selectedIndex == i ? "*": "> ") + " " + items.keySet().toArray()[i].toString().substring(1) + " ");
                if(selectedIndex == i) terminal.setCursorPosition(tpos);
            }
        }
        // runs a menu item's callable if possible
        public void call() {
            logger.debug("Calling menu item " + selectedIndex);
            try {
                items.get(items.keySet().toArray()[selectedIndex].toString()).call();
            } catch (Exception e) {
                for(StackTraceElement line : e.getStackTrace()) {
                    logger.error(line.toString());
                }
            }
        }
    }
    public static class monsterMenu extends menu {
        public monsterMenu(TreeMap<String, Callable<Void>> items) {
            super(items);
            alignment = true;
            gap = -2;
            selectedIndex = -1;
        }
        @Override
        public void call() {
            logger.debug("Attacking monster: " + selectedIndex);
        }
    }
    public static class status {
        public static void draw() {
            logger.debug("Drawing status bar");
        }
    }
    public static class menuItem {
        String name;

        public void onClick() {

        }
    }

}