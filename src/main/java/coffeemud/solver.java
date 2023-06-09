package coffeemud;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class solver {
    public static boolean puzzle(dungeons.roomTraps[] traps) throws IOException {
        // Variable setup
        int totalComplete = 0;
        for (dungeons.roomTraps i : traps) {
            logger.debug("Trap: " + i.description + ". Answer = " + i.answer);
            logger.debug("# of traps: " + traps.length + ". Traps complete: " + totalComplete);
            game.update(game.blankMenu(), i.problem);
            totalComplete = totalComplete + submitGuess(typing(), i);
            logger.debug(totalComplete);
        }
        ui.typing = false;
        if (totalComplete == traps.length) {
            try {
                game.update(game.mainMenu());
            } catch(Exception e) {

            }
            return true;
        } else
            return solver.puzzle(traps);
    }

    public static int submitGuess(String guess, dungeons.roomTraps trap) {
        if (guess.equals(trap.answer)) {
            return 1;
        } else
            return 0;
    }

    public static String typing() throws IOException {
        // Variable setup
        KeyStroke keyStroke;
        String guess = "";
        ui.typing = true;

        // Get intput
        while (ui.typing) {
            keyStroke = ui.terminal.readInput();
            char character = keyStroke.getCharacter();
            // Parse input
            if (character == '\n') {
                // submit text
                ui.typing = false;
                break;
            } else {
                if (keyStroke.getKeyType() == KeyType.Backspace) {
                    try {
                        logger.debug(guess);
                        // replace character with space to erase it as space is default
                        ui.textGraphics.setCharacter(guess.length(), 11, ' ');
                        guess = guess.substring(0, guess.length() - 1);
                    } catch (StringIndexOutOfBoundsException e) {
                        // do nothing if there's nothing to delete
                        logger.debug("too many backspaces");
                    }
                } else {
                    guess = guess.concat(character + "");
                }
                ui.textGraphics.putString(new TerminalPosition(0, 11), guess);
                ui.terminal.refresh();
            }
        }
        return guess;
    }
}
