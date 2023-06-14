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
    // public static inventory inv;
    

    public static ArrayList<Character> effects;

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
        /*try {
            inv = new inventory();
        }
        catch(IOException e) {
            logger.info(e.getMessage());
        }*/
    }

    public static int attack() {
        Random random = new Random();
        int damage = level * (attackLow + (random.nextInt(attackHigh - attackLow)));
        if (damage < 0)
            damage = 0;
        return damage;
    }

    public static void die() {
        logger.info("You have died.");
        System.exit(0);
    }

    public static void levelUP() {
        level++;
        maxHealth = maxHealth + (25*level);
        health = maxHealth;
        attackLow = 1*level;
        attackHigh = 15*level;
        armour = 5*level;
    }
}