package coffeemud;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class ui {
    // Interface for UI methods
    interface uiMethods {
        public void draw(short rows, short columns) throws IOException;

        public void update();
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

        // Draw the UI stage
        public void draw(short rows, short columns) throws IOException {
            logger.debug("Columns " + columns + " " + (columns < 60));
            if (columns < 60 || rows < 20) {
                logger.info("Terminal unusable due to size");
                return;
            }

            // Prepare data for drawing
            ArrayList<entities> monsters = new ArrayList<>();
            monsters.add(monsterbook.goblin);
            monsters.add(monsterbook.troll);
            monsters.add(monsterbook.witch);
            monsters.add(monsterbook.skeleton);
            monsters.add(monsterbook.skeleton);
            byte iterator = 1;

            // Clear the terminal screen
            terminal.clear();

            // Draw the heads-up display
            headsUp(currentMessage, rows, columns);

            byte day = 0;
            String days = "Day " + day + " of 7";
            textGraphics = terminal.newTextGraphics();
            textGraphics.putString(new TerminalPosition(0, 0), TextColor.ANSI.BLUE + "Home");
            textGraphics.putString(new TerminalPosition(columns - days.length(), 0), days);

            // Draw the menu items
            menuItems.draw(rows);

            // Draw the monster information
            for(entities monster : monsters) {
                textGraphics.putString(new TerminalPosition(columns - monster.name.length() - (Integer.toString(monster.health).length() + 2),2*iterator),monster.name + " ♥" + monster.health);
                iterator++;
            }

            // Draw player information
            textGraphics.putString(new TerminalPosition(0, 2), "You ♥ " + player.health);
            textGraphics.putString(new TerminalPosition(0,4), "⮹ " + player.level);

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
        public static void headsUp(String message, short rows, short columns) {
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
        public stage(status status, HashMap<String, Callable<Void>> menuItems) {
            this.menuItems = new menu(menuItems);
            this.status = status;
        }

    }
    // scrapped fightscene template
    public static class fightScene extends stage {
        public fightScene(String name, ArrayList<entities> monsters, status status, HashMap<String, Callable<Void>> menuItems) {
            super(status, menuItems);
            this.name = name;
        }
    }
    // class for multiple-choice menus
    public static class menu {
        public HashMap<String, Callable<Void>> items;
        public byte selectedIndex = 0;
        public menu(HashMap<String, Callable<Void>> items) {
            logger.debug("Creating menu");
            this.items = items;
        }
        public menu() {
            this.items = new HashMap<>();
        }
        // draws menu items and places the cursor next to the selected item
        public void draw(short rows) {
            logger.debug("Drawing menu");
            for(byte i = 0; i < items.size(); i++) {
                logger.debug("Drawing menu item "  + i);
                textGraphics.putString(new TerminalPosition(0, rows - 6 + i), (selectedIndex == i ? "*": "> ") + " " + items.keySet().toArray()[i].toString() + " ");
                if(selectedIndex == i) terminal.setCursorPosition(new TerminalPosition(0, rows - 6 + i));
            }
        }
        // runs a meny item's callable if possible
        public void call() {
            logger.debug("Calling menu item " + selectedIndex);
            try {
                items.get(items.keySet().toArray()[selectedIndex].toString()).call();
            } catch (Exception e) {
                logger.debug(e.getStackTrace().toString());
            }
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