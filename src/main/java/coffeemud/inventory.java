package coffeemud;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class inventory {
    int gold;
    boolean weaponEnchant;
    boolean armorEnchant;
    int dmgMin;
    int dmgMax;
    int armor;
    String weapon;
    String armorType;
    // Potions
    int hpotions;
    int mpotions;
    int ghpotions;
    int gmpotions;
    // Spell Scrolls
    int dragonScroll;
    int golemScroll;
    int fireScroll;
    int iceScroll;
    int elecScroll;
    int plantScroll;
    // Spells
    Map<String, Boolean> spells = new HashMap<String, Boolean>() {
        {
            put("Magic Missle", false);
            put("Shield", false);
            put("Fire Bolt", false);
            put("Spark", false);
            put("Ice Bolt", false);
            put("Thorns", false);
            put("Confuse", false);
            put("Psionic Blast", false);
            put("Sleep", false);
            put("FireBall", false);
            put("Ice Blast", false);
            put("Lightning Bolt", false);
            put("Roots", false);
            put("Barrier", false);
            put("Mage Armor", false);
            put("Summon Elemental", false);
            put("Cure Light Wounds", false);
            put("Faith Shield", false);
            put("Turn Undead", false);
            put("Bless", false);
            put("Confuse", false);
            put("Rage", false);
            put("Summon Lesser Spirit", false);
            put("Lesser Healing Aura", false);
            put("Inflict Light Wounds", false);
            put("Destroy Fiend", false);
            put("Cure Greater Wounds", false);
            put("Banish", false);
            put("Divine Intervention", false);
            put("Summon Greater Spirit", false);
            put("Greater Healing Aura", false);
            put("Inflict Greater Wounds", false);
        }
    };

    public inventory() throws IOException {
        inventory inv = inventory.getInventory();
        this.setItems(inv);
        this.setArms(inv);
    }

    public inventory(inventory inv) {
        this.setItems(inv);
        this.setArms(inv);
    }

    // Setters
    public void setItems(inventory inv) {
        this.gold = inv.gold;
        this.setPotions(inv);
        this.setScrolls(inv);
    }

    public void setPotions(inventory inv) {
        this.hpotions = inv.hpotions;
        this.hpotions = inv.mpotions;
        this.ghpotions = inv.ghpotions;
        this.gmpotions = inv.gmpotions;
    }

    public void setArms(inventory inv) {
        this.setArmor(inv);
        this.setWeapon(inv);
    }

    public void setArmor(inventory inv) {
        this.armor = inv.armor;
        this.armorEnchant = inv.armorEnchant;
        this.armorType = inv.armorType;
    }

    public void setWeapon(inventory inv) {
        this.dmgMax = inv.dmgMax;
        this.dmgMin = inv.dmgMin;
        this.weaponEnchant = inv.weaponEnchant;
        this.weapon = inv.weapon;
    }

    public void setScrolls(inventory inv) {
        this.fireScroll = inv.fireScroll;
        this.iceScroll = inv.iceScroll;
        this.elecScroll = inv.elecScroll;
        this.plantScroll = inv.plantScroll;
        this.golemScroll = inv.golemScroll;
        this.dragonScroll = inv.dragonScroll;
    }

    // Getters
    public void saveInventory() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource("src/main/resources/inventory.yaml").getFile());
        ObjectMapper map = new ObjectMapper(new YAMLFactory());
        map.writeValue(file, this);
    }

    public static inventory getInventory() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource("src/main/resources/inventory.yaml").getFile());
        ObjectMapper map = new ObjectMapper(new YAMLFactory());
        return map.readValue(file, inventory.class);
    }

}