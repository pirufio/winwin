/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WinWin.AndroidApp.Async;

import WinWin.AndroidApp.Async.Helpers.StreamHelper;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author Franco
 */
public class HttpAsync extends AsyncTask<String, Integer, Double>{
    
     /**
     *
     * @return
     */
    protected Double doInBackground(String... params)
    {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(params[0]);
        HttpResponse response;
        String result = null;
        try {
            response = client.execute(request);         
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                result = StreamHelper.convertStreamToString(instream);
                // now you have the string representation of the HTML request
                System.out.println("RESPONSE: " + result);
                instream.close();
                if (response.getStatusLine().getStatusCode() == 200) {
                    //OK!!!
                    System.out.println("Ok from server...");
                }
                else
                {
                    System.out.println("Error from server: " + 
                            response.getStatusLine()
                                    .getStatusCode());
                }
            }
            // Headers
            org.apache.http.Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println(header);
            }
        } catch (ClientProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return 0d;
    }
    
}
