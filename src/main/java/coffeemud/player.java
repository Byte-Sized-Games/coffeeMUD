package coffeemud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class player {
    int health = 100;
    int maxHealth = 100;
    int tempHealth = 0;
    String name;
    int armour = 5;
    int attackLow = 1;
    int attackHigh = 20;
    int level = 1;
    inventory inv;
    ArrayList<Character> effects;

    // Players class. Used for spell lists and abilities
        /*
         * f = fighter
         * w = wizard
         * r = rogue
         * c = cleric
         */
        char characterClass;

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
