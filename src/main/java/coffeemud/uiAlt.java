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
        ArrayList<entities> monsters;
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
