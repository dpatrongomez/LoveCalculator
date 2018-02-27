package es.dpatrongomez.lovecalculator

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.RotateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

import java.util.Calendar
import java.util.Random

class MainActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar)

        val female = findViewById<EditText>(R.id.female)
        val male = findViewById<EditText>(R.id.male)
        val result = findViewById<TextView>(R.id.result)
        val love = findViewById<ImageView>(R.id.love)
        val floatbutton = findViewById<FloatingActionButton>(R.id.floatbutton)

        MobileAds.initialize(this, "ca-app-pub-3101409762374546~7934127665")

        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        floatbutton.setOnClickListener {
            val teclado = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            teclado.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if (!female.text.toString().isEmpty() && !male.text.toString().isEmpty()) {
                val rotate = RotateAnimation(0f, 360f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
                rotate.duration = 100
                love.startAnimation(rotate)
                result.startAnimation(rotate)

                val mujer = female.text.toString()
                val hombre = male.text.toString()

                val c = Calendar.getInstance()
                val day = c.get(Calendar.DAY_OF_MONTH).toString()
                val month = c.get(Calendar.MONTH).toString()
                val year = c.get(Calendar.YEAR).toString()

                var resultstring = mujer + hombre + day + month + year
                resultstring = resultstring.toLowerCase()
                resultstring = resultstring.trim { it <= ' ' }

                val seed = resultstring.hashCode()

                val r = Random(seed.toLong())

                SystemClock.sleep(500)
                result.text = (r.nextInt(100) + 1).toString() + "%"


            }
        }
    }


}
