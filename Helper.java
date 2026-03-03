import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Helper {

    public static <T> String displayText(Manager<T> object) {

        ArrayList<T>  list = object.getResources();
        StringBuilder sb = new StringBuilder();

        for (T field : list) {
            sb.append(field.toString());
        
        }

        return sb.toString();
    }

    public static String readNextValue(BufferedReader br) throws IOException {

        String value;

        while ((value = br.readLine()) != null) {
            value = value.trim();

            if (!value.isEmpty())
                return value;
        }

        return "";
    }
}