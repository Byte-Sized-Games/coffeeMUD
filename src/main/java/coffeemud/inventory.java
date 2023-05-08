package coffeemud;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class inventory {
    int gold = 0;
    boolean weaponEnchant = false;
    boolean armorEnchant = false;

    Map<String, Integer> smallItems = new HashMap<String, Integer>() {
        {
            put("hpotions", 0);
            put("mpotions", 0);
            put("ghpotions", 0);
            put("gmpotions", 0);
        }
    };
    Map<String, Boolean> melee = new HashMap<String, Boolean>() {
        {
            put("sword", false);
            put("dagger", false);
            put("axe", false);
            put("mace", false);
            put("hammer", false);
            put("greatsword", false);
        }
    };
    Map<String, Boolean> ranged = new HashMap<String, Boolean>() {
        {
            put("shortBow", false);
            put("longBow", false);
            put("lightCrossbow", false);
            put("heavyCrossbow", false);
        }
    };
    Map<String, Boolean> armor = new HashMap<String, Boolean>() {
        {
            put("light", false);
            put("medium", false);
            put("heavy", false);
        }
    };

    public inventory() throws IOException {
        inventory inv = inventory.getInventory();
        this.setWeapons(inv.melee, inv.ranged, inv.weaponEnchant);
        this.setArmor(inv.armor, inv.armorEnchant);
        this.setItems(inv.smallItems, inv.gold);
    }

    public inventory(inventory inv) {
        this.setWeapons(inv.melee, inv.ranged, inv.weaponEnchant);
        this.setArmor(inv.armor, inv.armorEnchant);
        this.setItems(inv.smallItems, inv.gold);
    }

    public void setWeapons(Map<String, Boolean> mle, Map<String, Boolean> rng, Boolean enc) {
        this.melee = mle;
        this.ranged = rng;
        this.weaponEnchant = enc;
    }

    public void setArmor(Map<String, Boolean> arm, Boolean enc) {
        this.armor = arm;
        this.armorEnchant = enc;
    }

    public void setItems(Map<String, Integer> sm, int g) {
        this.smallItems = sm;
        this.gold = g;
    }

    public static inventory getInventory() throws IOException {
        ObjectMapper map = new ObjectMapper(new YAMLFactory());
        map.findAndRegisterModules();
        inventory inv = map.readValue(new File("src/main/resources"), inventory.class);
        return inv;
    }

}