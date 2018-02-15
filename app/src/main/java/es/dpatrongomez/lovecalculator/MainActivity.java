package es.dpatrongomez.lovecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText female = findViewById(R.id.female);
        final EditText  male = findViewById(R.id.male);
        Button calculate = findViewById(R.id.calculate);
        final TextView result = findViewById(R.id.result);
        final ImageView love = findViewById(R.id.love);

        MobileAds.initialize(this, "ca-app-pub-3101409762374546~7934127665");

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                InputMethodManager teclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                teclado.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                if (!female.getText().toString().isEmpty() && !male.getText().toString().isEmpty() ) {
                    RotateAnimation rotate = new RotateAnimation(0, 360,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setDuration(100);
                    love.startAnimation(rotate);
                    result.startAnimation(rotate);

                    String mujer = female.getText().toString();
                    String hombre = male.getText().toString();

                    Calendar c = Calendar.getInstance();
                    String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                    String month = String.valueOf(c.get(Calendar.MONTH));
                    String year = String.valueOf(c.get(Calendar.YEAR));

                    String result_string = mujer + hombre + day + month + year;
                    result_string = result_string.toLowerCase();
                    result_string = result_string.trim();

                    int seed = result_string.hashCode();

                    Random r = new Random(seed);

                    SystemClock.sleep(500);
                    result.setText((r.nextInt(100)+1) + "%");



                }
            }
        });
    }



}
