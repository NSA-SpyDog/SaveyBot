
import info.savestate.saveybot.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The Main Class for SaveyBot.
 * @author Joseph El-Khouri
 */
public class Main {

    /**
     * @param a The command line arguments.
     * @throws java.io.IOException thrown in provided inputs aren't found.
     */
    public static void main(String[] a) throws IOException {
        String configPath = "saveybot.cfg";
        String dbPath = "saveybot.json";
        for (int i=0; i<a.length; i++) {
            // decode json 
            if (a[i].equals("--json")) {
                databaseToJSON(a[++i]);
                return;
            }
            if (a[i].equals("--config"))
                configPath = a[++i];
            if (a[i].equals("--db"))
                dbPath = a[++i];
        }
        SaveyBot sb = new SaveyBot(configPath);
        JSONFileManipulator jfm = new JSONFileManipulator(dbPath);
        System.out.println(jfm.getSlot("666", false));
        System.out.println(jfm.getSlot("48257298572", false));
        System.out.println(jfm.getSlot("Savestate", false));
        System.out.println(jfm.getSlot("butts_wowowow", false));
        System.out.println(jfm.getSlot("666", true));
        System.out.println(jfm.getSlot("48257298572", true));
        System.out.println(jfm.getSlot("butts_wowowow", true));
        System.out.println(jfm.getSlot("Savestate", true));
        
    }
    
    /**
     * Converts an ArrayList&lt;NameContainer&gt; serialized object to JSON.
     * Uses the DatabaseConverter along with a legacy class file called NameContainer.
     * @param filepath The path to the legacy database file.
     */
    public static void databaseToJSON(String filepath) {
        try {
            DatabaseConverter dbc = new DatabaseConverter(filepath);
            dbc.importDatabase();
            dbc.toJSON();
            String jsonString = dbc.toString();
            byte[] json = jsonString.getBytes("UTF-8");
            FileOutputStream fos = new FileOutputStream("db.json");
            fos.write(json);
            fos.close();
            System.out.println("JSON database saved to \"db.json\".");
        } catch (IOException e) {
            System.out.println("JSON database error.");
        }
    }
    
}
