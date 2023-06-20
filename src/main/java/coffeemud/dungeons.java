package coffeemud;

import java.util.Random;

public class dungeons {
    public class roomTraps {
        // all traps are a math problem that has to be solved
        String description; // so the problem can be identified
        String problem; // the problem to solve
        String answer; // the answer to the problem
        int damage; // the damage done if the user answers wrong

        // list of all the problems, the fields are in order
        /*
         * Description is a letter and 2 numbers, first uppercase letter representing
         * area of math,
         * number representing difficulty (1-4),
         * second lowercase letter if there are multiple questions with the same area
         * and difficulty
         * 
         * Areas of math: (A)lgebra, g(R)aphing/geometry, (C)alculations/arithmatic
         * 
         */
        public static String[][] chooseProblem = {
                { "C1a", "Calculate 40+3", "43" },
                { "C1b", "Calculate 9/3", "3" },
                { "C1c", "Calculate 2.5 - 1.5", "1" },
                { "C1d", "What is the smallest prime integer greater than 5", "7" },
                { "C1e", "How many minutes in 0.25 hours?", "15" },
                { "C2a", "Calculate 18 + 9", "27" },
                { "C2b", "Calculate 25 - (4*3)", "13" },
                { "C2c", "Calculate 1/3 + 1 and 1/3. Answer as a decimal to the nearest tenth", "1.7" },
                { "C2d", "Whats the largest prime integer less than 36", "31" },
                { "C3a", "Find the cube root of 125", "5" },
                { "C3b", "Calculate 1/4 + 1/6. Answer as a simplified fraction in the form x/y with natural numbers",
                        "5/12" },
                { "C3c", "What the largest number divisible by 3 that is less than 98", "96" },
                { "C3d", "Calculate 2853 - 1942", "911" },
                { "C4a", "Calculate the absolute value of ( 1234 - 5678 )", "4444" },
                { "C4b", "What is the smallest perfect cube (natural number) with a 9 digit", "729" },
                { "C4c", "How many minutes in 0.7 hours?", "42" },
                { "C4d", "Find the smallest number that when squared equals a number between 130 and 150", "-12" },

                { "A1a", "x + 2 = 2. What is the value of x", "0" },
                { "A1b", "2x - 4 = 0. What is the value of x", "2" },
                { "A1c", "2x - 4 = 0. What is the value of x", "2" },
                { "A2a", "4x + 6 = 0. What is the the value of x as a decimal to the nearest tenth", "-1.5" },
                { "A2b", "5x = 4. What is the value of x as a simplified fraction in the form a/b with natural numbers",
                        "4/5" },
                { "A2c", "What number when doubled is 4 and when squared is 4", "2" },
                { "A2d", "What number when tripled is 9 and when squared is 9", "3" },
                { "A2e", "4x = 5 and 6y = 7 if both are natural numbers which is the greater variable", "x" },
                { "A3a", "x^2 = 2x, find x", "2" },
                { "A3b", "x^1/2 = (x^0)*4, find x", "16" },
                { "A3c", "Calculate 1/(1/(4/6). Answer as a decimal to the nearest tenth", "2/3" },
                { "A3c", "Find a if a^x = 2(x^0) = 3a", "3" },
                { "A3d", "x^2 -4x - 45 = 0. Find the largest possible value of x", "9" },
                { "A4a", "The answers to a quadratic equation are (x-7)(x+7), what was the value greater than 1 in the original equation",
                        "49" },
                { "A4b", "x^2x = -1/16. Find the value of x", "-2" },

                { "R1a", "What is the sum of interior angles of a triange in degrees", "180" },
                { "R1b", "The area of a square is 4. What's its perimeter", "8" },
                { "R1c", "What is the measure in degrees of one angle in an equilateral triangle", "60" },
                { "R2a", "A right angle is divided in half, whats the angle in degrees", "45" },
                { "R2b", "A circle has a diameter of 2/pi, what's it's circumference", "2" },
                { "R2c", "An exterior angle of a polygon measures 270 degrees, what's its interor angle", "90" },
                { "R2d", "What is the minimum y value of the graph of y = (x+3)ˆ2 + 1", "1" },
                { "R2e", "A regular hexagon's side lengths are 4, what's 1/6 of its area", "8" },
                { "R3a", "What is the acute angle in degrees formed by the line y=x intersecting the y-axis", "45" },
                { "R3b", "What shape does the graph y = xˆ2 + yˆ2 make. type your answer in lowercase letters",
                        "circle" },
                { "R3c", "What's the area of the largest triangle that could be contained by a square of side length 4",
                        "8" },
                { "R3d", "Does y=0 intercept y = 2|x+2| -1 \"right\" or \"left\" of the origin", "left" },
                { "R3e", "A pie has a circumference of 12, equal slices at a 60 degree angle are made from the center, what's the length of the slices' crust",
                        "2" },
                { "R4a", "3 supplimentary angles have a ratio of 3:2:4. Find the smallest angle in degrees", "40" },
                { "R4b", "Whats the length of the largest line that can be contained in a circle with a 60 degree arc length of pi/2",
                        "3" },
                { "R4c", "A rectangular area will be enclosed by a 40m fence, if the area is maximized what is it in meters squared",
                        "100" },

        };

