package coffeemud;

import java.util.ArrayList;

public class player {
    public static int health = 100;
    public static byte lives = 0;
    public static item[] inventory = new item[5];
    public static byte skips = 0;
    public static int maxHealth = 100;
    public static int tempHealth = 0;
    public static String name;
    public static int armour = 2;
    public static int attackLow = 1;
    public static int attackHigh = 20;
    public static int mana = 30;
    public static int level = 1;
    public static int gold;
    public static inventory inv = new inventory();
    public static ArrayList<spell> spellList = new ArrayList<spell>();

    
    public static ArrayList<Character> effects = new ArrayList<>('0');

    // Players class. Used for spell lists and abilities
        /*
         * f = fighter
         * w = wizard
         * r = rogue
         * c = cleric
         */
    public static char characterClass;

    public player(char c) {
        characterClass = c;
    }

    public static int attack() {

        int damage = level * (int) ((Math.random() * (attackHigh - attackLow)) + attackLow);
        if (damage < 0)
            damage = 0;
        return damage;
    }

    public static void die() {
        if(lives > 0) {
            lives--;
            health = maxHealth;
            logger.info("You have died. You have " + lives + " lives left.");
            return;
        } else {
            logger.info("You have died.");
            System.exit(0);
        }
    }

    public static void levelUP(int i) {
        level = i;
        maxHealth = maxHealth + (25*level);
        health = maxHealth;
        mana = 30*level;
        attackLow = 1*level;
        attackHigh = 15*level;
        armour = 1*level;
    }
}