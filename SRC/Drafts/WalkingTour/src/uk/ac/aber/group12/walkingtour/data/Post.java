package uk.ac.aber.group12.walkingtour.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lot15 on 28/01/2014.
 */
public class Post {
    public static boolean postJSON(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
