package coffeemud;

public class monsterbook {

    //the following functions allow to quickly create preset monsters
    public static void goblin() {
        entities goblin = new entities();

        goblin.health = goblin.maxHealth = 100;
        goblin.armour = 0;
        goblin.attackLow = 8;
        goblin.attackHigh = 15;
        goblin.attackMultip = 1;
        goblin.heal = 0;
    }

    public static void bat() {
        entities bat = new entities();

        bat.health = bat.maxHealth = 25;
        bat.armour = 0;
        bat.attackLow = 2;
        bat.attackHigh = 8;
        bat.attackMultip = 1;
        bat.heal = 0;
    }

    public static void witch() {
        entities witch = new entities();

        witch.health = witch.maxHealth = 200;
        witch.armour = 10;
        witch.attackLow = 2;
        witch.attackHigh = 20;
        witch.attackMultip = 1;
        witch.heal = 10;
    }

    public static void skeleton() {
        entities skeleton = new entities();

        skeleton.health = skeleton.maxHealth = 50;
        skeleton.armour = 10;
        skeleton.attackLow = 2;
        skeleton.attackHigh = 20;
        skeleton.attackMultip = 1;
        skeleton.heal = 10;
    }

    public static void troll() {
        entities troll = new entities();

        troll.health = 5;
        troll.maxHealth = 30;
        troll.armour = 25;
        troll.attackLow = 2;
        troll.attackHigh = 20;
        troll.attackMultip = 1;
        troll.heal = 10;
    }

}
