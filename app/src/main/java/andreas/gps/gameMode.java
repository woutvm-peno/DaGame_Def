package andreas.gps;

// insert here the main game activity

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class gameMode extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_test);
    }


    public void switchMain(View view) {
        Intent intent = new Intent(this, mainInt.class);
        startActivity(intent);
    }
}
