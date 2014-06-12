/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.winwin.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.winwin.helpers.StreamHelper;
import com.winwin.session.SessionContainer;

/**
 *
 * @author Franco
 */
public class HttpServiceHandler {
    
	private int httpStatusCode;
	
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	
	private static DefaultHttpClient httpClient;
	/**
	 * Builds a new HttpClient with the same CookieStore than the previous one.
	 * This allows to follow the http session, without keeping in memory the
	 * full DefaultHttpClient.
	 * @author Franco
	 */
	private HttpClient getHttpClient() {
		
		if(httpClient == null){
			httpClient = new DefaultHttpClient();
		    HttpContext httpContext = new BasicHttpContext();
		    httpContext.setAttribute(ClientContext.COOKIE_STORE, SessionContainer.sessionCookie);	
        }
		
        return httpClient;
	}
	
	
     /**
     *
     * @param args [0] = email de usuario , [1] = contrasenia para el usuario
     * @return valor de retorno 0 = correcto
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public String postAsJson(String url, String jsonString) throws ClientProtocolException, IOException
    {
    	String result = "";
    	
		HttpClient mhttpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);

        StringEntity se = new StringEntity(jsonString);

        httpPost.setEntity(se);
 
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        HttpResponse httpResponse = mhttpClient.execute(httpPost);
    	
        httpStatusCode = httpResponse.getStatusLine().getStatusCode();

        InputStream inputStream = httpResponse.getEntity().getContent();

        if(inputStream != null){
            result = StreamHelper.convertStreamToString(inputStream);
        }
	    return result;
    }
    
    public String getAsJsonString(String url) throws ClientProtocolException, IOException
    {
    	String result = "";
		HttpClient mHttpclient = getHttpClient();
		
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/url-encoded");

        HttpResponse httpResponse = mHttpclient.execute(httpGet);
        InputStream inputStream = httpResponse.getEntity().getContent();

        if(inputStream != null){
            result = StreamHelper.convertStreamToString(inputStream);
        }
        
        return result;
    }
}
