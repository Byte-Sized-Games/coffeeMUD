package coffeemud;

import java.util.Random;

//unit 6 demonstration im crying
//entities are players and monsters
public class entities{
        //the name of the monsters
        //phoenix bad
        String name;

        //whether the character is a monster or not
        boolean monster;

        //were not using an array for all these different stats as that would be confusing
        //using int so they can all nicely interoperate
        int health;
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

        public void attack(entities enemy){
                Random random = new Random();
             int damage = attackMultip*(attackLow + (random.nextInt(attackHigh-attackLow))) - armour;
             enemy.health -= damage;
        }



}
