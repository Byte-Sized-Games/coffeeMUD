package coffeemud;

public class dungeons {
    public class roomTraps extends spell {

    }
    public class room {
        entities[] monsters; // Monsters in room
        roomTraps[] traps; // Any potential traps
        int gold; // Gold gained for beating room
    }
    
    room[][] dungeonRooms;
    room currentRoom;
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

