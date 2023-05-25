package coffeemud;

import java.util.ArrayList;
import java.util.Scanner;

public class uiAlt {
    // Used to display items to players
    public class display {
        prompt gamePrompt;

        public void clear() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        public void show(battle m) {

        }

        public void show(String m) {
            gamePrompt.print(m);
        }
    }

    // Menu, shows a list
    public class menu {
        // Numbered lists, either for Item values or for a checklist
        public String numberedList(String[] x) {
            StringBuilder numBld = new StringBuilder();
            for (int i = 0; i < x.length; i++) {
                numBld.append((i + 1) + x[i] + "\n");
            }
            return numBld.toString();
        }

        public String numberedList(String[] x, int[] y) {
            StringBuilder numBld = new StringBuilder();
            for (int i = 0; i < x.length; i++) {
                numBld.append((i + 1) + ". " + x[i] + ": " + y[i] + "\n");
            }
            return numBld.toString();
        }

        // Unumbered list, for inventories and graphics
        public String unNumList(String[] x) {
            StringBuilder numBld = new StringBuilder();
            for (String i : x) {
                numBld.append("- " + i + "\n");
            }
            return numBld.toString();
        }

        public String unNumList(String[] x, int[] y) {
            StringBuilder numBld = new StringBuilder();
            for (int i = 0; i < x.length; i++) {
                numBld.append("- " + x[i] + ": " + y[i] + "\n");
            }
            return numBld.toString();
        }
    }

    // Used when fighting monsters
    public class battle {
        ArrayList<entities> players;
        ArrayList<entities> monsters;
        int round;
        int playerInitiative;
        int monsterInitiative;
        prompt battlePrompt;

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
                    castSpell();
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
        public int getMonster(String target) {
            int i = 0;
            for (entities x : monsters) {
                if (x.name.equals(target)) {
                    break;
                } else i++;
            }
            return i;
        }
        public int getPlayer(String target) {
            int i = 0;
            for (entities x : players) {
                if (x.name.equals(target)) {
                    break;
                } else i++;
            }
            return i;
        }
    }

    // A prompt for players to input text into
    public class prompt {
        String back;
        String mid;
        String front;
        Scanner scn = new Scanner(System.in);

        public prompt(String b, String m, String f) {
            this.set(b, m, f);
        }

        public void set(String b, String m, String f) {
            this.back = b;
            this.mid = m;
            this.front = f;
        }

        public void set(String x, int part) {
            switch (part) {
                case 1:
                    this.back = x;
                    break;
                case 2:
                    this.mid = x;
                    break;
                case 3:
                    this.front = x;
                    break;
                default:
                    break;
            }
        }

        public void display() {
            System.out.print(back + mid + front + " ");
        }

        public String read() {
            return scn.nextLine();
        }

        public void print(String text) {
            System.out.print(text);
            display();
        }
    }
}
