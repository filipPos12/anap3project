package scan.di.uoa.gr.exampleappsoftdev;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import shared.AccountProperties;
import tasks.LoginTask;
import tasks.RegisterTask;
import tasks.SaTask;

public class SaStatusActivity extends ActionBarActivity {
    private Button update;
    private Spinner spinner;
    private Handler progressBarbHandler = new Handler();

    private String[] data = new String[] {"Please click update ..."};
    private ArrayList<String> lst = new ArrayList<String>(Arrays.asList(data));
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sa_status);

        update = (Button) findViewById(R.id.button3);
        spinner = (Spinner) findViewById(R.id.saSpinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lst);
        spinner.setAdapter(adapter);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        update();
    }

    private void update() {
        String username = AccountProperties.getInstance().getUsername();
        String password = AccountProperties.getInstance().getPassword();

        SaTask task = new SaTask(SaStatusActivity.this, progressBarbHandler, adapter);
        task.execute(username, password);
    }
}
