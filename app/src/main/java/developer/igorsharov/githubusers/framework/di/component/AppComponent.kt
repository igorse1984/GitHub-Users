package developer.igorsharov.githubusers.framework.di.component

import dagger.Component
import developer.igorsharov.githubusers.framework.di.module.ApiModule
import developer.igorsharov.githubusers.framework.di.module.UseCasesModule
import developer.igorsharov.githubusers.ui.detail.DetailViewModel
import developer.igorsharov.githubusers.ui.root.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, UseCasesModule::class])
interface AppComponent {

    fun inject(mainViewModel: MainViewModel)

    fun inject(detailViewModel: DetailViewModel)
}