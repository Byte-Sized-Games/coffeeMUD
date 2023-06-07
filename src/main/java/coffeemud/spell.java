package coffeemud;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;
public class spell {
    // Type of spell. Types: d = damage, h = heal, b = buff, x = debuff
    char type;
    // Element/effect of spell, used for spells that inflict buffs/debuffs or have an element
    /*
     * n = no effect, spell does not buff/debuff
     * b = banisher, remove an enemy entirely from combat
     * p = protect, defend against one attack
     * B = bless, increase armor temporarily
     * s = summon, create an ally
     * a = change alignment of creature, changes who they can attack
     * f = fire
     * i = ice
     * P = plants
     * e = electricity
     * r = rage, increase attack
     */
    char effect;
    // Level, what level you need to be for the spell
    int level;
    // How much damage the spell heals or does
    int dmg;
    // Spell Name
    String name;
    // A short description of the spell
    String desc;

    public spell() {
        type = 'b';
        level = 0;
        effect = 'n';
    }
    public spell(char t, int d, char e, int l, String name, String tome) {
        this.set(t, d, e, l, name, tome);
    }

    /* Object functions */

    public void set(char t, int d, char e, int l, String newName, String tome) {
        this.type = t;
        this.dmg = d;
        this.effect = e;
        this.level = l;
        this.name = newName;
        this.desc = tome;
    }
    public String read() {
        return this.name + "\n" + this.desc;
    }
    public void cast(ArrayList<entities> creatures) {
        for (entities i : creatures) {
            switch(this.type) {
                case 'd':
                i.health -= dmg;
                case 'h':
                i.health += dmg;
                case 'b':
                    switch(this.effect) {
                        case 'p':
                            i.tempHP += this.dmg;
                        case 'B':
                            i.tempHP += (int) (Math.random() * (dmg - (dmg/4) + 1) + (dmg/4));
                        case 's':
                        case 'a':
                            if (i.monster) {
                                i.monster = false;
                            } else i.monster = true;
                    }
                case 'x':
                    switch(this.effect) {
                        case 'b':
                            i.health = 0;
                        default:
                            i.effects.add('x');
                    }
                default:
                i.health -= dmg;

            }
        }
    }
    /* Class Functions */

    public static void learn(spell newSpell, int playerLevel, inventory inv) throws IOException {
        // Empty for now, will contain code to interface with a character's inventory/spellbook, so that they may use it
        if (playerLevel < newSpell.level) {
            logger.debug("You cannot learn this spell! You need to reach level " + newSpell.level + " to learn it!");
        } else {
            logger.debug("Spell learnt. You can now cast " + newSpell.name);
            inv.spells.put(newSpell.name, true);

        }
    }
    public static void unlearn(spell oldSpell, inventory inv) throws IOException {
        // Also empty, will interact with player inventory to remove spells
        inv.spells.put(oldSpell.name, false);
    }
}