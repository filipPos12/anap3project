package scan.di.uoa.gr.exampleappsoftdev;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import tasks.LoginTask;
import tasks.RegisterTask;

public class WelcomeActivity extends ActionBarActivity {

    private Button login;
    private EditText username;
    private EditText password;
    private CheckBox register;

    private Handler progressBarbHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (CheckBox) findViewById(R.id.register);

        register.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Toast.makeText(getBaseContext(), "You can register now! ", Toast.LENGTH_LONG).show();
                    login.setText("REGISTER");
                } else {
                    Toast.makeText(getBaseContext(), "You can login now! ", Toast.LENGTH_LONG).show();
                    login.setText("LOGIN");
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (register.isChecked()) {
                RegisterTask task = new RegisterTask(WelcomeActivity.this, progressBarbHandler);
                task.execute(username.getText().toString(), password.getText().toString());
            } else {
                LoginTask task = new LoginTask(WelcomeActivity.this, progressBarbHandler);
                task.execute(username.getText().toString(), password.getText().toString());
            }
            }
        });
    }
}
