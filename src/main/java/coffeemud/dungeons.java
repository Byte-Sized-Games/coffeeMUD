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
}
