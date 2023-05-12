package coffeemud;

public class spellbook {
    public class wizard {
        spell[] spells = new spell[] {
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
    public class cleric {
        spell[] spells = new spell[] {
            new spell(),
        };
    }
    public class scroll {
        spell[] spells = new spell[] {
            new spell('d', 35, 'f', 4, "Fire Storm", "A powerful fire spell. Destructive againt creatures of ice"),
            new spell('d', 35, 'e', 4, "Thunder Storm", "A powerful electricity spell. Destructive againt creatures of fire"),
            new spell('d', 35, 'i', 4, "Ice Storm", "A powerful ice spell. Destructive againt creatures of earth"),
            new spell('d', 35, 'P', 4, "Vines", "A powerful earth spell. Destructive againt creatures of electricity"),
        };
    }
}
