package uk.ac.aber.group12.walkingtour.data;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lot15 on 28/01/2014.
 */
public class Post {
    public static boolean postJSON(String urlString) {
        try {
            URL url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
