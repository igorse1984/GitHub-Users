package developer.igorsharov.githubusers.di.module

import dagger.Module
import dagger.Provides
import developer.igorsharov.githubusers.model.UserRepository
import developer.igorsharov.githubusers.retrofit.ApiServiceUsers
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(apiServiceUsers: ApiServiceUsers): UserRepository =
        UserRepository(apiServiceUsers)
}