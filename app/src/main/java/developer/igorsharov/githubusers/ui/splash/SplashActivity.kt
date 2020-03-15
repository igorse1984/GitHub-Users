package developer.igorsharov.githubusers.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import developer.igorsharov.githubusers.ui.root.MainActivity
import developer.igorsharov.githubusers.R

class SplashActivity : AppCompatActivity(R.layout.splash_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            MainActivity.start(this)
            finish()
        }, 2000)
    }
}