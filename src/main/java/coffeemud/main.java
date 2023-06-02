package coffeemud;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class main {
    public static void doResizeStuff(Terminal terminal, TerminalSize terminalSize) {
        try {
            ui.terminal.doResizeIfNecessary();
            ui.currentStage.draw(terminalSize.getRows(), terminalSize.getColumns());
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
        Terminal balls = new DefaultTerminalFactory().createTerminalEmulator();
        ui.terminal = new TerminalScreen(balls);
        ui.terminal.startScreen();
        ui.currentStage = new ui.stage();
        doResizeStuff(balls, balls.getTerminalSize());
        balls.addResizeListener(main::doResizeStuff);
    }
}