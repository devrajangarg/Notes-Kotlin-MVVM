package custome.exoplayer.com.kotlinapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import custome.exoplayer.com.kotlinapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.frame, MainFragment()).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else
            super.onBackPressed()
    }
}
