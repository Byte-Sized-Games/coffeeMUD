package coffeemud;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class main {
    public static void doResizeStuff(Terminal terminal, TerminalSize terminalSize) {
        try {
            ui.terminal.doResizeIfNecessary();
            ui.currentStage.draw((byte) terminalSize.getRows(), (byte) terminalSize.getColumns());
            logger.debug(terminalSize.getColumns() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        for (String arg : args) {
            switch (arg) {
                case "--debug":
                    logger.debug("Debug mode enabled");
                    break;
                default:
                    logger.info("Unknown argument: " + arg);
                    break;
            }
        }
        HashMap<String, Callable<Void>> menuThing = new HashMap<>();
        menuThing.put("Start Game", () -> {
            return null;
        });
        menuThing.put("Quit Game", () -> {
            System.exit(0);
            return null;
        });
        menuThing.put("Credits", () -> {
            HashMap<String, Callable<Void>> items1 = new HashMap<>();
//            items1.putAll(new String[] {"Frontend by Phoenix Barr","Backend by Michael Ward and Brandon Thomas","Concept and story by Michael Ward"},Callable[]);

            ui.currentStage = new ui.stage(new ui.status(),items1);
            return null;
        });
        menuThing.put("Fortnite balls", () -> {
            return null;
        });
        menuThing.put("Fortnite ballasds", () -> {
            return null;
        });
        Terminal terminalThing = new DefaultTerminalFactory().createTerminalEmulator();
        ui.terminal = new TerminalScreen(terminalThing);
        ui.textGraphics = ui.terminal.newTextGraphics();
        ui.terminal.startScreen();
        ui.currentStage = new ui.stage(new ui.status(),menuThing);
        doResizeStuff(terminalThing, terminalThing.getTerminalSize());
        terminalThing.addResizeListener(main::doResizeStuff);
        KeyStroke keyStroke;
        String typing = "";
        do {
            keyStroke = ui.terminal.readInput();
            logger.debug("new input");
            if(keyStroke == null) continue;
            try {
                if(!ui.typing) {
                    switch (keyStroke.getCharacter()) {
                        case 'w' -> {
                            if (ui.currentStage.menuItems.selectedIndex > 0) ui.currentStage.menuItems.selectedIndex--;
                        }
                        case 's' -> {
                            if (ui.currentStage.menuItems.selectedIndex < ui.currentStage.menuItems.items.size() - 1)
                                ui.currentStage.menuItems.selectedIndex++;
                        }
                        case ' ', '\n' -> ui.currentStage.menuItems.call();
                    }
                    ui.currentStage.menuItems.draw((byte) ui.terminal.getTerminalSize().getRows());
                } else {
                    char character = keyStroke.getCharacter();
                    if(character == '\n') {
                        // submit text
                        typing = "";
                    } else {
                        if(keyStroke.getKeyType() == KeyType.Backspace) {
                            try {
                                logger.debug(typing);
                                ui.textGraphics.setCharacter(typing.length(), 11, ' ');
                                typing = typing.substring(0, typing.length() - 1);
                            } catch(StringIndexOutOfBoundsException e) {
                                logger.debug("too many backspaces");
                            }
                        } else {
                            typing = typing.concat(character + "");
                        }
                        ui.textGraphics.putString(new TerminalPosition(0,11),typing);
                    }
                }
                ui.terminal.refresh();
            } catch(NullPointerException e) {
                logger.debug("Invalid key");
            }

        } while (true);
    }
}