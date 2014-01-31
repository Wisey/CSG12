package uk.ac.aber.group12.walkingtour.data;

/**
 * Created by lot15 on 30/01/2014.
 */

public class JSON {
    /**
     *
     * This method adds a \\ before characters to stop them from being misinterpreted during the
     * JSON Post.
     *
     * @param string This is the JSON String to be sent.
     * @return A 'safe' JSON String, ready to be posted.
     */
    public static String quote(String string) {
        char         c = 0;
        int          i;
        int          len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String       t;
        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u" + t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }
}