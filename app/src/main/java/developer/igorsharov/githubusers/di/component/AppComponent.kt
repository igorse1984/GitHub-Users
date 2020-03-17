package developer.igorsharov.githubusers.di.component

import dagger.Component
import developer.igorsharov.githubusers.di.module.ApiModule
import developer.igorsharov.githubusers.di.module.RepositoryModule
import developer.igorsharov.githubusers.ui.detail.DetailViewModel
import developer.igorsharov.githubusers.ui.root.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)

    fun inject(detailViewModel: DetailViewModel)
}