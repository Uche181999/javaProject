import java.util.ArrayList;

public class DisplayHelper {

    public static <T> String displayText(Manager<T> object) {

        ArrayList<T>  list = object.getResources();
        StringBuilder sb = new StringBuilder();

        for (T field : list) {
            sb.append(field.toString());
        
        }

        return sb.toString();
    }
}