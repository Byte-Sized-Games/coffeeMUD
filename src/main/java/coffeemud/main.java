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
            ui.currentStage.draw((short) terminalSize.getRows(), (short) terminalSize.getColumns());
            logger.debug(terminalSize.getColumns() + "");
            logger.debug(terminalSize.getRows() + "");
            logger.debug((terminalSize.getColumns() < 60 || terminalSize.getRows() < 20) + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void titleScreen() {
        HashMap<String, Callable<Void>> menuThing = new HashMap<>();
        menuThing.put("Start Game", () -> {
            return null;
        });
        menuThing.put("Quit Game", () -> {
            System.exit(0);
            return null;
        });
        menuThing.put("Credits", () -> {
            credits.show();
            return null;
        });
        ui.currentStage = new ui.stage(new ui.status(),menuThing);
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
//        dungeons.currentRoom.name = "Home";
        Terminal terminalThing = new DefaultTerminalFactory().createTerminal();
        ui.terminal = new TerminalScreen(terminalThing);
        ui.textGraphics = ui.terminal.newTextGraphics();
        ui.terminal.startScreen();
        ui.terminal.setCursorPosition(new TerminalPosition(100,terminalThing.getTerminalSize().getRows() +2));
        titleScreen();
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
                    ui.currentStage.menuItems.draw((short) ui.terminal.getTerminalSize().getRows());
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