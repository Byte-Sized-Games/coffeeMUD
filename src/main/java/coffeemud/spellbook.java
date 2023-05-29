package coffeemud;

public class spellbook {
    public static class wizard {
        static final spell[] spells = new spell[] {
            new spell('d', 4, 'n', 1, "Magic Missle", "A minor damage spell"),
            new spell('b', 0, 'p', 1, "Shield", "Temporary protection from one attack"),
            new spell('d', 6, 'f', 1, "Fire Bolt", "A minor fire spell. Good against creatures of ice"),
            new spell('d', 6, 'e', 1, "Spark", "A minor electricity spell. Good against creatures of fire"),
            new spell('d', 6, 'i', 1, "Ice Bolt", "A minor ice spell. Good against creatures of earth"),
            new spell('d', 6, 'P', 1, "Thorns", "A minor earth spell. Good against creatures of electricity"),
            new spell('x', 0, 'a', 2, "Confuse", "Confuse an enemy, making it attack itself and its friends"),
            new spell('d', 10, 'n', 2, "Psionic Blast", "A powerful psionic blast of magic energy"),
            new spell('x', 3, 'b', 2, "Sleep", "Send enemies to sleep, leaving them vulnerable"),
            new spell('d', 15, 'n', 3, "Fireball", "An intermediate fire spell. Great against creatures of ice"),
            new spell('d', 15, 'e', 3, "Lightning Bolt", "A intermediate electricity spell. Great against creatures of fire"),
            new spell('d', 15, 'i', 3, "Ice Blast", "A intermediate ice spell. Great against creatures of earth"),
            new spell('d', 15, 'P', 3, "Roots", "A intermediate earth spell. Great against creatures of electricity"),
            new spell('b', 5, 'B', 3, "Barrier", "A powerful spell of protection"),
            new spell('b', 20, 'B', 4, "Mage Armor", ""),
            new spell('b', 0, 's', 4, "Summon elemental", "Summon a powerful elemental to fight by your side")
        };
    }
    public static class cleric {
        static final spell[] spells = new spell[] {
            new spell('h', 4, 'n', 1, "Cure light wounds", "Heal minor wounds on yourself and allies"),
            new spell('b', 2, 'p', 1, "Faith Shield", "Protect your allies from harm"),
            new spell('x', 4, 'b', 1, "Turn Undead", "Destroy or turn undead creatures"),
            new spell('b', 5, 'B', 1, "Bless", "Allow allies to better resist attacks"),
            new spell('x', 0, 'a', 1, "Confuse", "Confuse an enemy, making it attack itself and its friends"),
            new spell('b', 0, 'r', 2, "Rage", "Increase an allies attack temporarily"),
            new spell('b', 0, 's', 2, "Summon Lesser Spirit", "Summon a minor spirit to fight by your side"),
            new spell('h', 2, 'n', 2, "Lesser Healing Aura", "Heal all allies"),
        };
    }
    public static class scroll {
        static final spell[] spells = new spell[] {
            new spell('d', 35, 'f', 4, "Fire Storm", "A powerful fire spell. Destructive against creatures of ice"),
            new spell('d', 35, 'e', 4, "Thunder Storm", "A powerful electricity spell. Destructive against creatures of fire"),
            new spell('d', 35, 'i', 4, "Ice Storm", "A powerful ice spell. Destructive against creatures of earth"),
            new spell('d', 35, 'P', 4, "Vines", "A powerful earth spell. Destructive against creatures of electricity"),
        };
    }
}
