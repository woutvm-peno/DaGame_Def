package andreas.gps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Andreas on 22/10/2015.
 */
public class data1 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_data1);
    }


    public void switchMain(View view) {
        Intent intent = new Intent(this, mainInt.class);
        startActivity(intent);
    }
    public void record(View view) {
        new SoundAct(5000);
    }
}
