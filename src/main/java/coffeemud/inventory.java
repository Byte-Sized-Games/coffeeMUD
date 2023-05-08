package coffeemud;

import java.io.File;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class inventory {
    int gold = 0;
    boolean weaponEnchant = false;
    boolean armorEnchant = false;

    HashMap<String, Integer> smallItems = new HashMap<String, Integer>() {
        {
            put("hpotions", 0);
            put("mpotions", 0);
            put("ghpotions", 0);
            put("gmpotions", 0);
        }
    };
    HashMap<String, Boolean> melee = new HashMap<String, Boolean>() {
        {
            put("sword", false);
            put("dagger", false);
            put("axe", false);
            put("mace", false);
            put("hammer", false);
            put("greatsword", false);
        }
    };
    HashMap<String, Boolean> ranged = new HashMap<String, Boolean>() {
        {
            put("shortBow", false);
            put("longBow", false);
            put("lightCrossbow", false);
            put("heavyCrossbow", false);
        }
    };
    HashMap<String, Boolean> armor = new HashMap<String, Boolean>() {
        {
            put("light", false);
            put("medium", false);
            put("heavy", false);
        }
    };
    public inventory() {
        ObjectMapper map = new ObjectMapper(new YAMLFactory());
        map.findAndRegisterModules();
        inventory inv = map.readValue(new File("src/main/resources"), inventory.class);
    }
}