package coffeemud;

import java.util.ArrayList;
import java.util.Random;

//unit 6 demonstration im crying
//entities are players and monsters
public class entities{
        //the name of the monsters
        //phoenix bad
        // die
        String name;

        //whether the character is a monster or not
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

        //were not using an array for all these different stats as that would be confusing
        //using int so they can all nicely interoperate

        //the current health of the entity
        int health;

        // Bonus health, reset every round
        int tempHP;

        //the maximum health of the entity
        int maxHealth;
        int armour;

        //attackLow and attackHigh form a range of damages that a monster could do on a turn, selected randomly from that range
        int attackLow;
        int attackHigh;

        //a higher or lower attack that will be done on some special turns
        int attackMultip;

        //a special thing that some monsters can do
        spell specialAbility;

        //if monsters can heal, they will heal by this much
        int heal;

        public entities(String name, int health) {
                this.name = name;
                this.health = health;
        }
        public entities() {

        }
        public void attack(entities enemy){
                Random random = new Random();
             int damage = attackMultip*(attackLow + (random.nextInt(attackHigh-attackLow))) - armour;
             if (damage < 0)
                     damage = 0;
             enemy.health -= damage;
        }

        public void heal(){
                if (this.health + this.heal > this.maxHealth)
                        this.health = this.maxHealth;
                else
                        this.health += this.heal;
        }

        public entities(String name, ui.menu options, boolean monster, int maxHealth) {

        }

}

