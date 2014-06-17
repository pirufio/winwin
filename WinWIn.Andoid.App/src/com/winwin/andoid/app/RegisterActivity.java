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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.winwin.config.Constants;
import com.winwin.http.HttpServiceHandler;
import com.winwin.models.dto.UserDTO;
import com.winwin.models.dto.UserRegisterDTO;
import com.winwin.session.SessionContainer;
import com.winwin.validators.Validators;

public class RegisterActivity extends ActionBarActivity {

	ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		Button registerButton = (Button)findViewById(R.id.btn_register);
        registerButton.setOnClickListener(registerButtonOnClick);
		
        progress = new ProgressDialog(this);
	}
	
	
	
private class HttpAsyncTask extends AsyncTask<String, Void, String> {
    	
    	int httpStatusCode;
    	ObjectMapper mapper;
        @Override
        protected String doInBackground(String... urls) {
        	HttpServiceHandler handler = new HttpServiceHandler();
        	
        	UserRegisterDTO userRegisterDto = new UserRegisterDTO(
            		((EditText)findViewById(R.id.email))
                    .getText()
                    .toString(),
                    ((EditText)findViewById(R.id.password))
                    .getText()
                    .toString(),
                    ((EditText)findViewById(R.id.user))
                    .getText()
                    .toString());
        	
            mapper = new ObjectMapper();
            
            String jsonString = "";
            String result = "";
            
			try {
				jsonString = mapper.writeValueAsString(userRegisterDto);
			} catch (JsonGenerationException e) {
				
				e.printStackTrace();
			} catch (JsonMappingException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
            try {
				result = handler.postAsJson(urls[0], jsonString);
			} catch (ClientProtocolException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
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
        		
    			Toast.makeText(getBaseContext(), 
    					"Usuario registrado correctamente!", Toast.LENGTH_LONG)
    					.show();
    			Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
    			startActivity(nextScreen);
    			
        	}else{
        		Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
        	}
       
        }
	
	}
	
	
	private View.OnClickListener registerButtonOnClick 
    = new View.OnClickListener() {

		public void onClick(View v) {
			if( validate()){
				
				CheckBox conditions = (CheckBox)findViewById(R.id.conditions);
				if(conditions.isChecked() == false){
					Toast.makeText(getBaseContext(), "Debe aceptar las políticas de seguridad",
							Toast.LENGTH_LONG).show();
				}
				else{
					
					//esta todo OK
					progress.setTitle("Registrando");
		        	progress.setMessage("Por favor espere...");
		        	progress.show();
		            String[] args = {Constants.BaseUrl + "/api/user"};
		            new HttpAsyncTask().execute(args);
				}
			}
		}
	};
	
	

	private boolean validate() {
		
		boolean isValid = true;
		//nombre de usuario
		EditText userName = (EditText)findViewById(R.id.user);
		if( userName.getText().toString().length() == 0 ){
			userName.setError( "Usuario es requerido" );
			isValid = false;
		}
		//password
		EditText password = (EditText)findViewById(R.id.password);
		if( password.getText().toString().length() == 0 ){
			password.setError( "Pass es requerido" );
			isValid = false;
		}
		//confirmar password
		EditText confirmPassword = (EditText)findViewById(R.id.confirmPassword);
		if( confirmPassword.getText().toString().equals(password.getText().toString()) == false ){
			confirmPassword.setError( "Pass y confirmar pass no coinciden" );
			isValid = false;
		}
		
		//email
		EditText email = (EditText)findViewById(R.id.email);
		if( email.getText().toString().length() == 0 ){
			email.setError( "Email es requerido" );
			isValid = false;
		}else if (Validators.validEmail(email.getText().toString()) == false){
			email.setError( "Email no válido" );
			isValid = false;
		}
		
		return isValid;
	}
}