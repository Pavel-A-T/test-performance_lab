import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {
    static Map<Long, String> mapValues = new HashMap<>();
    static final String TEST = "tests";
    static final String ID = "id";
    static final String VALUE = "value";
    static final String VALUES = "values";
    static final String REPORT = "report.json";

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("arguments not found!");
            return;
        }
        parse(args[0], args[1]);
    }


    public static void parse(String testFile, String valueFile) throws IOException {
        File file = new File(REPORT);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);

        JSONParser parser = new JSONParser();
        try (FileReader value = new FileReader(valueFile)) {
            JSONObject json = (JSONObject) parser.parse(value);
            JSONArray array = (JSONArray) json.get(VALUES);
            for (Object element: array) {
               JSONObject obj = (JSONObject) element;
               mapValues.put((Long) obj.get(ID), (String) obj.get(VALUE));
            }
        }
        catch (Exception e) {
            System.out.println("Parsing error...");
        }


       try (FileReader reader = new FileReader(testFile)) {
           JSONObject json = (JSONObject) parser.parse(reader);
           JSONArray array = (JSONArray) json.get(TEST);
           parseValues(array);
           fileWriter.write(json.toString());
           fileWriter.flush();
           fileWriter.close();
       }
       catch (Exception e) {
           System.out.println("Parsing error...");
       }
    }

    static void parseValues(JSONArray values) {
        for (Object element : values) {
            JSONObject obj = (JSONObject) element;
            Long key = (Long) obj.get(ID);
            if (key != null && mapValues.containsKey(key)) {
                ((JSONObject) element).put(VALUE, mapValues.get(key));
            }
            JSONArray array = (JSONArray) obj.get(VALUES);
            if (array != null) {
                parseValues(array);
            }
        }
    }
}