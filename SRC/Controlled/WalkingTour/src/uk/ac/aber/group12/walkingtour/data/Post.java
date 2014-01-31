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
     * Attempts to Post a Json string via HTTP.
     *
     *
     * @return a boolean value, to confirm whether the Post was successful
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
     * Runs the send method, and if it fails,
     * it attempts to save the tour locally on the device.
     *
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
