package uk.ac.aber.group12.walkingtour.data;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by lot15 on 28/01/2014.
 */

public class Post {
    private String urlString;
    private String postBody;

    public Post(String urlString, String postBody) {
        this.urlString = urlString;
        this.postBody = postBody;
    }

    /**
     *
     * This method sends our JSON String containing information about a tour to the database to that
     * it can be stored.
     *
     * @return a boolean value (True/False) to confim whether or not the Post was sent successfully.
     */
    public boolean send() {
        int TIMEOUT = 60000;
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);
        HttpClient client = new DefaultHttpClient(httpParams);

        HttpPost request = new HttpPost(urlString);
        try {
            request.setEntity(new ByteArrayEntity(
                    postBody.toString().getBytes("UTF8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        try {
            HttpResponse response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * This method attempts to begin the sending of the JSON post to the database.
     * If there is an error in doing this, the tour is serialized and saved locally.
     */
    public void sendAsync() {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
