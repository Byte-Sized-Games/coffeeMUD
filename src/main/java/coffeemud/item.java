package coffeemud;

import java.util.Arrays;

public class item {
    public byte type;
    public String name;
    public byte efficacy;
    public String description;
    public item(byte type,String name, byte efficacy) {
        this.type = type;
        this.efficacy = efficacy;
        this.name = name;
    }
    static byte seed;
    public static String genName(String dropperName, byte type, byte efficacy) {
        seed = (byte) Math.round(Math.random() * 2);
        logger.info(seed);
        return switch(efficacy) {
            case 0 -> "";
            case 1 -> dropperName + "'s training";
            case 2 -> dropperName + "'s";
            case 3 -> dropperName + "'s legendary";
            default -> "Anomalous";
        } + " " + switch(type) {
            case 0 -> switch (seed) {
                case 0 -> "chestpiece";
                case 1 -> "helmet";
                default -> "shield";
            };
            case 1 -> switch (seed) {
                case 0 -> "sword";
                case 1 -> "longbow";
                default -> "greatsword";
            };
            case 2 -> switch (seed) {
                case 0 -> "orb of life";
                case 1 -> "crystal heart";
                default -> "talisman";
            };
            case 3 -> switch (seed) {
                case 0 -> "wings";
                case 1 -> "cloak of invisibility";
                default -> "distracting token";
            };
            default -> "corrupted artefact";
        } + (efficacy == 3 ? ", "+(seed == 2 ? "savior" : "vanquisher") + " of " + monsterbook.genName((byte) 0) : "");
    }
    public void get() {
        switch (type) {
            case 0 -> {
                player.maxHealth += efficacy;
                if (player.health > player.maxHealth) player.health = player.maxHealth;
            }
            case 1 -> {
                player.attackLow += efficacy;
                player.attackHigh += efficacy;
            }
            case 2 -> {
                player.lives++;
            }
            case 3 -> {
                player.skips+= efficacy;
            }
        }
    }
    static String purpose;
    public static void use(item currentitem) {
        switch (currentitem.type) {
            case 0 -> {
                player.health = player.maxHealth;
                purpose = "heal yourself fully";
            }
            case 1 -> {
                for(entity monster : battle.monsters) {
                    if(monster.health > 0) monster.health -= currentitem.efficacy * 10 + 20;
                    purpose = "deal " + (currentitem.efficacy * 10 + 20) + " damage to every monster in the dungeon";
                }
            }
            default -> {
                return;
            }
        }
        ui.stage.headsUp("You used " + currentitem.name + " to " + purpose + ".");
        currentitem.safeRemove();
    }
    public void safeRemove() {
        switch (type) {
            case 0 -> {
                player.maxHealth -= efficacy;
                if (player.health > player.maxHealth) player.health = player.maxHealth;
            }
            case 1 -> {
                player.attackLow -= efficacy;
                player.attackHigh -= efficacy;
            }
            case 2 -> {
                player.lives--;
            }
            case 3 -> {
                player.skips -= efficacy;
            }
        }
        Arrays.asList(player.inventory).remove(this);
    }
}
