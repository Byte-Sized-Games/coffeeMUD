package coffeemud;


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ui {
    public static stage currentStage;
    public static TerminalScreen terminal;
    public static TextGraphics textGraphics;
    public class panel {
        boolean expanded;
        public panel(boolean expanded) {
            this.expanded = expanded;
        }
    }
    public static class stage {
        public String name;
//        public Terminal buildTerminal;


        public boolean statusBar;
        public ArrayList<panel> contents;
        void onSizeChange() {
            logger.debug("Size changed");
        }
        public stage() {

        }
        public void createMenu() {
            logger.debug("Creating menu");
        }
        public void draw(int rows, int columns) throws IOException {
            if(columns < 20 || rows < 10) {
                logger.info("Terminal too small");
                return;
            }
            terminal.clear();

            logger.debug("fortnite burger");
            byte day = 0;
            String days = "Day " + columns + " of 7";
            textGraphics = terminal.newTextGraphics();
            textGraphics.putString(new TerminalPosition(0,0), "Serbia");
            textGraphics.putString(new TerminalPosition(columns - days.length(),0), days);
//            textGraphics.putString(new TerminalPosition(100,2), "fornite balls");
            terminal.refresh();
        }
    }
    public class fightScene extends stage {
        public fightScene(String name, ArrayList<entities> monsters) {
            this.name = name;
            statusBar = true;
            contents.add(new panel(true));
        }
    }
    public static class menuItem {
        String name;

        public void onClick() {

        }
    }
    public stage createStage() {
        return new stage();
    }
    public static void show(stage stage) throws IOException, InterruptedException {
        String cmd = "echo \"$LINES\""; // Replace with your desired command

        Process process = new ProcessBuilder("/bin/bash", "-c", cmd).start();
    }
}