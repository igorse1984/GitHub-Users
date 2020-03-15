package developer.igorsharov.githubusers.di.module

import dagger.Module
import dagger.Provides
import developer.igorsharov.githubusers.retrofit.ApiServiceUsers
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun getApiUsers(): ApiServiceUsers = ApiServiceUsers.create()
}