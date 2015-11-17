package andreas.gps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity implements View.OnClickListener{

    Button login_button;
    EditText login_edit, password_edit;
    TextView register_text;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        //login
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_low_in_rank);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login_edit = (EditText)findViewById(R.id.login_edit);
        password_edit = (EditText)findViewById(R.id.password_edit);
        login_button = (Button)findViewById(R.id.login_button);
        register_text = (TextView)findViewById(R.id.register_text);

        login_button.setOnClickListener(this);
        register_text.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:

                break;

            case R.id.register_text:

                Intent intent = new Intent(this, Register.class);
                startActivity(intent);

                break;
        }
    }
}
