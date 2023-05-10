package coffeemud;

import java.io.IOException;

//unit 6 demonstration
public class monsters {

        //the name of the monsterss
        //amoung
        String name;

        //the type of the monster, some monsters are weaker against others
        char type;

        int health;
        int armour;

        //attackLow and attackHigh form a range of damages that a monster could do on a turn, selected randomly from that range
        int attackLow;
        int attackHigh;

        //a higher or lower attack that will be done on some special turns
        int attackMultip;

        //a special thing that some monsters can do
        int specialAbility;

        //if monsters can heal, they will heal by this much
        int heal;
        inventory mInventory;

        public void initInventory() throws IOException {
                this.mInventory = new inventory(); 
        }

}
