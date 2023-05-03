package coffeemud;

public class player {
    // General Stats
    int level;
    char playerClass;
    String name;
    int hp;
    int ac;
    // Ability Scores
    int strength;
    int intelligence;
    int dexterity;
    int wisdom;

    public player() {
        level = 0;
        playerClass = 'n';
        name = null;
        hp = 0;
        ac = 0;
        strength = 0;
        intelligence = 0;
        dexterity = 0;
        wisdom = 0;
    }
    public player(int l, char p, String n, int h, int a, int s, int d, int i, int w) {
        this.set(l, p, n, h, a, s, d, i, w);
    }
    public void set(int l, char p, String n, int h, int a, int s, int d, int i, int w) {
        this.level = l;
        this.playerClass = p;
        this.name = n;
        this.strength = s;
        this.dexterity = d;
        this.intelligence = i;
        this.wisdom = w;
        this.hp = this.setHP(h);
        this.ac = this.setAC(a);
    }
    public int setHP(int h) {
        return (this.wisdom > this.strength) ? this.wisdom + h : this.strength + h;
    }
    public int setAC(int a) {
        return (this.dexterity > this.intelligence) ? this.dexterity + a : this.intelligence + a;
    }

    /* Class Functions */
    public static player createCharacter() {
        player newCharacter = new player();

        return newCharacter;
    }
}
