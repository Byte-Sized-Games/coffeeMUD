package coffeemud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.TreeMap;

import static coffeemud.game.*;

public class battle {
    public entity attacker;
    public static ArrayList<entity> monsters;
    int round;
    uiAlt.prompt battlePrompt = new uiAlt.prompt(colours.red + "[BATTLE! - ",
            colours.yellow + "Round: " + colours.reset + this.round, colours.red + "]");;

    public battle(ArrayList<entity> m) {
        this.init(m);
    }

    public void init(ArrayList<entity> m) {
        monsters = m;
        this.round = 0;
    }

    public boolean fight() throws IOException {
        update(battleMenu(), battleMessage());
        return true;
    }

    public void checkEffects() {
        player.tempHealth /= 4;
        for (char x : player.effects) {
            switch (x) {
                case 'x':
                    player.health -= 2;
            }
        }
        if (player.health <= 0) {
            player.die();
        }

        for (entity i : monsters) {
            i.tempHP = i.tempHP / 4;
            if (i.health <= 0) {
                monsters.remove(i);
            }
        }
    }

    public void playerTurn(String command) throws IOException {
        checkEffects();
        switch (command) {
            case "ATTACK":
                update(attackMenu(), battleMessage());
                break;
            case "CAST":
                logger.error("Not yet finished");
                update(spellMenu(), battleMessage());
                break;
            case "DEFEND":
                player.armour += 2;
                update(battleMenu(), battleMessage());
                break;
            case "", " ":
                logger.debug("No command chosen, please try again");
                break;
            default:
                logger.error("Command not recognized");
                break;
        }
        checkEffects();
    }

    public void monsterTurn() {
        logger.error("rawr");
        (attacker = monsters.get((int) Math.floor(Math.random() * monsters.size()))).attack();
    }

    public void castSpell(String target) {
        logger.debug("Select your targets: ");
        String[] getTargets = battlePrompt.read().split(" ");
        ArrayList<entity> targets = new ArrayList<entity>();
        for (int i = 0; i < getTargets.length; i++) {
            targets.set(i, monsters.get(getEntities(getTargets[i])));
        }
    }

    public int getMonster(String target) {
        int i = 0;
        for (entity x : monsters) {
            if (x.name.equals(target)) {
                break;
            } else
                i++;
        }
        return i;
    }

    public int getEntities(String target) {
        ArrayList<entity> battleEntities = new ArrayList<entity>();
        int i = 0;
        battleEntities.addAll(monsters);
        for (entity x : battleEntities) {
            if (x.name.equals(target)) {
                break;
            } else
                i++;
        }
        return i;
    }

