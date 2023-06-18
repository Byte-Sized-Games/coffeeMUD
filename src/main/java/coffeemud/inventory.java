package coffeemud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class inventory {
    String[] items;

    public inventory() {
        items = loadInv();
    }
    public String[] loadInv() {
        BufferedReader reader;
        ArrayList<String> items = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("inv.txt"));
            String line = reader.readLine();
            while (line != null) {
                items.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            
        }
        return items.toArray(new String[items.size()]);

    }
    public void saveInv() throws IOException {
        try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("inv.txt"));
        for (String i : items) {
            writer.write(i);
            writer.newLine();
        }
        writer.close();
    } catch (IOException e) {

    }

    }
}