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
import android.widget.Toast;

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
                String password_register_string = password_register_edit.getText().toString();
                String password_confirm_string = password_confirm_edit.getText().toString();
                String login_edit_string = login_register_edit.getText().toString();
                String email_edit_string = email_edit.getText().toString();

                if (password_confirm_string.equals("") || password_register_string.equals("") || login_edit_string.equals("")|| email_edit_string.equals("")){
                    Toast.makeText(getApplicationContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();

                }

                else if (!password_register_string.equals(password_confirm_string)){
                    Toast.makeText(getApplicationContext(),"Passwords do not match!",Toast.LENGTH_SHORT).show();

                }

                else if (email_edit_string.indexOf("@") <= -1){
                    Toast.makeText(getApplicationContext(),"Please fill in a valid email adress.",Toast.LENGTH_SHORT).show();

                }

                else{
                    create_account(login_edit_string,password_register_string,email_edit_string);
                }

            }
        });
    }

    public void create_account(String username, String password, String email){

    }
}
