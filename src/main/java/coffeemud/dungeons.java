package coffeemud;

import java.util.Random;

public class dungeons {
    public static class room {
        public static String name;
        public static boolean battle;
        public static boolean empty;
        entities[] monsters; // Monsters in room
        int gold; // Gold gained for beating room

        public room(String n, boolean b, boolean e,entities[] m) {
            name = n;
            battle = b;
            empty = e;
            monsters = m;
            gold = 10;
        }
        public void describe() {

        }

    }
    
    public static room[][] dungeonRooms = new room[20][20];
    public static room currentRoom;
    int x;
    int y;
    public void moveRooms(char dir) {
        switch(dir) {
            case 'n':
                currentRoom = dungeonRooms[x][(y+1)];
            case 's':
                currentRoom = dungeonRooms[x][(y-1)];
            case 'e':
                currentRoom = dungeonRooms[x+1][y];
            case 'w':
                currentRoom = dungeonRooms[x-1][y];
        }
    }
    public void genDungeon() {
        for (int i = 0; i < 20; i++) {
            for (int y = 0; y < 20; y++) {
//                dungeonRooms[i][y] = new room();
            }
        }
    }
}

