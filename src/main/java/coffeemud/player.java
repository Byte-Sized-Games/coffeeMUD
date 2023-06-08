package coffeemud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class player {
    int health;
    int maxHealth;
    int tempHealth;
    String name;
    int armour;
    int attackLow;
    int attackHigh;
    int level;
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

    public player(int h, int mh, String n, int a, int al, int ah) throws IOException {
        this.init(h, mh, n, a, al, ah);
    }

    public void init(int h, int mh, String n, int a, int al, int ah) throws IOException {
        this.health = h;
        this.maxHealth = mh;
        this.name = n;
        this.armour = a;
        this.attackLow = al;
        this.attackHigh = ah;
        this.level = 0;
        this.tempHealth = 0;
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
}
