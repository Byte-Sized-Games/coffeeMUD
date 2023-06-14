package coffeemud;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.HashMap;
import java.util.TreeMap;

public class battle {
    player isekai;
    ArrayList<entities> monsters;
    int round;
    int playerInitiative;
    int monsterInitiative;
    uiAlt.prompt battlePrompt = new uiAlt.prompt(colours.red + "[BATTLE! - ",
            colours.yellow + "Round: " + colours.reset + this.round, colours.red + "]");;

    public battle(ArrayList<entities> m, int pInitiative, int mInitiative) {
        this.init(m, pInitiative, mInitiative);
    }

    public void init(ArrayList<entities> m, int pI, int mI) {
        this.monsters = m;
        this.round = 0;
        this.playerInitiative = pI;
        this.monsterInitiative = mI;
    }

    public void turn(String command) {
        ui.stage.currentMessage = battleMessage();
        checkEffects();
        if (playerInitiative >= monsterInitiative) {
            playerTurn(command);
            for (entities i : monsters) {
                monsterTurn(i);
            }
        } else {
            for (entities i : monsters) {
                monsterTurn(i);
            }
            playerTurn(command);
        }
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
            for (char x : i.effects) {
                switch (x) {
                    case 'x':
                        i.health = -2;
                }
            }
            if (i.health <= 0) {
                monsters.remove(i);
            }
        }
    }

    public void playerTurn(String bugCommand) {
        String command = bugCommand.substring(0, bugCommand.indexOf(" ") + 1);
        String target = bugCommand.substring(bugCommand.indexOf(" "));
        switch (command) {
            case "ATTACK":
            monsters.get(getMonster(target)).health -= isekai.attack();
            break;
            case "CAST":
                castSpell(target);
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
    }

    public void monsterTurn(entities monster) {
        if (monster.health <= (monster.maxHealth / 4)) {
            monster.health += monster.heal;
        } else {
            monster.attack(isekai);
        }
    }

    public void castSpell(String target, entities player) {
        spell targetSpell = getSpell(target, isekai.characterClass);
        battlePrompt.print("Select your targets: ");
        String[] getTargets = battlePrompt.read().split(" ");
        ArrayList<entities> targets = new ArrayList<entities>();
        for (int i = 0; i < getTargets.length; i++) {
            targets.set(i, monsters.get(getEntities(getTargets[i])));
        }
        targetSpell.cast(targets);
    }

    public void castSpell(String target) {
        spell targetSpell = spellbook.wizard.spells[0];
        battlePrompt.print("Select your targets: ");
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
                    battlePrompt.print("You can't cast spells silly! You're the sneaky one");
                    return null;
                case 'f':
                    battlePrompt.print("You can't cast spells silly! You hit things with a sword");
                    return null;
                default:
                    battlePrompt.print("No spell specified, try again");
            }
        }
    }
    public String battleMessage() {
        StringBuilder message = new StringBuilder("There are " + monsters.size() + " monsters. ");
        for (entities i : monsters) {
            message.append("There is a " + i.name + ". It currently has " + i.health + " health remaining");
        }

        return message.toString();
    }
    public HashMap<String, Callable<Void>> battleMenu() {
        logger.error("In progress. Expect Bugs");
        HashMap<String, Callable<Void>> menu = new HashMap<>();
        menu.put("Attack", () -> {
            playerTurn("ATTACK");
            return null;
        });
        menu.put("Defend", () -> {
            
            return null;
        });
        menu.put("Cast", () -> {
            return null;
        });

        return menu;
    }

}
