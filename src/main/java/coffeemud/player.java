package coffeemud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class player {
    public static int health = 100;
    public static int maxHealth = 100;
    public static int tempHealth = 0;
    public static String name;
    public static int armour = 5;
    public static int attackLow = 1;
    public static int attackHigh = 20;
    public static int level = 1;
    public static inventory inv;

    static {
        try {
            inv = new inventory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Character> effects;

    // Players class. Used for spell lists and abilities
        /*
         * f = fighter
         * w = wizard
         * r = rogue
         * c = cleric
         */
        public static char characterClass;

    public player( String n, char c) throws IOException {
        this.init(n, c);
    }

    public void init(String n, char c) throws IOException {
        this.name = n;
        this.characterClass = c;
        this.inv = new inventory();
    }

    public int attack() {
        Random random = new Random();
        int damage = level * (attackLow + (random.nextInt(attackHigh - attackLow)));
        if (damage < 0)
            damage = 0;
        return damage;
    }

    public void die() {
        logger.info("You are dead.");
    }

    public void levelUP() {
        level++;
        this.maxHealth = maxHealth + (25*level);
        this.health = maxHealth;
        this.attackLow = 1*level;
        this.attackHigh = 15*level;
        this.armour = 5*level;
    }
}
