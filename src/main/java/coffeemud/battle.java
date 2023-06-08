package coffeemud;

import java.util.ArrayList;

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

    public void turn() {
        checkEffects();
        if (playerInitiative >= monsterInitiative) {
            playerTurn();
            for (entities i : monsters) {
                monsterTurn(i);
            }
        } else {
            for (entities i : monsters) {
                monsterTurn(i);
            }
            playerTurn();

        }
    }

    public void turn(String command) {
        checkEffects();
        if (playerInitiative >= monsterInitiative) {
            playerTurn();
            for (entities i : monsters) {
                monsterTurn(i);
            }
        } else {
            for (entities i : monsters) {
                monsterTurn(i);
            }
            playerTurn();
        }
    }

    public void checkEffects() {
            isekai.tempHealth = isekai.tempHealth / 4;
            for (char x : isekai.effects) {
                switch (x) {
                    case 'x':
                        isekai.health = -2;
                }
            }
            if (isekai.health <= 0) {
                isekai.die();
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

    public void playerTurn() {
        String input = battlePrompt.read();
        input = input.toUpperCase();
        String command = input.substring(0, input.indexOf(" ") + 1);
        String target = input.substring(input.indexOf(" "));
        switch (command) {
            case "ATTACK":
            monsters.get(getMonster(target)).health -= isekai.attack();
            case "CAST":
                castSpell(target);
            case "DEFEND":
                isekai.armour += 2;
            case "":
                battlePrompt.print("No command chosen, please try again");
            default:
                battlePrompt.print("Command not recognized");
        }
    }

    public void playerTurn(entities player, String bugCommand) {
        String command = bugCommand.substring(0, bugCommand.indexOf(" ") + 1);
        String target = bugCommand.substring(bugCommand.indexOf(" "));
        switch (command) {
            case "ATTACK":
            monsters.get(getMonster(target)).health -= isekai.attack();
            case "CAST":
                castSpell(target);
            case "DEFEND":
                isekai.armour += 2;
            case "":
                battlePrompt.print("No command chosen, please try again");
            default:
                battlePrompt.print("Command not recognized");
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
}
