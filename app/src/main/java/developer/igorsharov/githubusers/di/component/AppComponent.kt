package developer.igorsharov.githubusers.di.component

import dagger.Component
import developer.igorsharov.githubusers.di.module.RetrofitModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent