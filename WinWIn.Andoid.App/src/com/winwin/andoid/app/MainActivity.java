package com.winwin.andoid.app;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.winwin.config.Constants;
import com.winwin.http.HttpServiceHandler;
import com.winwin.models.dto.UserDTO;
import com.winwin.models.dto.UserLoginDTO;
import com.winwin.session.SessionContainer;

public class MainActivity extends ActionBarActivity {

	ProgressDialog progress;
	 /** Called when the activity is first created.
     * @param savedInstanceState */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(loginButtonOnClick);
        progress = new ProgressDialog(this);
    }
    
    private View.OnClickListener loginButtonOnClick 
            = new View.OnClickListener() {

        public void onClick(View v) {
        	
        	progress.setTitle("Conectando");
        	progress.setMessage("Por favor espere...");
        	progress.show();
            String[] args = {Constants.BaseUrl + "/api/login"};
            new HttpAsyncTask().execute(args);
        }
    };
    
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
    	
    	int httpStatusCode;
    	ObjectMapper mapper;
        @Override
        protected String doInBackground(String... urls) {
        	HttpServiceHandler handler = new HttpServiceHandler();
            UserLoginDTO userLoginDto = new UserLoginDTO(
            		((EditText)findViewById(R.id.email))
                    .getText()
                    .toString(),
                    ((EditText)findViewById(R.id.password))
                    .getText()
                    .toString());
            mapper = new ObjectMapper();
            String jsonString = "";
            String result = "";
			try {
				jsonString = mapper.writeValueAsString(userLoginDto);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            try {
				result = handler.postAsJson(urls[0], jsonString);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            httpStatusCode = handler.getHttpStatusCode();
            return result;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
        	progress.dismiss();
        	if(httpStatusCode == 200)
        	{
        		try {
					SessionContainer.userSession = mapper.readValue(result,  UserDTO.class );
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		if(SessionContainer.userSession != null){
        			Toast.makeText(getBaseContext(), 
        					"Login OK!", Toast.LENGTH_LONG)
        					.show();
        			Intent nextScreen = new Intent(getApplicationContext(), HomeActivity.class);
        			startActivity(nextScreen);
        		}else{
        			Toast.makeText(getBaseContext(), 
        					"Error al crear la sesi�n.", Toast.LENGTH_LONG)
        					.show();
        		}
        		
        	}else{
        		Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
        	}
       
        }
    }

}
