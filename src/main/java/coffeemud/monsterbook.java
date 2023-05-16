package coffeemud;

public class monsterbook {
    public static void goblin() {
        entities goblin = new entities();

        goblin.health = 100;
        goblin.armour = 0;
        goblin.attackLow = 8;
        goblin.attackHigh = 15;
        goblin.attackMultip = 1;
        goblin.heal = 0;
    }

    public static void bat() {
        entities bat = new entities();

        bat.health = 25;
        bat.armour = 0;
        bat.attackLow = 2;
        bat.attackHigh = 8;
        bat.attackMultip = 1;
        bat.heal = 0;
    }
}
