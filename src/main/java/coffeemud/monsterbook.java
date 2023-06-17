package coffeemud;

import java.util.concurrent.Callable;

public class monsterbook {

    static Callable[][] names = {
            {
                    () -> {
                        return switch((int) Math.floor(Math.random() * 2)) {
                            case 0 -> "Brandon ";
                            case 1 -> "Michael ";
                            case 2 -> "Phoenix ";
                            default ->
//                                    throw new IllegalStateException("Unexpected value: " + (int) Math.floor(Math.random() * 2));
                                    "Brandon";
                        };
                    },
                    () -> {
                        return "Aardvark ";
                    },
                    () -> {
                        return "Aardwolf ";
                    },
                    () -> {
                        return "Aboleth " ;
                    },
                    () -> {
                        return "Achaierai ";
                    },
                    () -> {
                        return "Allip ";
                    },
                    () -> {
                        return "Angel ";
                    },
                    () -> {
                        return "Ankheg ";
                    },
                    () -> {
                        return "Aranea ";
                    },
                    () -> {
                        return "Archon ";
                    },
                    () -> {
                        return "Arrowhawk ";
                    },
                    () -> {
                        return "Assassin Vine ";
                    },
                    () -> {
                        return "Athach ";
                    },
                    () -> {
                        return "Avoral ";
                    },
                    () -> {
                        return "Azer ";
                    },
                    () -> {
                        return "Barghest ";
                    },
                    () -> {
                        return "Basilisk ";
                    },
                    () -> {
                        return "Behir ";
                    },
                    () -> {
                        return "Beholder ";
                    },
                    () -> {
                        return "Blink Dog ";
                    },
                    () -> {
                        return "Bodak ";
                    },
                    () -> {
                        return "Bugbear ";
                    },
                    () -> {
                        return "Bulette ";
                    },
                    () -> {
                        return "Centaur ";
                    },
                    () -> {
                        return "Chimera ";
                    },
                    () -> {
                        return "Choker ";
                    },
                    () -> {
                        return "Chuul ";
                    },
                    () -> {
                        return "Cloaker ";
                    },
                    () -> {
                        return "Cockatric e ";
                    },
                    () -> {
                        return "Couatl ";
                    },
                    () -> {
                        return "Darkmantle ";
                    },
                    () -> {
                        return "Delver ";
                    },
                    () -> {
                        return "Demon ";
                    },
                    () -> {
                        return "Devil ";
                    },
                    () -> {
                        return "Dinosaur ";
                    },
                    () -> {
                        return "Displacer Beast ";
                    },
                    () -> {
                        return "Doppelganger ";
                    },
                    () -> {
                        return "Dragon ";
                    },
                    () -> {
                        return "Dragon Turtle ";
                    },
                    () -> {
                        return "Drider ";
                    },
                    () -> {
                        return "Dryad ";
                    },
                    () -> {
                        return "Duergar ";
                    },
                    () -> {
                        String name = "";
                        for(byte i = (byte) Math.floor(Math.random() * 21); i > 0; i--) {
                            name += (char) (Math.floor(Math.random() * 450) + 1);
                        }
                        return name;
                    }
            },
            {
                    () -> {
                        return "Gary " + genName((byte) 5);
                    },
                    () -> {
                        return "Gobbling " + genName((byte) 0);
                    },
                    () -> {
                        return genName((byte) 1) + " the Goblin";
                    },
                    () -> {
                        return "Goof";
                    },
                    () -> {
                        return "Goblin";
                    }
            },
            {
                    () -> {
                        return "Skeleton " + genName((byte) 0) + "the " + genName((byte) 6);
                    },
                    () -> {
                        return "Jams 1";
                    },
                    () -> {
                        return "Jams 2";
                    },
                    () -> {
                        return "Jams 3";
                    },
                    () -> {
                        return "Jams 4";
                    },
                    () -> {
                        return "Jams 5";
                    },
                    () -> {
                        return "Jams 6";
                    },
                    () -> {
                        return "Jams 7";
                    },
                    () -> {
                        return "Jams 8";
                    },
                    () -> {
                        return "Jams 9";
                    },
                    () -> {
                        return "Jams 10";
                    },
                    () -> {
                        return "Jams 11";
                    },
                    () -> {
                        return "Jams 12";
                    },
                    () -> {
                        return "Jams 13";
                    },
                    () -> {
                        return "Jams 14";
                    },
                    () -> {
                        return "Jams 15";
                    },
                    () -> {
                        return "Jams 16";
                    },
                    () -> {
                        return "Jams 17";
                    },
                    () -> {
                        return "Jams 18";
                    },
                    () -> {
                        return "Jams 19";
                    },
                    () -> {
                        return "Jams 20";
                    },
                    () -> {
                        return "Jams 21";
                    },
                    () -> {
                        return "Jams 22";
                    },
                    () -> {
                        return "Jams 23";
                    },
                    () -> {
                        return "Jams 24";
                    },
                    () -> {
                        return "Jams 25";
                    },
                    () -> {
                        return "Jams 26";
                    },
                    () -> {
                        return "Jams 27";
                    },
                    () -> {
                        return "Jams 28";
                    },
                    () -> {
                        return "Jams 29";
                    },
                    () -> {
                        return "Jams 30";
                    },
                    () -> {
                        return "Jams 31";
                    },
                    () -> {
                        return "Jams 32";
                    },
                    () -> {
                        return "Jams 33";
                    },
                    () -> {
                        return "Jams 34";
                    },
                    () -> {
                        return "Jams 35";
                    },
                    () -> {
                        return "Jams 36";
                    },
                    () -> {
                        return "Margaret Thatcher";
                    },
                    () -> {
                        return genName((byte) 1) + " the last.";
                    }
            },
            {
                    () -> {
                        if (Math.floor(Math.random() * 10) == 0) {
                            return "Greg";
                        } else {
                            return (Math.floor(Math.random()) == 0 ? "Dark " : "") + genName((byte) 0) + "Witchington";
                        }
                    }
            },
            {
                //troll names
                    () -> {
                        return "Jerm";
                    },
                    () -> {
                        return "James " + genName((byte) 5);
                    },
                    () -> {
                        return "Jams " + genName((byte) 5);
                    },
                    () -> {
                        return "Jamantha";
                    },
                    () -> {
                        return "Jerry";
                    }
            },
            {
                    () -> {
                        return "Ward";
                    },
                    () -> {
                        return "Bartholomew";
                    },
                    () -> {
                        byte iteration = (byte) (Math.floor(Math.random() * 10) + 1);
                        return "The " + iteration + switch (iteration) {
                            case 1 -> "st";
                            case 2 -> "nd";
                            case 3 -> "rd";
                            default -> "th";
                        };
                    }
            },
            {
                    () -> {
                        return "Old";
                    },
                    () -> {
                        byte iteration = (byte) (Math.floor(Math.random() * 10) + 1);
                        return iteration + switch (iteration) {
                            case 1 -> "st";
                            case 2 -> "nd";
                            case 3 -> "rd";
                            default -> "th";
                        };
                    }

            }
    };
    static String genName(byte monsterType) {
        try {
            return (String) names[monsterType][(int) (Math.random() * (names[monsterType].length))].call();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
    public static entities createTroll() {
        return new entities(genName((byte) 4),100,10,40,10);
    }
    public static entities createSkeleton() {
        return new entities(genName((byte) 2),50,5,35,5);
    }
    public static entities createWitch() {
        return new entities(genName((byte) 3),75,20,50,0);
    }
    public static entities createGoblin() {
        return new entities(genName((byte) 1),25,1,20,2);
    }
    public static entities createBat()  {
        return new entities("Bat",30,1,10,0);
    }
    /*
    tatic entities goblin = new entities("Goblin", 25, 1, 20, 2);

        static entities bat = new entities("Bat", 30, 1, 10, 0);

        static entities witch = new entities("Witch", 75, 20, 50, 0);

        static entities skeleton = new entities("Skeleton", 50, 5, 35, 5);

        static entities troll = new entities("Troll", 100, 10, 40, 10);
     */
}
