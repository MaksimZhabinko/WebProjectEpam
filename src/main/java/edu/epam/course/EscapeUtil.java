package edu.epam.course;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

public class EscapeUtil {

    public static final HashMap m = new HashMap(); // todo взгляните на это класс
    static {
        m.put(34, "&quot;"); // "
        m.put(60, "&lt;");   // <
        m.put(62, "&gt;");   // >
    }

    public String escapeHtml() {
        String str = "<script>alert(\"abc\")</script>";
        try {
            StringWriter writer = new StringWriter((int) (str.length() * 1.5));
            escape(writer, str);
            System.out.println("encoded string is " + writer.toString() );
            return writer.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    public static void escape(Writer writer, String str) throws IOException {
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            int ascii = (int) c;
            String entityName = (String) m.get(ascii);
            if (entityName == null) {
                writer.write(c);
            } else {
                writer.write(entityName);
            }
        }
    }
}
