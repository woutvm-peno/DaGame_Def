package andreas.gps;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Andreas on 22/10/2015.
 */
public class data1 extends AppCompatActivity {

    public int amplitude;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_data1);
    }


    public void switchMain(View view) {
        Intent intent = new Intent(this, mainInt.class);
        startActivity(intent);
    }
    public void record(View view) {
        //counter
        new CountDownTimer(6000, 1000) {
            TextView counter = (TextView) findViewById(R.id.counter);
            SoundAct soundAct = new SoundAct(5000);
            public void onTick(long millisUntilFinished) {
                counter.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                counter.setText("done!");
                if (soundAct.get_sound() != 0) {
                    Log.w("record after if", String.valueOf(soundAct.get_sound()));
                    TextView text_sound = (TextView) findViewById(R.id.text_sound);
                    text_sound.setText(String.valueOf(soundAct.get_sound()));
                }
            }
        }.start();

    }
}
