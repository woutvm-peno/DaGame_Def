package andreas.gps;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity{

    Button register_button;
    EditText login_register_edit, password_register_edit, email_edit, password_confirm_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //login
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_low_in_rank);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_register_edit = (EditText)findViewById(R.id.login_register_edit);
        password_register_edit = (EditText)findViewById(R.id.password_register_edit);
        email_edit = (EditText)findViewById(R.id.email_edit);
        password_confirm_edit = (EditText)findViewById(R.id.password_confirm_edit);
        register_button = (Button)findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
