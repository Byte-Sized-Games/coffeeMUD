package coffeemud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.TreeMap;

public class battle {
    ArrayList<entities> monsters;
    int round;
    uiAlt.prompt battlePrompt = new uiAlt.prompt(colours.red + "[BATTLE! - ",
            colours.yellow + "Round: " + colours.reset + this.round, colours.red + "]");;

    public battle(ArrayList<entities> m) {
        this.init(m);
    }

    public void init(ArrayList<entities> m) {
        this.monsters = m;
        this.round = 0;
    }

    public boolean fight() throws IOException {
        checkEffects();
        game.update(battleMenu(), battleMessage());
        return true;
        
    }

    public void checkEffects() {
        player.tempHealth = player.tempHealth / 4;
        for (char x : player.effects) {
            switch (x) {
                case 'x':
                    player.health = -2;
            }
        }
        if (player.health <= 0) {
            player.die();
        }

        for (entities i : monsters) {
            i.tempHP = i.tempHP / 4;
            if (i.health <= 0) {
                monsters.remove(i);
            }
        }
    }

    public void playerTurn(String command) throws IOException {

        switch (command) {
            case "ATTACK":
                game.update(attackMenu(), battleMessage());
                break;
            case "CAST":
                logger.error("Not yet finished");
                break;
            case "DEFEND":
                player.armour += 2;
                break;
            case "", " ":
                logger.debug("No command chosen, please try again");
                break;
            default:
                logger.error("Command not recognized");
                break;
        }
        monsterTurn();
    }

    public void monsterTurn() {
        for (entities i : monsters) {
            if (i.health <= (i.maxHealth / 4)) {
                i.health += i.heal;
            } else {
                i.attack();
            }
        }
    }

    public void castSpell(String target) {
        spell targetSpell = spellbook.wizard.spells[0];
        logger.debug("Select your targets: ");
        String[] getTargets = battlePrompt.read().split(" ");
        ArrayList<entities> targets = new ArrayList<entities>();
        for (int i = 0; i < getTargets.length; i++) {
            targets.set(i, monsters.get(getEntities(getTargets[i])));
        }
        targetSpell.cast(targets);
    }

    public int getMonster(String target) {
        int i = 0;
        for (entities x : monsters) {
            if (x.name.equals(target)) {
                break;
            } else
                i++;
        }
        return i;
    }

    public int getEntities(String target) {
        ArrayList<entities> battleEntities = new ArrayList<entities>();
        int i = 0;
        for (entities x : monsters) {
            battleEntities.add(x);
        }
        for (entities x : battleEntities) {
            if (x.name.equals(target)) {
                break;
            } else
                i++;
        }
        return i;
    }

    public spell getSpell(String target, char playerClass) {
        while (true) {
            switch (playerClass) {
                case 'w':
                    for (spell i : spellbook.wizard.spells) {
                        if (i.name.equals(target)) {
                            return i;
                        }
                    }
                case 'c':
                    for (spell i : spellbook.cleric.spells) {
                        if (i.name.equals(target)) {
                            return i;
                        }
                    }
                case 'r':
                    logger.debug("You can't cast spells silly! You're the sneaky one");
                    return null;
                case 'f':
                    logger.debug("You can't cast spells silly! You hit things with a sword");
                    return null;
                default:
                    logger.debug("No spell specified, try again");
            }
        }
    }

    public String battleMessage() {
        StringBuilder message = new StringBuilder("There are " + monsters.size() + " monsters. ");
        for (entities i : monsters) {
            message.append("There is a " + i.name + ". It currently has " + i.health + " health remaining. ");
        }

        return message.toString();
    }

    public TreeMap<String, Callable<Void>> battleMenu() {
        TreeMap<String, Callable<Void>> menu;
        String[] commands = {"Attack", "Defend", "Cast"};
        Callable[] callables = {
            () -> {
                playerTurn("ATTACK");
                return null;
            },
            () -> {
                playerTurn("DEFEND");
                return null;
            },
            () -> {
                // To be implemented
                // playerTurn("CAST");
                return null;
            }
        };
        menu = ui.createMap(commands, callables);
        return menu;
    }

    public TreeMap<String, Callable<Void>> attackMenu() {
        String[] names = new String[monsters.size()];
        Callable[] attack = new Callable[monsters.size()];
        for (int i = 0; i < monsters.size() - 1; i++) {
            final int y = i;
            names[i] = monsters.get(i).name;
            attack[i] = () -> {
                monsters.get(y).health -= player.attack();
                return null;
            };
        }
        return ui.createMap(names, attack);
    }

}
