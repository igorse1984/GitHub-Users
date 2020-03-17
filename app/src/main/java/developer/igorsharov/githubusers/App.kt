package developer.igorsharov.githubusers

import android.app.Application
import developer.igorsharov.githubusers.di.component.AppComponent
import developer.igorsharov.githubusers.di.component.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().build()
    }
}