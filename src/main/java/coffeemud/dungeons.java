package coffeemud;

import java.util.Random;

public class dungeons {
    public class roomTraps extends spell {

    }
    public static class room {
        public String name;
        entities[] monsters; // Monsters in room
        roomTraps[] traps; // Any potential traps
        int gold; // Gold gained for beating room
        public void describe() {

        }
    }
    
    room[][] dungeonRooms;
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
}

