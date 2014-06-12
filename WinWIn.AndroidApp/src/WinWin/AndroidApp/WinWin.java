package WinWin.AndroidApp;
import WinWin.AndroidApp.Async.HttpAsync;
import WinWin.AndroidApp.Async.PostLoginAsync;
import static WinWin.AndroidApp.R.layout.main;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class WinWin extends Activity
{
    /** Called when the activity is first created.
     * @param savedInstanceState */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(main);
        
        Button loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(loginButtonOnClick);
    }
    
    private View.OnClickListener loginButtonOnClick 
            = new View.OnClickListener() {

        public void onClick(View v) {
            String[] args = {((EditText)findViewById(R.id.email))
                        .getText()
                        .toString(),
                ((EditText)findViewById(R.id.password))
                        .getText()
                        .toString()};
            new PostLoginAsync(getBaseContext()).execute(args);
        }
    };
   
}
