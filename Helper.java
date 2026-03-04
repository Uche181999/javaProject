

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;


public class Helper {



public static <T> String displayText(Manager<T> object) {

        HashMap<String, T> map = object.getResources();
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------------\n\n");
        // iterate over the values of the map
        for (T field : map.values()) {
            sb.append(field.toString()).append("\n\n");
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