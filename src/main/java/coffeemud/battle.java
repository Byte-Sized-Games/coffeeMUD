package coffeemud;

import java.util.ArrayList;

public class battle {
    ArrayList<entities> players;
    ArrayList<entities> monsters;
    int round;
    int playerInitiative;
    int monsterInitiative;
    uiAlt.prompt battlePrompt;

    public battle(ArrayList<entities> p, ArrayList<entities> m, int pInitiative, int mInitiative) {
        this.init(p, m, pInitiative, mInitiative);
    }

    public void init(ArrayList<entities> p, ArrayList<entities> m, int pI, int mI) {
        this.players = p;
        this.monsters = m;
        this.round = 0;
        this.playerInitiative = pI;
        this.monsterInitiative = mI;
    }

    public void turn() {
        if (playerInitiative >= monsterInitiative) {
            for (entities i : players) {
                playerTurn(i);
            }
            for (entities i : monsters) {
                monsterTurn(i);
            }
        } else {
            for (entities i : monsters) {
                monsterTurn(i);
            }
            for (entities i : players) {
                playerTurn(i);
            }
        }
    }

    public void playerTurn(entities player) {
        String input = battlePrompt.read();
        input = input.toUpperCase();
        String command = input.substring(0, input.indexOf(" ") + 1);
        String target = input.substring(input.indexOf(" "));
        switch (command) {
            case "ATTACK":
                player.attack(monsters.get(getMonster(target)));
            case "CAST":
                castSpell(target, player);
            case "DEFEND":
                players.get(getPlayer(target)).armour += 2;
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
            monster.attack(players.get((int) (Math.random() * (players.size() - 1)) + 1));
        }
    }

    public void castSpell(String target, entities player) {
        spell targetSpell = getSpell(target, player.characterClass);
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

    public int getPlayer(String target) {
        int i = 0;
        for (entities x : players) {
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
        for (entities x : players) {
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
