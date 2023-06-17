package coffeemud;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;

public class credits {
    static Callable<Void> filler = () -> {
        return null;
    };
    static TreeMap<String, Callable<Void>> creditList = ui.createMap(new String[]{"Frontend by Phoenix Barr", "Backend by Michael Ward, Phoenix Barr, and Brandon Thomas", "Gameplay design by Brandon Thomas and Michael Ward","Awful terminal graphics library that barely works by the Lanterna developers", "Back"},new Callable[]{filler,filler,filler,filler,() -> {
        main.titleScreen();
        ui.currentStage.draw((short) ui.terminal.getTerminalSize().getRows(),(short) ui.terminal.getTerminalSize().getColumns());
        return null;
    }});
    static HashMap<String, Callable<Void>> menu = new HashMap<>();
    public static void show() throws IOException {
        ui.stage.currentMessage = "Thanks for playing coffeeDungeon!";
        (ui.currentStage = new ui.stage(new ui.status(), creditList)).draw((short) ui.terminal.getTerminalSize().getRows(),(short) ui.terminal.getTerminalSize().getColumns());
    }
}
