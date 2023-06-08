package coffeemud;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
// ♥
// 🎒
// 🏹
public class ui {
    interface uiMethods {
        public void draw(byte rows, byte columns) throws IOException;

        public void update();
    }

    public static stage currentStage;
    public static TerminalScreen terminal;
    public static boolean typing = false;
    public static TextGraphics textGraphics;

    public static class stage implements uiMethods {
        public menu menuItems = new menu();
        public void updateMenu(menu menu) throws IOException {
            menuItems = menu;
            draw((byte) terminal.getTerminalSize().getRows(), (byte) terminal.getTerminalSize().getColumns());
        }
        public void draw(byte rows, byte columns) throws IOException {
            if (columns < 20 || rows < 10) {
                logger.info("Terminal unusable due to size");
                return;
            }
            ArrayList<entities> monsters = new ArrayList<>();
            monsters.add(monsterbook.goblin);
            byte iterator = 1;

            terminal.clear();
            headsUp(currentMessage, rows, columns);
            byte day = 0;
            String days = "Day " + day + " of 7";
            textGraphics = terminal.newTextGraphics();
            textGraphics.putString(new TerminalPosition(0, 0), "Home");
            textGraphics.putString(new TerminalPosition(columns - days.length(), 0), days);
            menuItems.draw(rows);
            for(entities monster : monsters) {
                textGraphics.putString(new TerminalPosition(columns - monster.name.length() - (Integer.toString(monster.health).length() + 2),2*iterator),monster.name + " ♥" + monster.health);
                iterator++;
            }
            textGraphics.putString(new TerminalPosition(0, 2), "You ♥ " + 21/*health*/);
            textGraphics.putString(new TerminalPosition(0,3),"₤ " + /*money */ 12 );
            textGraphics.putString(new TerminalPosition(0,4), "⮹ " + /*level*/ + 10);
            terminal.refresh();
        }

        @Override
        public void update() {

        }

        public String name;
//        public Terminal buildTerminal;
        public static String currentMessage = "Welcome to Batatune II";
        public static void headsUp(String message, byte rows, byte columns) {
            byte lines = (byte) Math.ceil((double)message.length()/(double)columns);
            String pushMsg;
            for(byte i = 0; i < lines; i++) {
                pushMsg = message.substring(i*(message.length()/lines),(i + 1) * message.length()/lines);
                textGraphics.putString(new TerminalPosition(columns/2 - pushMsg.length()/2,rows - (10 - i)),pushMsg);
            }
//            textGraphics.putString(new TerminalPosition(columns/2 - message.length()/2, rows - 7), (currentMessage = message));
        }
        public status status;

        void onSizeChange() {
            logger.debug("Size changed");
        }

        public stage(status status, HashMap<String, Callable<Void>> menuItems) {
            this.menuItems = new menu(menuItems);
            this.status = status;
        }

    }
    public static class fightScene extends stage {
        public fightScene(String name, ArrayList<entities> monsters, status status, HashMap<String, Callable<Void>> menuItems) {
            super(status, menuItems);
            this.name = name;
        }
    }
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
        public void draw(byte rows) {
            logger.debug("Drawing menu");
            for(byte i = 0; i < items.size(); i++) {
                logger.debug("Drawing menu item "  + i);
                textGraphics.putString(new TerminalPosition(0, rows - 6 + i), (selectedIndex == i ? "*": "> ") + " " + items.keySet().toArray()[i].toString() + " ");
            }
        }
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
//    public stage createStage() {
//        return new stage(new status());
//    }
    public static void show(stage stage) throws IOException, InterruptedException {
        String cmd = "echo \"$LINES\""; // Replace with your desired command

        Process process = new ProcessBuilder("/bin/bash", "-c", cmd).start();
    }
}