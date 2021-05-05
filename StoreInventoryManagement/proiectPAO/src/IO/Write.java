package IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;


public class Write {

    public static void writeAudit(String operation) {
        try (var out = new BufferedWriter(new FileWriter("StoreInventoryManagement/proiectPAO/data/audit.csv", true))) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            out.write(operation + ", " + timestamp.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
