package coffeemud;

import java.util.ArrayList;

//unit 6 demonstration im crying
//entities are players and monsters
public class entities {
        // the name of the monsters
        // phoenix bad
        // die
        String name;

        // whether the character is a monster or not
        boolean monster;

        ui.menu options;

        // Players class. Used for spell lists and abilities
        /*
         * f = fighter
         * w = wizard
         * r = rogue
         * c = cleric
         */
        char characterClass;

        // Effects on entity
        ArrayList<Character> effects;

        // were not using an array for all these different stats as that would be
        // confusing
        // using int so they can all nicely interoperate

        // the current health of the entity
        int health;

        // Bonus health, reset every round
        int tempHP = 0;

        // the maximum health of the entity
        int maxHealth;
        int armour;

        // attackLow and attackHigh form a range of damages that a monster could do on a
        // turn, selected randomly from that range
        int attackLow;
        int attackHigh;

        // a special thing that some monsters can do
        spell specialAbility = spellbook.wizard.spells[9];

        // if monsters can heal, they will heal by this much
        int heal = health / 6;

        public entities(String name, int health, int low, int high, int prot) {
                this.name = name;
                this.maxHealth = health;
                this.health = this.maxHealth;
                this.armour = prot;
        }

        public void attack() {
                int damage = (int) ((Math.random() * (attackHigh - attackLow)) + attackLow);
                if (damage < 0)
                        damage = 0;
                player.health -= damage;
        }

        public void heal() {
                if (this.health + this.heal > this.maxHealth)
                        this.health = this.maxHealth;
                else
                        this.health += this.heal;
        }

        public entities(String name, ui.menu options, boolean monster, int maxHealth) {

        }

}
