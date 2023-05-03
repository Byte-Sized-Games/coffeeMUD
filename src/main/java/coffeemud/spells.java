package coffeemud;
public class spells {
    // Type of spell. Types: d = damage, h = heal, b = buff, d = debuff
    char type;
    // Element/effect of spell
    /*
     * n = null, no effect
     * m = magic, generic spell
     * f = fire, good againt plants
     * w = water, good against fire
     * e = electricity, good against water
     * p = plants, good against electricity
     * b = banisher, remove an enemy entirely from combat
     * p = protect, defend against one attack
     * B = bless, increase armor temporarily
     * s = summon, create an ally
     * a = change alignment of creature, changes who they can attack
     */
    char effect;
    // Level, what level you need to be for the spell
    int level;
    // How much damage the spell heals or does
    int dmg;
    // A short description of the spell
    String desc;

    public spells() {
        type = 'b';
        level = 0;
        effect = 'n';
    }
    public spells(char t, int d, char e, int l, String tome) {
        this.set(t, d, e, l, tome);
    }

    /* Object functions */

    public void set(char t, int d, char e, int l, String tome) {
        this.type = t;
        this.dmg = d;
        this.effect = e;
        this.level = l;
        this.desc = tome;
    }
    public String read() {
        return desc;
    }
    public void cast() {
        // Currently empty, to be filled when player and inventory programs are more fleshed out
    }
    /* Class Functions */

    public static void learn(spells newSpell, int playerLevel) {
        // Empty for now, will contain code to interface with a character's inventory/spellbook, so that they may use it
        if (playerLevel < newSpell.level) {
            logger.debug("You cannot learn this spell! You need to reach level " + newSpell.level + " to learn it!");
            return;
        }
    }
    public static void unlearn(spells oldSpell) {
        // Also empty, will interact with player inventory to remove spells
    }
}