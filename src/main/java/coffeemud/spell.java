package coffeemud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class spell {
    // Type of spell. Types: d = damage, h = heal, b = buff, x = debuff
    char type;
    // Level, what level you need to be for the spell
    int cost;
    // How much damage the spell heals or does
    int dmg;
    // Spell Name
    String name;
    // A short description of the spell
    String desc;

    public spell(int startLine) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("spells.txt"));
            if (startLine == 0) {
                this.name = reader.readLine();
                this.type = reader.readLine().charAt(0);
                this.cost = Integer.valueOf(reader.readLine());
                this.dmg = Integer.valueOf(reader.readLine());
            } else {
                for (int i = 0; i < startLine -1; i++) {
                    reader.readLine();
                }
                this.name = reader.readLine();
                this.type = reader.readLine().charAt(0);
                this.cost = Integer.valueOf(reader.readLine());
                this.dmg = Integer.valueOf(reader.readLine());
            }
            
            reader.close();

        } catch (Exception e) {

        }

    }
}