    public String battleMessage() {
        StringBuilder message = new StringBuilder((player.health != player.maxHealth ? attacker.name + " " + attacker.verb + " you." : "") + "There are " + monsters.size() + " monsters. ");
        for (entity i : monsters) {
            message.append("There is a " + i.name + ". It currently has " + i.health + " health remaining. ");
        }
        message.append("You have " + player.health + " remaining");
        if(itemhd) {
            message = new StringBuilder("You received " + drop.name);
            itemhd = false;
        }
        return message.toString();
    }
    boolean itemhd = false;
    public TreeMap<String, Callable<Void>> battleMenu() {
        checkEffects();
        TreeMap<String, Callable<Void>> menu;

        if (monsters.size() == 0) {
            if(game.gameDungeon.currentRoom.monsters.length > 2) {
                byte rand = (byte) Math.round(Math.random() * 3);
                byte efficacy = (byte) ((byte) Math.round(Math.random() * 3));
                drop = new item(rand, item.genName(game.gameDungeon.currentRoom.monsters[0].name,rand,efficacy),efficacy);
                ui.stage.headsUp("You received " + drop.name);
                itemhd = true;
                menu = ui.createMap(new String[] { "Receive","Ignore" },
                        new Callable[] { () -> {
                            byte iterator = 0;
                            for(item slot : player.inventory) {
                                iterator++;
                                if(Objects.isNull(slot)) {
                                    player.inventory[iterator] = new item(drop.type,drop.name,drop.efficacy);
                                    slot = new item(drop.type,drop.name,drop.efficacy);
                                    drop.get();
                                    drop = null;
                                    update(mainMenu());
                                    logger.debug("item received" + player.inventory[iterator].name);
                                    return null;
                                }
                            }
                            game.itemDeleteMenu();
                            return null;
                        }, () -> {
                            update(mainMenu());
                            return null;
                        } });
            } else {
                String[] commands = { "Finish" };
                Callable[] callables = {
                        () -> {
                            update(mainMenu());
                            return null;
                        }
                };
                menu = ui.createMap(commands, callables);
            }
        } else {
            String[] commands = { "Attack", "Defend", "Cast", "Use item(doesn't take a turn)" };
            Callable[] callables = {
                    () -> {
                        playerTurn("ATTACK");

                        return null;
                    },
                    () -> {
                        playerTurn("DEFEND");
                        monsterTurn();
                        return null;
                    },
                    () -> {
                        playerTurn("CAST");
                        monsterTurn();
                        return null;
                    },
                    () -> {
                        game.itemUseMenu();
                        return null;
                    }
            };
            menu = ui.createMap(commands, callables);
        }

        return menu;
    }

    public TreeMap<String, Callable<Void>> attackMenu() {
        checkEffects();
        String[] names = new String[monsters.size() + 1];
        Callable[] attack = new Callable[monsters.size() + 1];
        for (int i = 0; i < monsters.size(); i++) {
            final int y = i;
            names[i] = monsters.get(i).name;
            attack[i] = () -> {
                checkEffects();
                monsters.get(y).health -= player.attack();
                ui.currentStage.draw((short) ui.terminal.getTerminalSize().getRows(), (short) ui.terminal.getTerminalSize().getColumns());
                checkEffects();
                monsterTurn();
                update(battleMenu(), battleMessage());
                return null;
            };
        }
        if (monsters.size() > 0) {
            names[monsters.size()] = "Back";
            attack[monsters.size()] = () -> {
                checkEffects();
                update(battleMenu(), battleMessage());
                return null;
            };
        } else {
            names[monsters.size()] = "Finish";
            attack[monsters.size()] = () -> {
                checkEffects();
                update(mainMenu(), battleMessage());
                return null;
            };
        }

        return ui.createMap(names, attack);
    }

    public void cast(spell i) throws IOException {
        player.mana -= i.cost;
        switch (i.type) {
            case 'd':
                update(targetMenu(i), battleMessage());
                break;
            case 'h':
                player.health += i.dmg;
                checkEffects();
                update(battleMenu(), battleMessage());
                break;
            case 'b':
                player.armour += i.dmg;
                checkEffects();
                update(battleMenu(), battleMessage());
                break;
        }
    }

    public TreeMap<String, Callable<Void>> targetMenu(spell x) {
        String[] names = new String[monsters.size()];
        Callable[] attack = new Callable[monsters.size()];
        for (int i = 0; i < monsters.size(); i++) {
            final int y = i;
            names[i] = monsters.get(i).name;
            attack[i] = () -> {
                checkEffects();
                monsters.get(y).health -= (x.dmg * player.level);
                checkEffects();
                update(battleMenu(), battleMessage());
                return null;
            };
        }
        return ui.createMap(names, attack);
    }

    public TreeMap<String, Callable<Void>> spellMenu() {
        String[] name = new String[player.spellList.size()];
        Callable[] castables = new Callable[player.spellList.size()];
        for (int i = 0; i < player.spellList.size(); i++) {
            final int y = i;
            name[i] = player.spellList.get(i).name;
            castables[i] = () -> {
                cast(player.spellList.get(y));
                return null;
            };
        }
        return ui.createMap(name, castables);
    }

}