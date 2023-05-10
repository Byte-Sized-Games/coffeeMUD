package coffeemud;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.StringBuffer;
import java.io.Console;

public class ui {
    public class panel {
        boolean expanded;
        public panel(boolean expanded) {
            this.expanded = expanded;
        }
    }
    public stage currentStage;
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
        public void draw() {
//            logger.info(buildTerminal.getSize().toString());
        }
    }
    public class fightScene extends stage {
        public fightScene(String name, ArrayList<monsters> monsters) {
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