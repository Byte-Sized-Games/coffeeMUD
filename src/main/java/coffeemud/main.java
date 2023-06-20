package coffeemud;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import java.awt.Font;
import java.awt.*;

import java.io.IOException;
import java.util.concurrent.Callable;

public class main {
    // Perform necessary actions on terminal resize
    public static void doResizeStuff(Terminal terminal, TerminalSize terminalSize) {
        try {
            // Resize the UI and redraw the current stage
            ui.terminal.doResizeIfNecessary();
            ui.currentStage.draw((short) terminalSize.getRows(), (short) terminalSize.getColumns());
            logger.debug(terminalSize.getColumns() + "");
            logger.debug(terminalSize.getRows() + "");
            logger.debug((terminalSize.getColumns() < 60 || terminalSize.getRows() < 20) + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Set up the title screen with menu options
    public static void titleScreen() {
        ui.stage.currentMessage = "Welcome to Batatune II, if you need help, consult the manual included in the game files";
        ui.currentStage = new ui.stage(new ui.status(), ui.createMap(new String[] {"Start Game", "Quit Game", "Credits"}, new Callable[] {() -> {
            game.start();
            return null;
        }, () -> {
            System.exit(0);
            return null;
        }, () -> {
            credits.show();
            return null;
        }}));
    }

    public static void main(String[] args) throws IOException {
        logger.debuggable = false;
        for (String arg : args) {
            switch (arg) {
                case "--debug", "-D", "-d":
                    logger.debug("Debug mode enabled");
                    logger.debuggable = true;
                    break;
                case "--version", "-v":
                    System.out.println("CoffeeDungeon\nVERSION 0.1.0\n");
                    System.exit(0);
                    break;
                case "--test", "--cheat", "-t", "-c", "konami", "uuddlrlrba":
                    logger.sneaky = true;
                    logger.error("CHEAT MODE ENABLED. YOU BETTER KNOW WHAT YOU'RE DOING");
                    break;
                default:
                    logger.info("Unknown argument: " + arg);
                    break;
            }
        }
        logger.debug(item.genName(monsterbook.genName((byte)2), (byte) 0, (byte) 2));
        // Create the terminal and UI components
        Terminal terminalThing = new DefaultTerminalFactory().createTerminalEmulator();
        ui.terminal = new TerminalScreen(terminalThing);
        ui.textGraphics = ui.terminal.newTextGraphics();
        ui.terminal.startScreen();
        ui.terminal.setCursorPosition(new TerminalPosition(100,terminalThing.getTerminalSize().getRows() +2));
        // Set up the title screen
        titleScreen();

        // Perform initial resizing and attach resize listener
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
                    // Handle key inputs when not typing
                    switch (keyStroke.getCharacter()) {
                        case 'w' -> {
                            if (ui.currentStage.menuItems.selectedIndex > 0)
                                ui.currentStage.menuItems.selectedIndex--;
                        }
                        case 's' -> {
                            if (ui.currentStage.menuItems.selectedIndex < ui.currentStage.menuItems.items.size() - 1)
                                ui.currentStage.menuItems.selectedIndex++;
                        }
                        case ' ', '\n' -> ui.currentStage.menuItems.call();
                    }
                    ui.currentStage.menuItems.draw((short) ui.terminal.getTerminalSize().getRows(),(short) ui.terminal.getTerminalSize().getColumns());
                } else {
                    // Handle key inputs when typing
                    char character = keyStroke.getCharacter();
                    if(character == '\n') {
                        // submit text
                        typing = "";
                    } else {
                        if(keyStroke.getKeyType() == KeyType.Backspace) {
                            try {
                                logger.debug(typing);
                                // replace character with space to erase it as space is default
                                ui.textGraphics.setCharacter(typing.length(), 11, ' ');
                                typing = typing.substring(0, typing.length() - 1);
                            } catch(StringIndexOutOfBoundsException e) {
                                // do nothing if there's nothing to delete
                                logger.debug("too many backspaces");
                            }
                        } else {
                            typing = typing.concat(character + "");
                        }
                        ui.textGraphics.putString(new TerminalPosition(0,11),typing);
                    }
                }
                ui.terminal.refresh();
                // some keys don't produce a character and are not used, e.g. control keys
            } catch(NullPointerException e) {
                logger.debug("Invalid key");
            }

        } while (true);
    }
}