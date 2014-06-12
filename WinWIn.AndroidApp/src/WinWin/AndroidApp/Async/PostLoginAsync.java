/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WinWin.AndroidApp.Async;

import WinWin.AndroidApp.Config.Constants;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Franco
 */
public class PostLoginAsync extends AsyncTask<String, Integer, Double>{
    
    private Context _context;
    public PostLoginAsync(Context context ) {
        _context = context;
    }
    
     /**
     *
     * @param args [0] = email de usuario , [1] = contraseña para el usuario
     * @return valor de retorno 0 = correcto
     */
    protected Double doInBackground(String... args)
    {
        HttpURLConnection connection;
        OutputStreamWriter request = null;

            URL url = null;   
            String response = null;         
            String parameters = "UserName="+ args[0] +"&Password="+ args[1];

            try
            {
                url = new URL(Constants.BaseUrl + "/api/login");
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");    

                request = new OutputStreamWriter(connection.getOutputStream());
                request.write(parameters);
                request.flush();
                request.close();            
                String line = "";               
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                // Response from server after login process will be stored in response variable.                
                response = sb.toString();
                // You can perform UI operations here
                
                new AlertDialog.Builder(_context)
                        .setTitle("¡Atención!")
                        .setMessage("response OK: " + response)
                        .setNeutralButton("Close", null)
                        .show();
                
                isr.close();
                reader.close();

            }
            catch(IOException e)
            {
                // Error
                new AlertDialog.Builder(_context)
                        .setTitle("¡Error!")
                        .setMessage("Error: " + e.getMessage())
                        .setNeutralButton("Close", null)
                        .show();
            }
        return 0d;
    }
}
