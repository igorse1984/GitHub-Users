package developer.igorsharov.githubusers.framework.di.module

import dagger.Module
import dagger.Provides
import igor.sharov.data.PersistenceSourceUsers
import igor.sharov.data.UsersRepository
import igor.sharov.usecases.GetUsersUseCase
import javax.inject.Singleton

@Module
class UseCasesModule {
    @Provides
    @Singleton
    fun provideUsersInteractor(usersRepository: UsersRepository): GetUsersUseCase =
        GetUsersUseCase(usersRepository)

    @Provides
    @Singleton
    fun provideUsersRepository(persistenceSourceUsers: PersistenceSourceUsers): UsersRepository =
        UsersRepository(persistenceSourceUsers)
}