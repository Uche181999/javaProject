import java.util.ArrayList;

public class DisplayHelper {

    public static String buildCDDisplay(ArrayList<Mycd> cds) {

        StringBuilder sb = new StringBuilder();

        for (Mycd cd : cds) {
            sb.append(cd.toString());
            sb.append("\n\n----------------------------\n\n");
        }

        return sb.toString();
    }
}