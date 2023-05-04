package coffeemud;
import jdk.internal.org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.lang.StringBuffer;
import java.awt.Canvas;
public class ui {
    public class panel {
        boolean expanded;
        public panel(boolean expanded) {
            this.expanded = expanded;
        }
    }
    public stage currentStage;
    public class stage {
        public String name;
        public Terminal buildTerminal;


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
            logger.info(buildTerminal.getSize().toString());
        }
    }
    public class fightScene extends stage {
        public fightScene(String name, ArrayList<monster> monsters) {
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
    public void show(stage stagE) {
        logger.debug("Showing stage");
    }
}
class monster {
    public monster() {
        throw new UnsupportedOperationException("unfinished");
    }
}