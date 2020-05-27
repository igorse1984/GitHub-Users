package igor.sharov.usecases

import igor.sharov.data.UsersRepository
import igor.sharov.domain.User
import io.reactivex.Single

class GetUsersUseCase(private val usersRepository: UsersRepository) {

    fun getUsers(since: Int, amount: Int): Single<List<User>> =
        usersRepository.getUsers(since, amount)

    fun getUser(login: String): Single<User> = usersRepository.getUser(login)
}