        public roomTraps() {
            int probNumb = randRange(0, chooseProblem.length - 1);
            problem = chooseProblem[probNumb][1];
            answer = chooseProblem[probNumb][2];
            description = chooseProblem[probNumb][0];
            damage = randRange(1, 20);

        }

    }

    public class room {
        boolean complete; // If the room is complete
        entity[] monsters; // Monsters in room
        roomTraps[] traps; // Any potential traps
        int gold; // Gold gained for beating room
        String description; // Short description of the room

        public room() throws Exception {
            gold = randRange(5, 50);
            complete = false;
            byte cnt = (byte) (Math.round(Math.random() * 3) + 1);
            if(Math.round(Math.random()) == 0) {
                traps = new roomTraps[]{};
                monsters = new entity[cnt];
                for (int i = 0; i < cnt; i++) {
                    switch (randRange(0, 4)) {
                        case 0 -> monsters[i] = monsterbook.createBat();
                        case 1 -> monsters[i] = monsterbook.createGoblin();
                        case 2 -> monsters[i] = monsterbook.createSkeleton();
                        case 3 -> monsters[i] = monsterbook.createTroll();
                        case 4 -> monsters[i] = monsterbook.createWitch();
                    }
                }
            } else {
                monsters = new entity[]{};
                traps = new roomTraps[cnt];
                for (int i = 0; i < cnt; i++) {
                    traps[i] = new roomTraps();
                }
            }
//            int maxTraps = randRange(0, 2);
//            if (maxEntities == 0 && maxTraps > 0) {
//                traps = new roomTraps[maxTraps];
//                for (int i = 0; i < maxTraps; i++) {
//                    traps[i] = new roomTraps();
//                }
//                gold = randRange(5, 50);
//                complete = false;
//            } else if (maxTraps == 0 && maxEntities > 0) {
//                monsters = new entity[maxEntities];
//
//                gold = randRange(5, 50);
//                complete = false;
//            } else {
//                gold = randRange(5, 50);
//                complete = true;
//            }
            description = setDescription(monsters.length, traps.length);

        }

        // Gives the player gold when the room is complete (no traps or enemies left)
        public int giveGold() {
            return gold;
        }

        public String setDescription(int monsters, int traps) {
            String[] adjective = { "Fancy", "Dusty", "Old", "Well Kept", "Grimey", "Odd", "Suspicious", "Magical",
                    "Overgrown", "Warm", "Cold", "Freezing", "Burning", "Dark", "Bright" };
            StringBuilder desc = new StringBuilder("The room is " + adjective[randRange(0, adjective.length - 1)]);
            if (monsters == 0 && traps > 0) {
                desc.append(", there appear to be " + traps + " traps that you'll need to solve to get through.");
            } else if (traps == 0 && monsters > 0) {
                desc.append(", there are " + monsters + " monsters that you'll need to fight to proceed.");
            } else {
                desc.append(", and it seems to be empty. No traps or monsters in sight.");
            }
            desc.append(" There is " + gold + " pieces of gold to be looted from here");
            return desc.toString();
        }

    }

    room[][] dungeonRooms = new room[20][20];
    room currentRoom;
    int x;
    int y;

    public void moveRooms(char dir) {
        switch (dir) {
            case 'n':
                if (y == 19) {
                    y = 18;
                    logger.error("You have reached a wall. You cannot go any further");
                }
                y = y + 1;
                break;
            case 's':
                if (y == 0) {
                    y = 1;
                    logger.error("You have reached a wall. You cannot go any further");
                }
                y = y - 1;
                break;
            case 'e':
                if (x == 19) {
                    x = 18;
                    logger.error("You have reached a wall. You cannot go any further");
                }
                x = x + 1;
                break;
            case 'w':
                if (x == 0) {
                    x = 1;
                    logger.error("You have reached a wall. You cannot go any further");
                }
                x = x - 1;
                break;
            default:
                logger.error("No input given");
        }
        currentRoom = dungeonRooms[x][y];
        logger.info("X: " + x + ", Y: " + y + " [Desc]\t" + currentRoom.description + ". Complete = " + currentRoom.complete);
    }

    public void genDungeon() throws Exception {
        for (int i = 0; i < 20; i++) {
            for (int a = 0; a < 20; a++) {
                dungeonRooms[i][a] = new room();
                logger.debug("x:" + i + ", y:" + a + " | " + dungeonRooms[i][a].description + ". Complete = " + dungeonRooms[i][a].complete);
            }
        }
    }

    public dungeons(int a, int b) throws Exception {
        genDungeon();
        x = a;
        y = b;
        currentRoom = dungeonRooms[x][y];
    }

    public int randRange(int low, int high) {
        Random rand = new Random();
        return rand.nextInt((high - low) + 1) + low;
    }
}