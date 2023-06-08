package coffeemud;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class credits {
    static Callable<Void> filler = () -> {
        return null;
    };
    static String[] credits = {"Frontend by Phoenix Barr", "Backend by Michael Ward, Phoenix Barr, and Brandon Thomas", "Gameplay design by Brandon Thomas","Awful terminal graphics library that barely works by the Lanterna developers"};
    static HashMap<String, Callable<Void>> menu = new HashMap<>();
    public static void show() throws IOException {
        for(String credit : credits) menu.put(credit, filler);
        menu.put("Back", () -> {
            main.titleScreen();
            ui.currentStage.draw((short) ui.terminal.getTerminalSize().getRows(), (short) ui.terminal.getTerminalSize().getColumns());
            return null;
        });
        ui.stage.currentMessage = "Thanks for playing coffeeDungeon!";
        (ui.currentStage = new ui.stage(new ui.status(),menu)).draw((short) ui.terminal.getTerminalSize().getRows(),(short) ui.terminal.getTerminalSize().getColumns());
    }
}
